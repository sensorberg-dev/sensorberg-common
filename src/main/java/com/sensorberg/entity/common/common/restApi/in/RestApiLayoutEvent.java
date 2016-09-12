package com.sensorberg.entity.common.common.restApi.in;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * Created by Andreas Dörner on 02.08.16.
 */
@Data
public class RestApiLayoutEvent implements Serializable {
    private String pid;
    private Date dt;
    private int trigger;
    private String location;
}
