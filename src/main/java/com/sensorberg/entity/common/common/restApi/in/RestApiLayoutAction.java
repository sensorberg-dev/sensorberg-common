package com.sensorberg.entity.common.common.restApi.in;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * Created by Andreas Dörner on 02.08.16.
 */
@Data
public class RestApiLayoutAction implements Serializable {
    String id;
    String eid;
    String pid;
    Date dt;
    String location;
    int trigger;
}
