package com.madad.case_registration.repository;
import com.madad.case_registration.domain.MissingChildCase;
import com.madad.case_registration.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
