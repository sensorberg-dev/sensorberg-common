package com.sensorberg.entity.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class BackendTimeframe {
    private String end;
    private String start;
}