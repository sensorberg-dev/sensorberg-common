package com.sensorberg.entity.common.common.backend;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * Created by Andreas Dörner on 06.06.16.
 */
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
@Data
public class BackendEvent {
    private String pid;
    private Date dt;
    private int trigger;
    private String location;
}

