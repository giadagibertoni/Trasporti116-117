package openhostservice;

import patientbc.PatientDigitalTwin;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}