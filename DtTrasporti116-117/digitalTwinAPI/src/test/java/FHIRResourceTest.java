/*
 * Copyright (c) 2022. Giada Gibertoni
 */

import com.azure.core.http.rest.PagedIterable;
import digitalTwinAPI.connection.Client;
import dtModel.patient.PatientDtModel;
import fhirParser.FHIRParser;
import fhirParser.FHIRPatientResource;
import org.junit.Test;
import utils.Constants;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class FHIRResourceTest {
    @Test
    public void testCreatePatientResource() {
        String patient = FHIRPatientResource.createFHIRResource(
                TestDataValue.PATIENT_DT_MODEL);
        System.out.println(patient);
        assertTrue(FHIRParser.inputJSONResourceIsCorrect(patient));
    }

}
