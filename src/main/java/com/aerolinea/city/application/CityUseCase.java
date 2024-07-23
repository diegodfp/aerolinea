package com.aerolinea.city.application;

import java.util.List;

import com.aerolinea.city.domain.entity.City;
import com.aerolinea.city.domain.service.CityService;

public class CityUseCase {
    private final CityService cityService;

    public CityUseCase(CityService cityService) {
        this.cityService = cityService;
    }

    
    public List<City> getAllModels( String idCountry){
        return cityService.getAllCitys(idCountry);
    }
    
    public City getCityById(String cityId) {
        return cityService.getCityById(cityId);
    }

}
