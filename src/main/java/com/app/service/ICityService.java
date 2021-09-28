package com.app.service;

import java.util.List;

import com.app.pojos.City;

public interface ICityService {
City addCity(City transientCity);

List<City> allCityList();

List<City> cityListWithDealer();
long getCount();

City getCityById(int id);
}
