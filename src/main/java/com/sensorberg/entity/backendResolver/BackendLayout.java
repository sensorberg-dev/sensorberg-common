package com.sensorberg.entity.backendResolver;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class BackendLayout {
    @JsonProperty("actions")
    private List<BackendAction> backendActions = new ArrayList<>();
    @JsonProperty("beacons")
    private List<BackendBeacon> backendBeacons = new ArrayList<>();
    private Integer requestedVersionId;
    private Long tillVersionId;
}