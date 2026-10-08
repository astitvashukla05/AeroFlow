package com.aeroflow.service.Impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aeroflow.payload.request.CityRequest;
import com.aeroflow.payload.response.CityResponse;
import com.aeroflow.service.CityService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    @Override
    public CityResponse createCity(CityRequest request) {

        throw new UnsupportedOperationException("Unimplemented method 'createCity'");
    }

    @Override
    public CityResponse getCityById(Long id) {

        throw new UnsupportedOperationException("Unimplemented method 'getCityById'");
    }

    @Override
    public CityResponse updateCity(Long id) {

        throw new UnsupportedOperationException("Unimplemented method 'updateCity'");
    }

    @Override
    public void deleteCity(Long id) {

        throw new UnsupportedOperationException("Unimplemented method 'deleteCity'");
    }

    @Override
    public Page<CityResponse> getAllCities(Pageable pageable) {

        throw new UnsupportedOperationException("Unimplemented method 'getAllCities'");
    }

    @Override
    public Page<CityResponse> searchCities(String Keyword, Pageable pageable) {

        throw new UnsupportedOperationException("Unimplemented method 'searchCities'");
    }

    @Override
    public Page<CityResponse> getCitiesByCountryCode(String countryCode, Pageable pageable) {

        throw new UnsupportedOperationException("Unimplemented method 'getCitiesByCountryCode'");
    }

    @Override
    public boolean cityExists(String cityCode) {

        throw new UnsupportedOperationException("Unimplemented method 'cityExists'");
    }

    @Override
    public boolean validateCityCode(String cityCode) {

        throw new UnsupportedOperationException("Unimplemented method 'validateCityCode'");
    }

}
