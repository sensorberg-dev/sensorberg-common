package com.sensorberg.entity.common.common.restApi.out;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RestApiLayout {
    private List<String> accountProximityUUIDs = new ArrayList<>();
    private List<RestApiAction> actions = new ArrayList<>();
    // We need to keep this until it is removed from all old SDKs
    private Boolean currentVersion = false;
}
