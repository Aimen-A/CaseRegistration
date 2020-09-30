package com.madad.case_registration.repository;
import com.madad.case_registration.domain.MissingChildCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissingChildCaseRepository extends JpaRepository<MissingChildCase, Long> {
    public List<MissingChildCase> findByUserId(Long userId);
}
