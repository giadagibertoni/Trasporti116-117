/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package fhirParser;

import domain.patient.*;
import org.hl7.fhir.r4.model.*;
import org.hl7.fhir.r4.model.Condition;

import java.time.ZoneId;
import java.util.Date;

public class FHIRPatientResource {

    public static String createFHIRResource(PatientFiscalCode fc, PatientPersonalData personalData, PatientResidence residence, PatientCondition condition){
        // Create a resource instance
        Patient patient = new Patient();
        patient.addIdentifier(new Identifier().setValue(fc.getFiscalCode()));
        patient.addName(new HumanName().setFamily(personalData.getSurname()).addGiven(personalData.getName()).setUse(HumanName.NameUse.OFFICIAL));
        patient.setBirthDate(Date.from(personalData.getBirthDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));

        patient.addAddress(new Address()
                .setDistrict(residence.getDistrict().getDistrict())
                .setCity(residence.getCity().getCity())
                .addLine(residence.getAddress().getAddress())
                .setPostalCode(String.valueOf(residence.getPostalCode().getPostalCode())));

        patient.addContained(new Condition().setCode(new CodeableConcept(new Coding().setCode(String.valueOf(condition.getCode())).setSystem(condition.getSystem()))));

        return FHIRParser.getParser().encodeResourceToString(patient);
    }

    public static Patient parseFHIRResource(String jsonResource){
        Patient patient = FHIRParser.getParser().parseResource(Patient.class, jsonResource);

        return patient;
    }
}
