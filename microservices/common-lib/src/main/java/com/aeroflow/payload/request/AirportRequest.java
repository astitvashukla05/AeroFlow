package com.aeroflow.payload.request;

import com.aeroflow.embeddable.Address;
import com.aeroflow.embeddable.GeoCode;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AirportRequest {

    @NotBlank(message = "IATA code is mandatory")
    @Size(min = 3, max = 3, message = "IATA code length must be 3")
    private String iataCode;

    @NotBlank(message = "Airport name is mandatory")
    private String name;
    @Valid
    private Address address;
    @Valid
    private GeoCode geoCode;
    private String timeZone;
    @NotNull(message = "City ID is mandatory")
    private Long cityId;

}
