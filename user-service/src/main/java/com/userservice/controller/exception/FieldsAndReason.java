package com.userservice.controller.exception;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class FieldsAndReason {
    String fieldName;
    String reason;
}
