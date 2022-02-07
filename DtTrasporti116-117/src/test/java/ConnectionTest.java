/*
 * Copyright (c) 2022. Giada Gibertoni
 */

import com.azure.digitaltwins.core.DigitalTwinsClient;
import sharedkernel.azureservice.Client;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConnectionTest {
    @Test
    public void testConnection() {
        assertEquals(TestDataValue.EQUALS_VALUE, Client.getClient().getClass(), DigitalTwinsClient.class);
    }
}
