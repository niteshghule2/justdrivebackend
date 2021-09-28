package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Integer>{

Customer  findByEmailAndPassword(String email, String password);

Customer findByEmail(String email);
//	@Query("select c from Customer c where c.email = :email and c.password =: password")
//	public Customer loginCustomer(@Param("email") String email,@Param("password") String password);

}
