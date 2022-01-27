/*
 * Copyright (c) 2022. Giada Gibertoni
 */

import com.azure.digitaltwins.core.BasicDigitalTwin;
import digitalTwinAPI.connection.Client;
import digitalTwinAPI.vehicle.AmbulanceDigitalTwin;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DTAmbulance {

    @BeforeClass
    public static void createConnection() {
        Client.getClient();
    }

    @Test
    public void createAmbulance() {
        AmbulanceDigitalTwin.createAmbulance(TestDataValue.AMBULANCE_RESOURCE);
        assertEquals(TestDataValue.EQUALS_DT, Client.getClient().getDigitalTwin(TestDataValue.AMBULANCE_ID, BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);
    }

    @Test
    public void getAmbulances() {
        AmbulanceDigitalTwin.createAmbulance(TestDataValue.AMBULANCE_RESOURCE);

        List<String> ambulances = AmbulanceDigitalTwin.getAmbulance();
        assertTrue(ambulances.stream().anyMatch(s -> s.contains(TestDataValue.AMBULANCE_ID)));
    }
}
