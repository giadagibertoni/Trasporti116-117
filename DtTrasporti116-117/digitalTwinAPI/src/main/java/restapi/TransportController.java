package restapi;

import digitalTwinAPI.transport.TransportDigitalTwin;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransportController {
    @PostMapping("/API/Transport/createTransport")
    @ResponseBody
    public String createTransport(@RequestBody String resource) {
       return TransportDigitalTwin.createTransport(resource);
    }

    @GetMapping("/API/Transport/getScheduledTransports")
    @ResponseBody
    public List<String> getScheduledTransports() {
        return TransportDigitalTwin.getScheduledTransports();
    }
}