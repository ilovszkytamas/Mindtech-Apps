package com.MindtechApps.MindtechApps.dto;

import com.MindtechApps.MindtechApps.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {

    //@NotNull
    private Long id;

    //@NotEmpty(message = "Email must not be empty")
    //@Email(message = "Invalid email format", regexp = "^(.+)@(\\S+)$")
    private String email;
    private String userName;
    private Role role;
}
