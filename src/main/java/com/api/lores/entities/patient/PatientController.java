package com.api.lores.entities.patient;

import com.api.lores.generic.GenericController;
import com.api.lores.generic.GenericService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patients")
public class PatientController extends GenericController<PatientModel> {

    final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @Override
    public GenericService<PatientModel> getService() {
        return patientService;
    }
}