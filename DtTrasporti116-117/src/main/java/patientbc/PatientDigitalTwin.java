/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package patientbc;

import com.azure.core.http.rest.PagedIterable;
import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicDigitalTwinMetadata;
import sharedkernel.azureservice.Client;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.Patient;
import patientbc.dtmodel.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains create patient digital twin API
 */
public final class PatientDigitalTwin {
    private PatientDigitalTwin() { }

    /**
     * Create a patient digital twin
     *
     * @return id of the patient created
     */
    public static String createPatient(String resource) {
        Patient patient = FHIRPatientResource.parseFHIRResource(resource);

        PatientFiscalCode fs = new PatientFiscalCode().setFiscalCode(patient.getIdentifierFirstRep().getValue());

        PatientResidence residence = new PatientResidence()
                .setAddress(new PatientAddress().setAddress(patient.getAddressFirstRep().getLine().get(0).toString()))
                .setCity(new PatientCity().setCity(patient.getAddressFirstRep().getCity()))
                .setDistrict(new PatientDistrict().setDistrict(patient.getAddressFirstRep().getDistrict()))
                .setPostalCode(new PatientPostalCode().setPostalCode(Integer.parseInt(patient.getAddressFirstRep().getPostalCode())));

        PatientPersonalData patientPersonalData = new PatientPersonalData()
                .setFiscalCode(fs)
                .setName(patient.getNameFirstRep().getGivenAsSingleString())
                .setSurname(patient.getNameFirstRep().getFamily())
                .setBirthDate(patient.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .setResidence(residence);

        Condition fhirCondition = (Condition) patient.getContained().get(0);

        PatientCondition condition = new PatientCondition()
                .setCode(Integer.parseInt(fhirCondition.getCode().getCodingFirstRep().getCode()))
                .setSystem(fhirCondition.getCode().getCodingFirstRep().getSystem());

        BasicDigitalTwin patientDT = new BasicDigitalTwin(patient.getIdentifierFirstRep().toString())
                .setMetadata(
                        new BasicDigitalTwinMetadata().setModelId(PatientConstants.PATIENT_MODEL_ID)
                )
                .addToContents("personalData", patientPersonalData)
                .addToContents("condition", condition);

        BasicDigitalTwin basicTwinResponse = Client.getClient().createOrReplaceDigitalTwin(fs.getFiscalCode(), patientDT, BasicDigitalTwin.class);

        return basicTwinResponse.getId();
    }

    /**
     * Get all patients
     *
     * @return List of patient resource
     */
    public static List<String> getPatients() {
        List<String> patients = new ArrayList<>();
        String query = "SELECT * FROM DIGITALTWINS WHERE IS_OF_MODEL('" + PatientConstants.PATIENT_MODEL_ID + "')";
        PagedIterable<PatientDtModel> pageableResponse = Client.getClient().query(query, PatientDtModel.class);

        pageableResponse.forEach(dt -> patients.add(FHIRPatientResource.createFHIRResource(dt)));

        return patients;
    }
}
