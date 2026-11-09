package com.aeroflow.payload.response;

import com.aeroflow.embeddable.Address;
import com.aeroflow.embeddable.GeoCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AirportResponse {

    private Long id;
    private String iataCode;
    private String detailedName;
    private String name;
    private Address address;
    private GeoCode geoCode;
    private String timeZone;
    private CityResponse city;
}
