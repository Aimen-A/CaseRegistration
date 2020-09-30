package com.madad.case_registration.repository;

import com.madad.case_registration.domain.Igp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IgpRepository extends JpaRepository<Igp, Long> {
    public Igp findByProvince(String province);
}
