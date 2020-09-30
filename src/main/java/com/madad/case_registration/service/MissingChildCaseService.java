package com.madad.case_registration.service;

import com.madad.case_registration.domain.MissingChildCase;

import java.util.List;

public interface MissingChildCaseService {
    MissingChildCase createMissingChildCase(MissingChildCase missingChildCase);

    MissingChildCase updateMissingChildCase(MissingChildCase missingChildCase);

    List<MissingChildCase> getAllMissingChildCase(long userId);

    MissingChildCase getMissingChildCaseById(long missingChildCaseId);

    void deleteMissingChildCase(long missingChildCaseId);
}
