package com.riwi.demo.api.dto.response;

import java.util.List;

import com.riwi.demo.utils.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResp {
    private String user_id;
    private String username;
    private String email;
    private String full_name;
    private Role role;
    private List<CoursesToUsers> courses;
}
