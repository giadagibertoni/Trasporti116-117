package openhostservice;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import patientbc.PatientDigitalTwin;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PatientController {
    @PostMapping("/API/Patient/createPatient")
    @ResponseBody
    public String createPatient(@RequestBody String resource) {
       return PatientDigitalTwin.createPatient(resource);
    }

    @GetMapping("/API/Patient/getPatients")
    @ResponseBody
    public List<String> getPatients() {
        return PatientDigitalTwin.getPatients();
    }

    @GetMapping("/API/Patient/getPatient")
    @ResponseBody
    public String getPatient(@RequestParam String id) {
        if (PatientDigitalTwin.getPatient(id).isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "patient not found"
            );
        return PatientDigitalTwin.getPatient(id).get();
    }
}