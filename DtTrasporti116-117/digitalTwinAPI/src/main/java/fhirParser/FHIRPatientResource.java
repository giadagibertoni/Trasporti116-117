/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package fhirParser;

import dtModel.patient.*;
import org.hl7.fhir.r4.model.*;
import org.hl7.fhir.r4.model.Condition;
import org.jetbrains.annotations.NotNull;

import java.time.ZoneId;
import java.util.Date;

public class FHIRPatientResource {

    public static String createFHIRResource(@NotNull PatientDtModel dt){
        // Create a resource instance
        Patient patient = new Patient();
        patient.addIdentifier(new Identifier().setValue(dt.getPersonalData().getFiscalCode().getFiscalCode()));
        patient.addName(new HumanName().setFamily(dt.getPersonalData().getSurname()).addGiven(dt.getPersonalData().getName()).setUse(HumanName.NameUse.OFFICIAL));
        patient.setBirthDate(Date.from(dt.getPersonalData().getBirthDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));

        patient.addAddress(new Address()
                .setDistrict(dt.getPersonalData().getResidence().getDistrict().getDistrict())
                .setCity(dt.getPersonalData().getResidence().getCity().getCity())
                .addLine(dt.getPersonalData().getResidence().getAddress().getAddress())
                .setPostalCode(String.valueOf(dt.getPersonalData().getResidence().getPostalCode().getPostalCode())));

        patient.addContained(new Condition().setCode(new CodeableConcept(new Coding().setCode(String.valueOf(dt.getCondition().getCode())).setSystem(dt.getCondition().getSystem()))));

        return FHIRParser.getParser().encodeResourceToString(patient);
    }

    public static Patient parseFHIRResource(String jsonResource){
        return FHIRParser.getParser().parseResource(Patient.class, jsonResource);
    }
}
