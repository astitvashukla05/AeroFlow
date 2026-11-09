package com.aeroflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aeroflow.model.Airport;

public interface AirportRepo extends JpaRepository<Airport, Long> {

    boolean existsByIataCode(String iataCode);

    List<Airport> findByCity_Id(Long cityId);
}