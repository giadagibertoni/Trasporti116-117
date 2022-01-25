/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package digitalTwinAPI.patient;

import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicDigitalTwinMetadata;
import digitalTwinAPI.connection.Client;
import dtModel.Address;
import dtModel.City;
import dtModel.District;
import dtModel.PostalCode;
import dtModel.patient.*;
import fhirParser.FHIRPatientResource;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.Patient;
import utils.Constants;

import java.time.ZoneId;

/**
 * Contains create patient digital twin API
 */
public final class CreatePatient {
    private CreatePatient() { }

    /**
     * Create a patient digital twin
     *
     * @return id of the patient created
     */
    public static String createPatient(String resource) {
        Patient patient = FHIRPatientResource.parseFHIRResource(resource);

        PatientFiscalCode fs = new PatientFiscalCode(patient.getIdentifierFirstRep().getValue());

        PatientResidence residence = new PatientResidence(
                new Address(patient.getAddressFirstRep().getLine().get(0).toString()),
                new City(patient.getAddressFirstRep().getCity()),
                new District(patient.getAddressFirstRep().getDistrict()),
                new PostalCode(Integer.parseInt(patient.getAddressFirstRep().getPostalCode()))
        );

        PatientPersonalData patientPersonalData = new PatientPersonalData(
                fs,
                patient.getNameFirstRep().getGivenAsSingleString(),
                patient.getNameFirstRep().getFamily(),
                patient.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                residence
        );
        Condition fhirCondition = (Condition) patient.getContained().get(0);
        System.out.println("condition " + fhirCondition.getCode().getCodingFirstRep().getCode());


        PatientCondition condition = new PatientCondition(
                Integer.parseInt(fhirCondition.getCode().getCodingFirstRep().getCode()),
                fhirCondition.getCode().getCodingFirstRep().getSystem());

        BasicDigitalTwin patientDT = new BasicDigitalTwin(patient.getIdentifierFirstRep().toString())
                .setMetadata(
                        new BasicDigitalTwinMetadata().setModelId(Constants.PATIENT_MODEL_ID)
                )
                .addToContents("personalData", patientPersonalData)
                .addToContents("condition", condition);

        BasicDigitalTwin basicTwinResponse = Client.getClient().createOrReplaceDigitalTwin(fs.getFiscalCode(), patientDT, BasicDigitalTwin.class);

        return basicTwinResponse.getId();
    }
}
