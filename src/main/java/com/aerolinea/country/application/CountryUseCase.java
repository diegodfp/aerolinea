package com.aerolinea.country.application;

import java.util.List;

import com.aerolinea.country.domain.entity.Country;
import com.aerolinea.country.domain.service.CountryService;

public class CountryUseCase {
private final CountryService countryService;

    public CountryUseCase(CountryService countryService) {
        this.countryService = countryService;
    }

    public List<Country> getAllCountrys() {
        return countryService.getAllCountrys();
    }

    public Country getCountryById(String countryId) {
        return countryService.getCountryById(countryId);
    }

    
}
