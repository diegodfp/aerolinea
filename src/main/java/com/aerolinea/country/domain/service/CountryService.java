package com.aerolinea.country.domain.service;

import java.util.List;

import com.aerolinea.country.domain.entity.Country;

public interface CountryService {
    List<Country> getAllCountrys();

    Country getCountryById(String countryId);
}
