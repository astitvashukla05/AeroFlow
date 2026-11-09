package com.aeroflow.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.aeroflow.mapper.AirportMapper;
import com.aeroflow.model.Airport;
import com.aeroflow.model.City;
import com.aeroflow.payload.request.AirportRequest;
import com.aeroflow.payload.response.AirportResponse;
import com.aeroflow.repository.AirportRepo;
import com.aeroflow.repository.CityRepo;
import com.aeroflow.service.AirportService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {

    private final AirportRepo airportRepo;
    private final CityRepo cityRepo;

    // CREATE AIRPORT
    @Override
    public AirportResponse createAirport(AirportRequest airportRequest) {

        if (airportRepo.existsByIataCode(airportRequest.getIataCode())) {
            throw new RuntimeException("Airport already exists");
        }

        City city = cityRepo.findById(airportRequest.getCityId())
                .orElseThrow(() -> new RuntimeException("City not found"));

        Airport airport = AirportMapper.toEntity(airportRequest);
        airport.setCity(city);

        Airport savedAirport = airportRepo.save(airport);

        return AirportMapper.toResponse(savedAirport);
    }

    // GET AIRPORT BY ID
    @Override
    public AirportResponse getAirportById(Long airportId) {

        Airport airport = airportRepo.findById(airportId)
                .orElseThrow(() -> new RuntimeException("Airport not found"));

        return AirportMapper.toResponse(airport);
    }

    // GET ALL AIRPORTS
    @Override
    public List<AirportResponse> getAllAirports() {

        return airportRepo.findAll()
                .stream()
                .map(AirportMapper::toResponse)
                .collect(Collectors.toList());
    }

    // UPDATE AIRPORT
    @Override
    public AirportResponse updateAirport(Long id, AirportRequest airportRequest) {

        Airport existingAirport = airportRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Airport not found"));

        // Check whether the new IATA code is already used
        if (airportRequest.getIataCode() != null
                && !airportRequest.getIataCode().equals(existingAirport.getIataCode())
                && airportRepo.existsByIataCode(airportRequest.getIataCode())) {

            throw new RuntimeException("Airport with given IATA code already exists");
        }

        // Update city relationship if cityId is provided
        if (airportRequest.getCityId() != null) {

            City city = cityRepo.findById(airportRequest.getCityId())
                    .orElseThrow(() -> new RuntimeException("City not found"));

            existingAirport.setCity(city);
        }

        AirportMapper.updateEntity(airportRequest, existingAirport);

        Airport updatedAirport = airportRepo.save(existingAirport);

        return AirportMapper.toResponse(updatedAirport);
    }

    // DELETE AIRPORT
    @Override
    public String deleteAirport(Long id) {

        Airport airport = airportRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Airport does not exist"));

        airportRepo.delete(airport);

        return "Airport deleted successfully";
    }

    // GET AIRPORTS BY CITY ID
    @Override
    public List<AirportResponse> getAirportsByCityId(Long cityId) {

        return airportRepo.findByCity_Id(cityId)
                .stream()
                .map(AirportMapper::toResponse)
                .collect(Collectors.toList());
    }
}