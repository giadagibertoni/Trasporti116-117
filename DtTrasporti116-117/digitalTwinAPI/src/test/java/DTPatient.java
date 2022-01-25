/*
 * Copyright (c) 2022. Giada Gibertoni
 */

/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

import com.azure.digitaltwins.core.BasicDigitalTwin;
import digitalTwinAPI.connection.Client;
import digitalTwinAPI.patient.CreatePatient;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DTPatient {


    @BeforeClass
    public static void createConnection() {
        Client.getClient();
    }

    @Test
    public void createPatient() {
        CreatePatient.createPatient(TestDataValue.PATIENT_RESOURCE);
        assertEquals(TestDataValue.EQUALS_DT, Client.getClient().getDigitalTwin(TestDataValue.PATIENT_FISCAL_CODE.getFiscalCode(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);
    }

}

