/*
 * Copyright (c) 2022. Giada Gibertoni
 */

/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

import com.azure.digitaltwins.core.BasicDigitalTwin;
import digitalTwinAPI.connection.Client;
import digitalTwinAPI.patient.PatientDigitalTwin;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DTPatient {
    @BeforeClass
    public static void createConnection() {
        Client.getClient();
    }

    @Test
    public void createPatient() {
        PatientDigitalTwin.createPatient(TestDataValue.PATIENT_RESOURCE);
        assertEquals(TestDataValue.EQUALS_DT, Client.getClient().getDigitalTwin(TestDataValue.PATIENT_FISCAL_CODE.getFiscalCode(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);
    }

    @Test
    public void getPatient() {
        PatientDigitalTwin.createPatient(TestDataValue.PATIENT_RESOURCE);
        List<String> patients = PatientDigitalTwin.getPatients();
        assertTrue(patients.stream().anyMatch(s -> s.contains(TestDataValue.PATIENT_FISCAL_CODE.getFiscalCode())));
    }

}

