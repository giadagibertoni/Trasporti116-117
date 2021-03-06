/*
 * Copyright (c) 2022. Giada Gibertoni
 */

import com.azure.digitaltwins.core.BasicDigitalTwin;
import sharedkernel.azureservice.Client;
import vehiclebc.AmbulanceDigitalTwin;
import vehiclebc.OperatorDigitalTwin;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DTOperator {
    @BeforeClass
    public static void createConnection() {
        Client.getClient();
    }

    @Test
    public void createOperator() {
        OperatorDigitalTwin.createOperator(TestDataValue.OPERATOR_RESOURCE);
        assertEquals(TestDataValue.EQUALS_DT, Client.getClient().getDigitalTwin(TestDataValue.OPERATOR_ID, BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);
    }

    @Test
    public void getOperators() {
        OperatorDigitalTwin.createOperator(TestDataValue.OPERATOR_RESOURCE);

        List<String> operators = OperatorDigitalTwin.getOperators();
        operators.forEach(System.out::println);
        assertTrue(operators.stream().anyMatch(s -> s.contains(TestDataValue.OPERATOR_ID)));
    }

    @Test
    public void getOperator() {
        OperatorDigitalTwin.createOperator(TestDataValue.OPERATOR_RESOURCE);
        Optional<String> operator = OperatorDigitalTwin.getOperator(TestDataValue.OPERATOR_ID);
        assertTrue(operator.isPresent());
        assertTrue(operator.get().contains(TestDataValue.OPERATOR_ID));
    }
}
