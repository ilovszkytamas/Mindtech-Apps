package com.MindtechApps.MindtechApps.converter;

import com.MindtechApps.MindtechApps.dto.UserDTO;
import com.MindtechApps.MindtechApps.entity.User;
import org.springframework.core.convert.converter.Converter;

public class UserToUserDTOConverter implements Converter<User, UserDTO> {
    @Override
    public UserDTO convert(User source) {
        return UserDTO
            .builder()
            .id(source.getId())
            .email(source.getEmail())
            .userName(source.getUsername())
            .role(source.getRole())
            .build();
    }
}