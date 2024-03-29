package com.MindtechApps.MindtechApps.configuration;

import com.MindtechApps.MindtechApps.converter.OrderToOrderDTOConverter;
import com.MindtechApps.MindtechApps.converter.UserToUserDTOConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new UserToUserDTOConverter());
        registry.addConverter(new OrderToOrderDTOConverter());
    }
}
