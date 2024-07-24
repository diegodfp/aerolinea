package com.aerolinea.city.domain.service;

import java.util.List;

import com.aerolinea.city.domain.entity.City;

public interface CityService {
    
    List<City> getAllCitys(String idCountry);
    
    City getCityById(String cityId);
}
