/*
 * Copyright (c) 2022. Giada Gibertoni
 */

import fhirParser.FHIRParser;
import fhirParser.FHIRPatientResource;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class FHIRResourceTest {
    @Test
    public void testCreatePatientResource() {
        String patient = FHIRPatientResource.createFHIRResource(
                TestDataValue.PATIENT_FISCAL_CODE,
                TestDataValue.PATIENT_PERSONALDATA,
                TestDataValue.PATIENT_RESIDENCE,
                TestDataValue.PATIENT_CONDITION);
        System.out.println(patient);
        assertTrue(FHIRParser.inputJSONResourceIsCorrect(patient));
    }

}
