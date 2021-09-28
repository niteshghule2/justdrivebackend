package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Dealer;

public interface DealerRepository extends JpaRepository<Dealer,Integer> {
	Dealer findByEmailAndPassword(String email, String password);
}
