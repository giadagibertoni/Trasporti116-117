/*
 * Copyright (c) 2022. Giada Gibertoni
 */

import com.azure.digitaltwins.core.BasicDigitalTwin;
import sharedkernel.azureservice.Client;
import patientbc.PatientDigitalTwin;
import transportbc.TransportDigitalTwin;
import vehiclebc.AmbulanceDigitalTwin;
import vehiclebc.OperatorDigitalTwin;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void getScheduledTransports(){
        TransportDigitalTwin.createTransport(TestDataValue.TRANSPORT_SCHEDULED_RESURCE);
        List<String> transports = TransportDigitalTwin.getScheduledTransports();
        assertTrue(transports.stream().anyMatch(s -> s.contains(TestDataValue.TRANSPORT_ID)
                        && s.contains(TestDataValue.AMBULANCE_ID)
                        && s.contains(TestDataValue.PATIENT_FISCAL_CODE.getFiscalCode())));
    }
}
