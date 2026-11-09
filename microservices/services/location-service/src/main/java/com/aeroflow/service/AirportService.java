package com.aeroflow.service;

import java.util.List;

import com.aeroflow.payload.request.AirportRequest;
import com.aeroflow.payload.response.AirportResponse;

public interface AirportService {

    AirportResponse createAirport(AirportRequest airportRequest);

    AirportResponse getAirportById(Long airportId);

    List<AirportResponse> getAllAirports();

    AirportResponse updateAirport(Long id, AirportRequest airportRequest);

    String deleteAirport(Long id);

    List<AirportResponse> getAirportsByCityId(Long cityId);

}
