package com.sensorberg.entity.common.common.restApi.out;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
class RestApiContent {
    private String subject;
    private String body;
    private Object payload;
    private String url;
}