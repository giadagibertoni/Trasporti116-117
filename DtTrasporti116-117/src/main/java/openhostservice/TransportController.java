package openhostservice;

import transportbc.TransportDigitalTwin;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransportController {
    @PostMapping("/API/Transport/createTransport")
    @ResponseBody
    public String createTransport(@RequestBody String resource) {
       return TransportDigitalTwin.createTransport(resource);
    }

    @PostMapping("/API/Transport/setTransportInProgress")
    @ResponseBody
    public void setTransportInProgress(@RequestParam String id) {
        TransportDigitalTwin.setTransportInProgress(id);
    }

    @PostMapping("/API/Transport/setTransportCompleted")
    @ResponseBody
    public void setTransportCompleted(@RequestParam String id) {
        TransportDigitalTwin.setTransportCompleted(id);
    }

    @GetMapping("/API/Transport/getScheduledTransports")
    @ResponseBody
    public List<String> getScheduledTransports() {
        return TransportDigitalTwin.getScheduledTransports();
    }

    @GetMapping("/API/Transport/getInProgressTransports")
    @ResponseBody
    public List<String> getInProgressTransports() {
        return TransportDigitalTwin.getInProgressTransports();
    }

    @GetMapping("/API/Transport/getCompletedTransports")
    @ResponseBody
    public List<String> getCompletedTransports() {
        return TransportDigitalTwin.getCompletedTransports();
    }

}