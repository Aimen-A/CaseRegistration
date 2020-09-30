package com.madad.case_registration.controller;
import com.madad.case_registration.domain.Igp;
import com.madad.case_registration.exception.ResourceNotFoundException;
import com.madad.case_registration.repository.IgpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/madad")
public class IgpController {
    @Autowired
    private IgpRepository igpRepo;

    @GetMapping("/igp")
    public List<Igp> getAllIgp() {
        return igpRepo.findAll();
    }

    @GetMapping("/igp/{id}")
    public ResponseEntity<Igp> getIgpById(@PathVariable(value = "id") Long igpId)
            throws ResourceNotFoundException {
        Igp igp = igpRepo.findById(igpId)
                .orElseThrow(() -> new ResourceNotFoundException("Igp not found for this id :: " + igpId));
        return ResponseEntity.ok().body(igp);
    }

    @PostMapping("/igp")
    public Igp createIgp(@Valid @RequestBody Igp igp) {
        return igpRepo.save(igp);
    }

    @PutMapping("/igp/{id}")
    public ResponseEntity<Igp> updateIgp(@PathVariable(value = "id") Long igpId,
                                                   @Valid @RequestBody Igp igpDetails) throws ResourceNotFoundException {
        Igp igp = igpRepo.findById(igpId)
                .orElseThrow(() -> new ResourceNotFoundException("Igp not found for this id :: " + igpId));

        igp.setFirstName(igpDetails.getFirstName());
        igp.setLastName(igpDetails.getLastName());
        igp.setPassword(igpDetails.getPassword());
        igp.setProvince(igpDetails.getProvince());
        final Igp updatedIgp = igpRepo.save(igp);
        return ResponseEntity.ok(updatedIgp);
    }

    @DeleteMapping("/igp/{id}")
    public Map<String, Boolean> deleteIgp(@PathVariable(value = "id") Long igpId)
            throws ResourceNotFoundException {
        Igp igp = igpRepo.findById(igpId)
                .orElseThrow(() -> new ResourceNotFoundException("Igp not found for this id :: " + igpId));

        igpRepo.delete(igp);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
