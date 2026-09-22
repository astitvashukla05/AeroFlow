package com.aeroflow.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityRequest {
    @NotBlank(message = "Name is required")
    @Size(max = 100)
    private String name;

    @NotBlank(message = "City Code is required")
    @Size(max = 10)
    private String cityCode;

    @NotBlank(message = "Country Code is required")
    @Size(max = 10)
    private String countryCode;

    @NotBlank(message = "Country Name is required")
    @Size(max = 100)
    private String countryName;

    @Size(max = 10)
    private String regionCode;

    @Size(max = 10)
    private String timeZoneOffset;

}
