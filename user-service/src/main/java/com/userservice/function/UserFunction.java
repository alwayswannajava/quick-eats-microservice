package com.userservice.function;

import com.userservice.dto.message.CreateUserMessageDto;
import com.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class UserFunction {

    @Bean
    public Consumer<CreateUserMessageDto> updateCommunication(UserService userService) {
        return createUserMessageDto -> {
            log.info("Update communication status for user: {}", createUserMessageDto.email());
            userService.updateCommunicationStatus(createUserMessageDto);
        };
    }
}
