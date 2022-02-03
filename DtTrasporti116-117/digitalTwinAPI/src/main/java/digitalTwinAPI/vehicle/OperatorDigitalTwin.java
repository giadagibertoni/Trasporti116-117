/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package digitalTwinAPI.vehicle;

import com.azure.core.http.rest.PagedIterable;
import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicDigitalTwinMetadata;
import digitalTwinAPI.connection.Client;
import dtModel.Address;
import dtModel.City;
import dtModel.District;
import dtModel.PostalCode;
import dtModel.vehicle.operator.OperatorDtModel;
import dtModel.vehicle.operator.OperatorPersonalData;
import dtModel.vehicle.operator.OperatorResidence;
import fhirSerializer.FHIROperatorResource;
import org.hl7.fhir.r4.model.Practitioner;
import utils.Constants;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class OperatorDigitalTwin {
    /**
     * Create a operator digital twin
     *
     * @return id of the operator created
     */
    public static String createOperator(String resource) {
        Practitioner operator = FHIROperatorResource.parseFHIRResource(resource);
        String id = operator.getIdentifierFirstRep().getValue();
        OperatorResidence residence= new OperatorResidence()
                .setAddress(new Address().setAddress(operator.getAddressFirstRep().getLine().get(0).toString()))
                .setCity(new City().setCity(operator.getAddressFirstRep().getCity()))
                .setDistrict(new District().setDistrict(operator.getAddressFirstRep().getDistrict()))
                .setPostalCode(new PostalCode().setPostalCode(Integer.parseInt(operator.getAddressFirstRep().getPostalCode())));


        OperatorPersonalData personalData = new OperatorPersonalData()
                .setName(operator.getNameFirstRep().getGivenAsSingleString())
                .setSurname(operator.getNameFirstRep().getFamily())
                .setBirthDate(operator.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .setResidence(residence);

        BasicDigitalTwin ambulanceDT = new BasicDigitalTwin(id)
                .setMetadata(
                        new BasicDigitalTwinMetadata().setModelId(Constants.OPERATOR_MODEL_ID)
                )
                .addToContents("personalData", personalData);

        BasicDigitalTwin basicTwinResponse = Client.getClient().createOrReplaceDigitalTwin(id, ambulanceDT, BasicDigitalTwin.class);

        return basicTwinResponse.getId();
    }

    /**
     * Get all operators
     *
     * @return List of operator resource
     */
    public static List<String> getOperators() {
        List<String> operators = new ArrayList<>();
        String query = "SELECT * FROM DIGITALTWINS WHERE IS_OF_MODEL('" + Constants.OPERATOR_MODEL_ID + "')";
        PagedIterable<OperatorDtModel> pageableResponse = Client.getClient().query(query, OperatorDtModel.class);
        pageableResponse.forEach(dt -> operators.add(FHIROperatorResource.createFHIRResource(dt)));
        return operators;
    }
}
