package com.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CityRepository;
import com.app.pojos.Car;
import com.app.pojos.City;
import com.app.pojos.Dealer;

@Service
@Transactional
public class CityServiceImpl implements ICityService {

	@Autowired
	private CityRepository cityRepo;

	@Override
	public City addCity(City transientCity) {
		return cityRepo.save(transientCity);
	}

	@Override
	public List<City> allCityList() {
		return cityRepo.findAll();
	}

	@Override
	public long getCount() {
		return cityRepo.count();
	}

//	@Override
//	public List<City> cityListWithDealer() {
//		List<City> cityList=cityRepo.findAll();
//		List<City> cList=new ArrayList<City>();
//		for(City c:cityList) {
//			if(c.getDealerList().size()!=0)
//				cList.add(c);
//		}
//		return cList;
//		
//	}
	
	

	@Autowired
	private CarServiceImpl carServ;

	@Override
	public List<City> cityListWithDealer() {
		List<City> cityList = cityRepo.findAll();
		List<City> cList = new ArrayList<City>();
		for (City c : cityList) {
			loop: if (c.getDealerList().size() != 0) {
				for (Dealer d : c.getDealerList()) {
					if (carServ.getAllCarList(d.getId()).size() != 0) {
						for (Car cr : carServ.getAllCarList(d.getId())) {
							if (!cr.isCarStatus()) {
								cList.add(c);
								break loop;
							}
						}

					}
				}
			}
		}
		return cList;
	}

	@Override
	public City getCityById(int id) {
		return cityRepo.getById(id);
	}
}
