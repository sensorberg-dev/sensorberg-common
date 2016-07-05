package com.sensorberg.entity.backend;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class BackendBeacon {

    private String actionId;
    private Boolean active;
    private String apiKey;
    private String applicationId;
    private String companyId;
    private String id;
    private Double latitude;
    private Double longitude;
    private Integer major;
    private Integer minor;
    private String name;
    private String proximityUUID;
}