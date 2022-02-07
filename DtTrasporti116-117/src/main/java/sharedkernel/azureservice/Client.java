/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package sharedkernel.azureservice;

import com.azure.digitaltwins.core.DigitalTwinsClient;
import com.azure.digitaltwins.core.DigitalTwinsClientBuilder;
import com.azure.identity.ClientSecretCredentialBuilder;

/**
 * Reppresent the client for use Azure API
 */
public final class Client {
    private Client() { }
    private static DigitalTwinsClient dtClient = null;

    private static void createClient() {
        dtClient = new DigitalTwinsClientBuilder()
                .credential(
                        new ClientSecretCredentialBuilder()
                                .tenantId(Constants.TENANT_ID)
                                .clientId(Constants.CLIENT_ID)
                                .clientSecret(Constants.CLIENT_SECRET)
                                .build()
                )
                .endpoint(Constants.ENDPOINT)
                .buildClient();
       /* dtClient = new DigitalTwinsClientBuilder()
                .credential(
                        new DefaultAzureCredentialBuilder()
                                .build())
                .endpoint(Constants.ENDPOINT)
                .buildClient();*/
    }

    /**
     * @return the sync client
     */
    public static DigitalTwinsClient getClient() {
        if (dtClient == null) {
            createClient();
        }
        return dtClient;
    }

}
