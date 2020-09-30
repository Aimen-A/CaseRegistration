package com.madad.case_registration.controller;
import com.madad.case_registration.domain.MissingChildCase;
import com.madad.case_registration.domain.User;
import com.madad.case_registration.dto.RegisterCaseRequest;
import com.madad.case_registration.exception.ResourceNotFoundException;
import com.madad.case_registration.repository.IgpRepository;
import com.madad.case_registration.repository.UserRepository;
import com.madad.case_registration.service.MissingChildCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/madad")
public class MissingChildCaseController {
    @Autowired
    private MissingChildCaseService missingchildcaseService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IgpRepository igpRepository;

    @GetMapping("/user/{userId}/missingchildcase")
    public ResponseEntity<List<MissingChildCase>> getAllMissingChildCase(@PathVariable long userId){
        return ResponseEntity.ok().body(missingchildcaseService.getAllMissingChildCase(userId));
    }

    @GetMapping("/user/{userId}/missingchildcase/{id}")
    public ResponseEntity<MissingChildCase> getMissingChildCaseById(@PathVariable long id){
        return ResponseEntity.ok().body(missingchildcaseService.getMissingChildCaseById(id));
    }

    @PostMapping("/user/{userId}/missingchildcase")
    public ResponseEntity<MissingChildCase> createMissingChildCase(@RequestBody MissingChildCase childCase, @PathVariable(value = "userId") Long userId){
        childCase.setUser(userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId)));
        childCase.setIgp(igpRepository.findByProvince(childCase.getLastSeenProvince()));
        return ResponseEntity.ok().body(this.missingchildcaseService.createMissingChildCase(childCase));
    }

    @PutMapping("/user/{userId}/missingchildcase/{id}")
    public ResponseEntity<MissingChildCase> updateMissingChildCase(@PathVariable long id, @RequestBody MissingChildCase childCase, @PathVariable(value = "userId") Long userId){
        childCase.setId(id);
        childCase.setUser(userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId)));
        return ResponseEntity.ok().body(this.missingchildcaseService.updateMissingChildCase(childCase));
    }

    @DeleteMapping("/user/{userId}/missingchildcase/{id}")
    public HttpStatus deleteMissingChildCase(@PathVariable long id){
        this.missingchildcaseService.deleteMissingChildCase(id);
        return HttpStatus.OK;
    }
    
}
