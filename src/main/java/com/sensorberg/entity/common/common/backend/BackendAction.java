package com.sensorberg.entity.common.common.backend;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class BackendAction {

    private Boolean active;
    private String apiKey;
    private String companyId;
    private Integer deliverAfter;
    private Object deliverAfter;
    private Date deliverAt;
    private Boolean sendOnlyOnce;
    private String id;
    private Object name;
    private Map<String, Object> payload;
    private Integer suppressionTime;
    private List<BackendTimeframe> timeframes = new ArrayList<>();
    private Boolean triggerEntry;
    private Boolean triggerExit;
    private Integer type;
}