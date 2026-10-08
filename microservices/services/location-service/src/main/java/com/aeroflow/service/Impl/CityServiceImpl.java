package com.aeroflow.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aeroflow.mapper.CityMapper;
import com.aeroflow.model.City;
import com.aeroflow.payload.request.CityRequest;
import com.aeroflow.payload.response.CityResponse;
import com.aeroflow.repository.CityRepo;
import com.aeroflow.service.CityService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    @Autowired
    CityRepo cityRepo;

    @Override
    public CityResponse createCity(CityRequest request) {
        if (cityRepo.existsByCityCode(request.getCityCode())) {
            throw new RuntimeException("City already exists with give code");
        }
        City city = CityMapper.toEntity(request);

        cityRepo.save(city);
        CityResponse response = CityMapper.toResponse(city);
        return response;
    }

    @Override
    public CityResponse getCityById(Long id) {
        City city = cityRepo.findById(id).orElseThrow(
                () -> new RuntimeException("City does not exists"));
        CityResponse response = CityMapper.toResponse(city);
        return response;
    }

    @Override
    public CityResponse updateCity(Long id, CityRequest request) {

        City city = cityRepo.findById(id).orElseThrow(
                () -> new RuntimeException("City does not exists"));

        if (cityRepo.existsByCityCode(city.getCityCode())) {
            throw new RuntimeException("City with this code already exists");
        }
        city = CityMapper.updateEntity(city, request);
        CityResponse response = CityMapper.toResponse(city);
        return response;
    }

    @Override
    public void deleteCity(Long id) {
        City city = cityRepo.findById(id).orElseThrow(
                () -> new RuntimeException("City does not exists"));
        cityRepo.delete(city);
    }

    @Override
    public Page<CityResponse> getAllCities(Pageable pageable) {
        return cityRepo.findAll(pageable).map(CityMapper::toResponse);
    }

    @Override
    public Page<CityResponse> searchCities(String Keyword, Pageable pageable) {
        return cityRepo.searchByKeyword(Keyword, pageable).map(CityMapper::toResponse);
    }

    @Override
    public Page<CityResponse> getCitiesByCountryCode(String countryCode, Pageable pageable) {
        return cityRepo.findByCountryCodeIgnoreCase(countryCode, pageable).map(CityMapper::toResponse);
    }

    @Override
    public boolean cityExists(String cityCode) {
        return cityRepo.existsByCityCode(cityCode);
    }
}
