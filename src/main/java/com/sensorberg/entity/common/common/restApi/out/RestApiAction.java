package com.sensorberg.entity.common.common.restApi.out;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sensorberg.entity.common.BackendTimeframe;

import lombok.Data;

/**
 * Created by Andreas Dörner on 11.01.16.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RestApiAction {
    private String eid;
    private Integer trigger;
    private Integer delay;
    private List<String> beacons = new ArrayList<>();
    private Integer supressionTime;
    private Integer suppressionTime;
    private Map<String, Object> content;
    private Integer type;
    private List<BackendTimeframe> timeframes = new ArrayList<>();
    private Boolean sendOnlyOnce;
    private String typeString;
    private String deliverAt;
}
