package com.sensorberg.entity.common.common.restApi.in;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * Created by Andreas Dörner on 02.08.16.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class RestApiLayoutRequestBody implements Serializable {
    private Date deviceTimestamp;
    private List<RestApiLayoutEvent> events = new ArrayList<>();
    private List<RestApiLayoutAction> actions = new ArrayList<>();
    private List<RestApiLayoutConversion> conversions = new ArrayList<>();
}






