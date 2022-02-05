/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package fhirserializer;

import dtmodel.vehicle.operator.OperatorDtModel;
import org.hl7.fhir.r4.model.*;
import org.jetbrains.annotations.NotNull;
import utils.Constants;

import java.time.ZoneId;
import java.util.Date;

public class FHIROperatorResource {
    public static String createFHIRResource(@NotNull OperatorDtModel dt){
        Practitioner operator = new Practitioner();
        operator.addIdentifier(new Identifier().setValue(dt.getOperatorId()));
        operator.addName(new HumanName().setFamily(dt.getPersonalData().getSurname()).addGiven(dt.getPersonalData().getName()));
        operator.setBirthDate(Date.from(dt.getPersonalData().getBirthDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));

        operator.addAddress(new Address()
                .setDistrict(dt.getPersonalData().getResidence().getDistrict().getDistrict())
                .setCity(dt.getPersonalData().getResidence().getCity().getCity())
                .addLine(dt.getPersonalData().getResidence().getAddress().getAddress())
                .setPostalCode(String.valueOf(dt.getPersonalData().getResidence().getPostalCode().getPostalCode())));

        operator.addContained(new PractitionerRole().addSpecialty(
                new CodeableConcept().addCoding(
                        new Coding()
                                .setCode(Constants.PRACTITIONER_ROLE_CODE)
                                .setSystem(Constants.SNOMED_SYSTEMS))));

        return FHIRParser.getParser().encodeResourceToString(operator);
    }

    public static Practitioner parseFHIRResource(String jsonResource){
        return FHIRParser.getParser().parseResource(Practitioner.class, jsonResource);
    }
}
