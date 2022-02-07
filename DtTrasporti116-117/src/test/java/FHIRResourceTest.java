/*
 * Copyright (c) 2022. Giada Gibertoni
 */

import org.junit.Test;
import patientbc.FHIRPatientResource;
import sharedkernel.FHIRParser;
import transportbc.FHIRTransportResource;
import vehiclebc.FHIRAmbulanceResource;
import vehiclebc.FHIROperatorResource;

import static org.junit.Assert.assertTrue;

public class FHIRResourceTest {
    @Test
    public void testCreatePatientResource() {
        String patient = FHIRPatientResource.createFHIRResource(
                TestDataValue.PATIENT_DT_MODEL);
        System.out.println(patient);
        assertTrue(FHIRParser.inputJSONResourceIsCorrect(patient));
    }

    @Test
    public void testCreateAmbulanceResource() {
        String ambulance = FHIRAmbulanceResource.createFHIRResource(
                TestDataValue.AMBULANCE_DT_MODEL);
        System.out.println(ambulance);
        assertTrue(FHIRParser.inputJSONResourceIsCorrect(ambulance));
    }

    @Test
    public void testCreateOperatorResource() {
        String operator = FHIROperatorResource.createFHIRResource(TestDataValue.OPERATOR_DT_MODEL);
        System.out.println(operator);
        assertTrue(FHIRParser.inputJSONResourceIsCorrect(operator));
    }

    @Test
    public void testCreateTransportScheduledResource() {
        String transport = FHIRTransportResource.createTransportAppointmentFHIRResource(TestDataValue.TRANSPORT_DT_MODEL_SCHEDULED, TestDataValue.AMBULANCE_ID, TestDataValue.PATIENT_FISCAL_CODE.getFiscalCode());
        System.out.println(transport);
        assertTrue(FHIRParser.inputJSONResourceIsCorrect(transport));
        assertTrue(transport.contains("\"resourceType\":\"Appointment\""));
        transport = FHIRTransportResource.createTransportEncounterFHIRResource(TestDataValue.TRANSPORT_DT_MODEL_SCHEDULED, TestDataValue.AMBULANCE_ID, TestDataValue.PATIENT_FISCAL_CODE.getFiscalCode(), TestDataValue.OPERATOR_ID);
        assertTrue(FHIRParser.inputJSONResourceIsCorrect(transport));
        assertTrue(transport.contains("\"resourceType\":\"Appointment\""));
    }

    @Test
    public void testCreateTransportInProgressResource() {
        String transport = FHIRTransportResource.createTransportEncounterFHIRResource(TestDataValue.TRANSPORT_DT_MODEL_IN_PROGRESS, TestDataValue.AMBULANCE_ID, TestDataValue.PATIENT_FISCAL_CODE.getFiscalCode(), TestDataValue.OPERATOR_ID);
        System.out.println(transport);
        assertTrue(FHIRParser.inputJSONResourceIsCorrect(transport));
        assertTrue(transport.contains("\"status\":\"in-progress\""));
    }

    @Test
    public void testCreateTransportCompletedResource() {
        String transport = FHIRTransportResource.createTransportEncounterFHIRResource(TestDataValue.TRANSPORT_DT_MODEL_COMPLETED, TestDataValue.AMBULANCE_ID, TestDataValue.PATIENT_FISCAL_CODE.getFiscalCode(), TestDataValue.OPERATOR_ID);
        System.out.println(transport);
        assertTrue(FHIRParser.inputJSONResourceIsCorrect(transport));
        assertTrue(transport.contains("\"status\":\"finished\""));
    }


}
