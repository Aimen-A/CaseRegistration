package com.madad.case_registration.dto;

import com.madad.case_registration.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegisterCaseRequest {
    private User user;
}
