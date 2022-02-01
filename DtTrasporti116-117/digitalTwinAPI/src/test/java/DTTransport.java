/*
 * Copyright (c) 2022. Giada Gibertoni
 */

import com.azure.digitaltwins.core.BasicDigitalTwin;
import digitalTwinAPI.connection.Client;
import digitalTwinAPI.patient.PatientDigitalTwin;
import digitalTwinAPI.transport.TransportDigitalTwin;
import digitalTwinAPI.vehicle.AmbulanceDigitalTwin;
import digitalTwinAPI.vehicle.OperatorDigitalTwin;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DTTransport {

    @BeforeClass
    public static void createConnection() {
        Client.getClient();
        AmbulanceDigitalTwin.createAmbulance(TestDataValue.AMBULANCE_RESOURCE);
        PatientDigitalTwin.createPatient(TestDataValue.PATIENT_RESOURCE);
        OperatorDigitalTwin.createOperator(TestDataValue.OPERATOR_RESOURCE);
    }

    @Test
    public void createTransport() {
        TransportDigitalTwin.createTransport(TestDataValue.TRANSPORT_SCHEDULED_RESURCE);
        assertEquals(TestDataValue.EQUALS_DT, Client.getClient().getDigitalTwin(TestDataValue.TRANSPORT_ID, BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);
    }
}
