/*
 * Copyright (c) 2022. Giada Gibertoni
 */

import fhirParser.FHIRAmbulanceResource;
import fhirParser.FHIRParser;
import fhirParser.FHIRPatientResource;
import org.junit.Test;

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

}
