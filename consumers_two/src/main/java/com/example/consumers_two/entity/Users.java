package com.example.consumers_two.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Users {

    private String userCode;

    private String userName;

    private Long userRole;

    private String userPhone;

}
