package com.app.test;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.dao.AdminRepository;
import com.app.pojos.Admin;
import com.app.service.EncryptPassword;

@SpringBootTest
public class _1_AdminDaoTest {
	
	@Autowired
	AdminRepository adminRepo;
	
	@Autowired
	private EncryptPassword encryptPass;
	@Test
	public void test() {
		List<Admin> admin=Arrays.asList(
				new Admin("jarvis", "jarvis@gmail.com", encryptPass.encryptPassword("pass123"))
				);
		adminRepo.saveAll(admin);
		
	}

}
