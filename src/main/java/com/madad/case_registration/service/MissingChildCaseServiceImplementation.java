package com.madad.case_registration.service;

import com.madad.case_registration.domain.MissingChildCase;
import com.madad.case_registration.dto.RegisterCaseRequest;
import com.madad.case_registration.exception.ResourceNotFoundException;
import com.madad.case_registration.repository.IgpRepository;
import com.madad.case_registration.repository.MissingChildCaseRepository;
import com.madad.case_registration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@Transactional
public class MissingChildCaseServiceImplementation implements MissingChildCaseService {

    @Autowired
    private MissingChildCaseRepository missingchildcaseRepo;


    @Override
    public MissingChildCase createMissingChildCase(MissingChildCase missingChildCase){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy'T' HH:mm:ss");
        missingChildCase.setRegDateTime(LocalDateTime.parse(dtf.format(LocalDateTime.now()), dtf)); //LocalDateTime in desired format as LocalDateTime type
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
        //Dob
        try{
            missingChildCase.setDateOfBirth(LocalDate.parse(missingChildCase.getDateOfBirthInString(), formatter));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        //LastSeenDate
        try{
            missingChildCase.setLastSeenDate(LocalDate.parse(missingChildCase.getLastSeenDateInString(), formatter));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return missingchildcaseRepo.save(missingChildCase);
    }

    @Override
    public MissingChildCase updateMissingChildCase(MissingChildCase missingChildCase) {
        Optional<MissingChildCase> MissingChildCaseDb = this.missingchildcaseRepo.findById(missingChildCase.getId());

        if(MissingChildCaseDb.isPresent()) {
            MissingChildCase missingchildcaseUpdate = MissingChildCaseDb.get();
            missingchildcaseUpdate.setFirstName(missingChildCase.getFirstName());
            missingchildcaseUpdate.setLastName(missingChildCase.getLastName());
//        missingchildcase.setRegDate();
            missingchildcaseUpdate.setAge(missingChildCase.getAge());
            missingchildcaseUpdate.setEyeColor(missingChildCase.getEyeColor());
            missingchildcaseUpdate.setGender(missingChildCase.getGender());
            missingchildcaseUpdate.setHairColor(missingChildCase.getHairColor());
            missingchildcaseUpdate.setHeight(missingChildCase.getHeight());
            missingchildcaseUpdate.setWeight(missingChildCase.getWeight());
            missingchildcaseUpdate.setLanguage(missingChildCase.getLanguage());
            missingchildcaseUpdate.setLastSeenClothes(missingChildCase.getLastSeenClothes());
            missingchildcaseUpdate.setLastSeenProvince(missingChildCase.getLastSeenProvince());
            missingchildcaseUpdate.setLastSeenTime(missingChildCase.getLastSeenTime());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
            //Dob
            try{
                missingchildcaseUpdate.setDateOfBirth(LocalDate.parse(missingChildCase.getDateOfBirthInString(), formatter));
            }
            catch (Exception e){
                e.printStackTrace();
            }
            //LastSeenDate
            try{
                missingchildcaseUpdate.setLastSeenDate(LocalDate.parse(missingChildCase.getLastSeenDateInString(), formatter));
            }
            catch (Exception e){
                e.printStackTrace();
            }
//        missingchildcase.setPhoto(missingChildCase.getPhoto());
          return missingchildcaseUpdate;
        }else {
            throw new ResourceNotFoundException("Record not found with id : " + missingChildCase.getId());
        }
    }

    @Override
    public List<MissingChildCase> getAllMissingChildCase(long userId) {
        return this.missingchildcaseRepo.findByUserId(userId);
    }

    @Override
    public MissingChildCase getMissingChildCaseById(long missingChildCaseId) {
        Optional<MissingChildCase> MissingChildCaseDb = this.missingchildcaseRepo.findById(missingChildCaseId);

        if(MissingChildCaseDb.isPresent()) {
            return MissingChildCaseDb.get();
        }else {
            throw new ResourceNotFoundException("Record not found with id : " + missingChildCaseId);
        }
    }

    @Override
    public void deleteMissingChildCase(long missingChildCaseId) {
        Optional<MissingChildCase> MissingChildCaseDb = this.missingchildcaseRepo.findById(missingChildCaseId);

        if(MissingChildCaseDb.isPresent()) {
            this.missingchildcaseRepo.delete(MissingChildCaseDb.get());
        }else {
            throw new ResourceNotFoundException("Record not found with id : " + missingChildCaseId);
        }

    }
}
