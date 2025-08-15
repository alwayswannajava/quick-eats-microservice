package com.userservice.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.Map;
import java.util.List;

@ConfigurationProperties(prefix = "users")
@Getter
@Setter
public class UserContactInfoResponse {
    private String message;
    private Map<String, String> contactDetails;
    private List<String> onCallSupportNumbers;
}
