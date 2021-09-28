package com.app.test;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.dao.DealerRepository;
import com.app.pojos.Dealer;
import com.app.service.EncryptPassword;
import com.app.service.ICityService;

@SpringBootTest
public class _5_DealerDaoTest {
	@Autowired
	DealerRepository dealerRepo;
	@Autowired
	ICityService cityService;
	@Autowired
	private EncryptPassword encryptPass;

	@Test
	void testAddNewDealer() {
		List<Dealer> dealer = Arrays.asList(
				new Dealer("nitesh", "nitesh@gmail.com", encryptPass.encryptPassword("pass123"), 7734561245l, "pimpri", "18.2334", "73.5678",
						cityService.getCityById(1)),
				new Dealer("omkar", "omkar@gmail.com",  encryptPass.encryptPassword("pass123"), 7845127845l, "kothrud", "11.2334", "89.5678",
						cityService.getCityById(2)),
				new Dealer("ajit", "ajit@gmail.com", encryptPass.encryptPassword("pass123"), 967827845l, "katraj", "45.2334", "10.5678",
						cityService.getCityById(3)),
				new Dealer("dinesh", "dinesh@gmail.com", encryptPass.encryptPassword("pass123"), 957812458l, "Hinjawadi", "52.2334", "10.5678",
						cityService.getCityById(4)),
				new Dealer("pratul", "pratul@gmail.com", encryptPass.encryptPassword("pass123"), 887812789l, "Hinjawadi", "58.2334", "14.5678",
						cityService.getCityById(5))
		/*
		 * new Dealer("nilesh", "nilesh@gmail.com", "pass123", 456912789l, "mulshi",
		 * "72.2334", "63.5678", cityService.getCityById(6)), new Dealer("kumar",
		 * "kumar@gmail.com", "pass123", 987912789l, "ambegaon", "72.2334", "63.5678",
		 * cityService.getCityById(7)), new Dealer("rahul", "rahul@gmail.com",
		 * "pass123", 789912789l, "haweli", "82.2334", "93.5678",
		 * cityService.getCityById(8)), new Dealer("neha", "neha@gmail.com", "pass123",
		 * 987912789l, "khed", "82.2334", "23.5678", cityService.getCityById(9)), new
		 * Dealer("nancy", "nancy@gmail.com", "pass123", 887912789l, "shirur",
		 * "55.2334", "33.5678", cityService.getCityById(10))
		 */
		);
		dealerRepo.saveAll(dealer);

	}
}
