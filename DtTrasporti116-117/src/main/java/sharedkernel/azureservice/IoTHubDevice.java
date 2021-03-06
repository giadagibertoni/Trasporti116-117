/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package sharedkernel.azureservice;

import com.microsoft.azure.sdk.iot.service.Device;
import com.microsoft.azure.sdk.iot.service.RegistryManager;
import com.microsoft.azure.sdk.iot.service.exceptions.IotHubException;

import java.io.IOException;
public final class IoTHubDevice {

    public static void addAmbulanceGPSDevice(String ambulanceId) throws Exception
    {
        RegistryManager registryManager = RegistryManager.createFromConnectionString(Constants.IOT_HUB_CONNECTION_STRING);

        Device device = Device.createFromId(ambulanceId, null, null);

        if(registryManager.getDevices(Constants.MAX_DEVICES).stream().noneMatch(d -> d.getDeviceId().equals(ambulanceId))){
            try
            {
                device = registryManager.addDevice(device);

                System.out.println("Device created: " + device.getDeviceId());
                System.out.println("Device key: " + device.getPrimaryKey());
            }
            catch (IotHubException | IOException e)
            {

                e.printStackTrace();
            }
        }

        registryManager.close();
    }
}
