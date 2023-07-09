package com.example.irrigation.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class Status {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String text;

}
