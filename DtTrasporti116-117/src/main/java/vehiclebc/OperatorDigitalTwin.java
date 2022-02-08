/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package vehiclebc;

import com.azure.core.http.rest.PagedIterable;
import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicDigitalTwinMetadata;
import sharedkernel.azureservice.Client;
import vehiclebc.dtmodel.operator.OperatorAddress;
import vehiclebc.dtmodel.operator.OperatorCity;
import vehiclebc.dtmodel.operator.OperatorDistrict;
import vehiclebc.dtmodel.operator.OperatorPostalCode;
import vehiclebc.dtmodel.operator.OperatorDtModel;
import vehiclebc.dtmodel.operator.OperatorPersonalData;
import vehiclebc.dtmodel.operator.OperatorResidence;
import org.hl7.fhir.r4.model.Practitioner;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
                .setAddress(new OperatorAddress().setAddress(operator.getAddressFirstRep().getLine().get(0).toString()))
                .setCity(new OperatorCity().setCity(operator.getAddressFirstRep().getCity()))
                .setDistrict(new OperatorDistrict().setDistrict(operator.getAddressFirstRep().getDistrict()))
                .setPostalCode(new OperatorPostalCode().setPostalCode(Integer.parseInt(operator.getAddressFirstRep().getPostalCode())));


        OperatorPersonalData personalData = new OperatorPersonalData()
                .setName(operator.getNameFirstRep().getGivenAsSingleString())
                .setSurname(operator.getNameFirstRep().getFamily())
                .setBirthDate(operator.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .setResidence(residence);

        BasicDigitalTwin ambulanceDT = new BasicDigitalTwin(id)
                .setMetadata(
                        new BasicDigitalTwinMetadata().setModelId(VehicleConstants.OPERATOR_MODEL_ID)
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
        String query = "SELECT * FROM DIGITALTWINS WHERE IS_OF_MODEL('" + VehicleConstants.OPERATOR_MODEL_ID + "')";
        PagedIterable<OperatorDtModel> pageableResponse = Client.getClient().query(query, OperatorDtModel.class);
        pageableResponse.forEach(dt -> operators.add(FHIROperatorResource.createFHIRResource(dt)));
        return operators;
    }

    /**
     * Get operator by id
     *
     * @return operator resource
     */
    public static Optional<String> getOperator(String id) {
        String query = "SELECT * FROM DIGITALTWINS " +
                "WHERE IS_OF_MODEL('"
                + VehicleConstants.OPERATOR_MODEL_ID + "' )"
                + "AND $dtId = '" + id + "'";

        Optional<OperatorDtModel> dt = Client.getClient().query(query, OperatorDtModel.class).stream().findFirst();

        return dt.map(FHIROperatorResource::createFHIRResource);
    }
}
