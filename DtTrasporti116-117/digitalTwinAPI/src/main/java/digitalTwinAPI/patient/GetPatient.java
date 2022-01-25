/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package digitalTwinAPI.patient;

import com.azure.core.http.rest.PagedIterable;
import digitalTwinAPI.connection.Client;
import dtModel.patient.PatientDtModel;
import fhirParser.FHIRPatientResource;
import utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class GetPatient {
    /**
     * Get all patients
     *
     * @return List of patient Fiscal Code
     */
    public static List<String> getPatients() {
        List<String> patients = new ArrayList<>();
        String query = "SELECT * FROM DIGITALTWINS WHERE IS_OF_MODEL('" + Constants.PATIENT_MODEL_ID + "')";
        PagedIterable<PatientDtModel> pageableResponse = Client.getClient().query(query, PatientDtModel.class);

        pageableResponse.forEach(r -> patients.add(FHIRPatientResource.createFHIRResource(r)));

        return patients;
    }
}
