package openhostservice;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import patientbc.PatientDigitalTwin;
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
    public void setTransportInProgress(@RequestBody String id) {
        TransportDigitalTwin.setTransportInProgress(id);
    }

    @PostMapping("/API/Transport/setTransportCompleted")
    @ResponseBody
    public void setTransportCompleted(@RequestBody String id) {
        TransportDigitalTwin.setTransportCompleted(id);
    }

    @PostMapping("/API/Transport/setTransportDeleted")
    @ResponseBody
    public void setTransportDeleted(@RequestBody String id) {
        TransportDigitalTwin.setTransportDeleted(id);
    }

    @GetMapping("/API/Transport/getScheduledTransports")
    @ResponseBody
    public JSONArray getScheduledTransports() {
        JSONArray transports = new JSONArray();
        JSONParser parser = new JSONParser();
        TransportDigitalTwin.getScheduledTransports().forEach(t -> {
            try {
                transports.add(parser.parse(t));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        return transports;
    }

    @GetMapping("/API/Transport/getInProgressTransports")
    @ResponseBody
    public JSONArray getInProgressTransports() {
        JSONArray transports = new JSONArray();
        JSONParser parser = new JSONParser();
        TransportDigitalTwin.getInProgressTransports().forEach(t -> {
            try {
                transports.add(parser.parse(t));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        return transports;
    }

    @GetMapping("/API/Transport/getCompletedTransports")
    @ResponseBody
    public JSONArray getCompletedTransports() {
        JSONArray transports = new JSONArray();
        JSONParser parser = new JSONParser();
        TransportDigitalTwin.getCompletedTransports().forEach(t -> {
            try {
                transports.add(parser.parse(t));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        return transports;
    }

    @GetMapping("/API/Transport/getCancelledTransports")
    @ResponseBody
    public JSONArray getCancelledTransports() {
        JSONArray transports = new JSONArray();
        JSONParser parser = new JSONParser();
        TransportDigitalTwin.getCancelledTransports().forEach(t -> {
            try {
                transports.add(parser.parse(t));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        return transports;
    }

}