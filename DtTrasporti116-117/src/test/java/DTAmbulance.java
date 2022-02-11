/*
 * Copyright (c) 2022. Giada Gibertoni
 */

import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicRelationship;
import patientbc.PatientDigitalTwin;
import sharedkernel.azureservice.Client;
import transportbc.FHIRTransportResource;
import transportbc.TransportDigitalTwin;
import vehiclebc.AmbulanceDigitalTwin;
import org.junit.BeforeClass;
import org.junit.Test;
import vehiclebc.dtmodel.ambulance.AmbulanceDtModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Test
    public void getFreeAmbulance() {
        PatientDigitalTwin.createPatient(TestDataValue.PATIENT_RESOURCE);
        AmbulanceDigitalTwin.createAmbulance(TestDataValue.AMBULANCE_RESOURCE);
        TransportDigitalTwin.createTransport(TestDataValue.TRANSPORT_SCHEDULED_RESURCE);

        assertTrue(AmbulanceDigitalTwin.getFreeAmbulance(
                LocalDateTime.of(2022, 2, 10, 13, 0),
                LocalDateTime.of(2022, 2, 10, 14, 0)).isPresent());

        assertTrue(AmbulanceDigitalTwin.getFreeAmbulance(
                LocalDateTime.of(2022, 2, 10, 16, 30),
                LocalDateTime.of(2022, 2, 10, 18, 0)).isPresent());
    }
    @Test
    public void getBusyAmbulance(){
        PatientDigitalTwin.createPatient(TestDataValue.PATIENT_RESOURCE);
        AmbulanceDigitalTwin.createAmbulance(TestDataValue.AMBULANCE_RESOURCE);
        TransportDigitalTwin.createTransport(TestDataValue.TRANSPORT_SCHEDULED_RESURCE);

        Optional<String> ambulance = AmbulanceDigitalTwin.getFreeAmbulance(
                LocalDateTime.of(2022, 2, 10, 15, 00),
                LocalDateTime.of(2022, 2, 10, 16, 30));
        if (ambulance.isPresent()){
            assertFalse(ambulance.get().contains(TestDataValue.AMBULANCE_ID));
        } else {
            assertTrue(true);
        }

        ambulance = AmbulanceDigitalTwin.getFreeAmbulance(
                LocalDateTime.of(2022, 2, 10, 14, 0),
                LocalDateTime.of(2022, 2, 10, 15, 30));
        if (ambulance.isPresent()){
            assertFalse(ambulance.get().contains(TestDataValue.AMBULANCE_ID));
        } else {
            assertTrue(true);
        }

        ambulance = AmbulanceDigitalTwin.getFreeAmbulance(
                LocalDateTime.of(2022, 2, 10, 14, 0),
                LocalDateTime.of(2022, 2, 10, 17, 0));
        if (ambulance.isPresent()){
            assertFalse(ambulance.get().contains(TestDataValue.AMBULANCE_ID));
        } else {
            assertTrue(true);
        }
    }
}
