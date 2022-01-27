/*
 * Copyright (c) 2022. Giada Gibertoni
 */

import com.azure.digitaltwins.core.BasicDigitalTwin;
import digitalTwinAPI.connection.Client;
import digitalTwinAPI.vehicle.AmbulanceDigitalTwin;
import org.json.simple.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DTAmbulance {

    @BeforeClass
    public static void createConnection() {
        Client.getClient();
    }

    @Test
    public void createAmbulance() {
        AmbulanceDigitalTwin.createAmbulance(TestDataValue.AMBULANCE_RESOURCE);
        assertEquals(TestDataValue.EQUALS_DT, Client.getClient().getDigitalTwin(TestDataValue.AMBULANCE_ID.getid(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);
    }

    @Test
    public void getAmbulances() {
        AmbulanceDigitalTwin.createAmbulance(TestDataValue.AMBULANCE_RESOURCE);
        System.out.println(Client.getClient().getDigitalTwin(TestDataValue.AMBULANCE_ID.getid(), JSONObject.class));
        List<String> ambulances = AmbulanceDigitalTwin.getAmbulance();
        ambulances.forEach(System.out::println);
        System.out.println(TestDataValue.AMBULANCE_RESOURCE);
        assertTrue(ambulances.contains(TestDataValue.AMBULANCE_RESOURCE));
    }
}
