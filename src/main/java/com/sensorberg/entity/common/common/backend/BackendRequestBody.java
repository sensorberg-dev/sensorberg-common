package com.sensorberg.entity.common.common.backend;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * Created by Andreas Dörner on 06.06.16.
 */
@JsonInclude(value=JsonInclude.Include.NON_EMPTY)
@Data
public class BackendRequestBody {
    private Date deviceTimestamp;
    private List<BackendEvent> events = new ArrayList<>();
    private List<BackendAction> actions = new ArrayList<>();
}



