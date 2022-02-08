/*
 * Copyright (c) 2022. Giada Gibertoni
 */

import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicRelationship;
import sharedkernel.azureservice.Client;
import vehiclebc.AmbulanceDigitalTwin;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
        List<String> ambulances = AmbulanceDigitalTwin.getAmbulances();
        assertTrue(ambulances.stream().anyMatch(s -> s.contains(TestDataValue.AMBULANCE_ID)));
    }

    @Test
    public void addAmbulanceWorkDay() {
        AmbulanceDigitalTwin.addOperatorWorkDay(TestDataValue.AMBULANCE_ID, TestDataValue.OPERATOR_ID, LocalDate.of(2022, 03, 01));
        assertEquals(TestDataValue.EQUALS_DT, Client.getClient().getRelationship(TestDataValue.AMBULANCE_ID, TestDataValue.REL_AMBULANCE_OPERATOR_ID, BasicRelationship.class).getClass(), BasicRelationship.class);
    }

    @Test
    public void getAmbulance() {
        AmbulanceDigitalTwin.createAmbulance(TestDataValue.AMBULANCE_RESOURCE);
        Optional<String> ambulance = AmbulanceDigitalTwin.getAmbulance(TestDataValue.AMBULANCE_ID);
        assertTrue(ambulance.isPresent());
        assertTrue(ambulance.get().contains(TestDataValue.AMBULANCE_ID));
    }
}
