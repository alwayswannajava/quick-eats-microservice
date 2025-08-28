package com.messageservice.function;

import com.messageservice.dto.CreateUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.function.Function;

@Configuration
@Slf4j
public class MessageFunctions {

    @Bean
    public Function<CreateUserDto, CreateUserDto> email() {
        return createUserDto -> {
            log.info("Sending email to user {}:", createUserDto);
            return createUserDto;
        };
    }
}
