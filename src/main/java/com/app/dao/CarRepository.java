package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.pojos.Car;

public interface CarRepository extends JpaRepository<Car,Integer> {
	//List<Car> findByDealer(Dealer transientDealer);
	
	@Query("select c from Car c where c.dealer.id=:id")
	List<Car> findByDealerId(@Param("id") int id);
	
	@Query("select c from Car c where c.carType.id=:tid and c.dealer.city.id=:cid and c.carStatus=false")
	List<Car> findByCarTypeAndCity(@Param("tid") int tid,@Param("cid") int cid);
	
	@Query("select c from Car c where c.carStatus=false")
	List<Car> findByCarStatus();
	
	@Query("select c from Car c where c.dealer.id=:id and c.carStatus=false")
	List<Car> getCarsByStatusByDealers(@Param("id") int id);
}
