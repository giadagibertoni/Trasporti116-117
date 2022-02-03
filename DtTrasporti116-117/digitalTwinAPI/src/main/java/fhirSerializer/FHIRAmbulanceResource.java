/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package fhirSerializer;

import dtModel.vehicle.ambulance.AmbulanceDtModel;
import org.hl7.fhir.r4.model.*;
import org.jetbrains.annotations.NotNull;
import utils.Constants;

public class FHIRAmbulanceResource {

    public static String createFHIRResource(@NotNull AmbulanceDtModel dt){
        // Create a resource instance
        Device ambulance = new Device();
        ambulance.addIdentifier(new Identifier().setValue(dt.getId()));

        switch (dt.getState()){
            case BUSY -> ambulance.setStatus(Device.FHIRDeviceStatus.ACTIVE);
            case FREE -> ambulance.setStatus(Device.FHIRDeviceStatus.INACTIVE);
            case UNDER_MAINTENANCE -> ambulance.setStatus(Device.FHIRDeviceStatus.ENTEREDINERROR);
            case DISUSED -> ambulance.setStatus(Device.FHIRDeviceStatus.UNKNOWN);
        }

        ambulance.setType(new CodeableConcept()
                .addCoding(new Coding()
                        .setSystem(Constants.SNOMED_SYSTEMS)
                        .setCode(Constants.AMBULANCE_CODE)));

        ambulance.addContained(new Location()
                .setPhysicalType(new CodeableConcept()
                        .addCoding(new Coding()
                                .setSystem(Constants.SNOMED_SYSTEMS)
                                .setCode(Constants.GPS_CODE)))
                        .setPosition(new Location.LocationPositionComponent(
                                new DecimalType(dt.getCoordinates().getLongitude()),
                                new DecimalType(dt.getCoordinates().getLatitude()))));

        return FHIRParser.getParser().encodeResourceToString(ambulance);
    }

    public static Device parseFHIRResource(String jsonResource){
        return FHIRParser.getParser().parseResource(Device.class, jsonResource);
    }
}
