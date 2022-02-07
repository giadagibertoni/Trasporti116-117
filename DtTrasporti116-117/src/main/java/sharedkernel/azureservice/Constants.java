/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package sharedkernel.azureservice;

/**
 * Class that contains a set of constants
 */
public final class Constants {
    private Constants() { }

    /**
     * Endpoint path string
     */
    public static final String ENDPOINT = "https://DTInstance116117.api.weu.digitaltwins.azure.net";

    /**
     * Tenant identifier stirng
     */
    public static final String TENANT_ID = "9a97eb40-e341-4a0f-8c5d-c6264663964c";

    /**
     * Client identifier string
     */
    public static final String CLIENT_ID = "f00ec15a-5310-4dcf-a766-77fcc1bcf40e";

    /**
     * Client secret
*/
    public static final String CLIENT_SECRET = "n5_i0mRm8TD6x725E~k2jgtTunx.f-lGzc";

    /**
     * IoT hub connection string
     */
    public static final String IOT_HUB_CONNECTION_STRING = "HostName=IoTHub116117.azure-devices.net;SharedAccessKeyName=iothubowner;SharedAccessKey=ajHdtr2d6QRliDGnpSe/FR/RVUWrM8fKjE5P12DpcaY=";

    /**
     * regisrty manager num max devices
     */
    public static final Integer MAX_DEVICES = 10000;


}

