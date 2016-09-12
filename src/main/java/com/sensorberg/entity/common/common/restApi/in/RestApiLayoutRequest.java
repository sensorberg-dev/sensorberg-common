package com.sensorberg.entity.common.common.restApi.in;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * Created by Andreas Dörner on 06.06.16.
 */
@JsonInclude(value=JsonInclude.Include.NON_EMPTY)
@Data
public class RestApiLayoutRequest implements Serializable {
    private String diRaw;
    private String deviceId;
    private String apiKey;
    private String geohash;
    private String geohashComputed;
    private String pid;
    private String remoteAddr;
    private String qos;
    private String etag;
    private RestApiLayoutRequestBody activity;
    private boolean largeApplication;
    private boolean invalid;
    private String advertisingIdentifier;
}