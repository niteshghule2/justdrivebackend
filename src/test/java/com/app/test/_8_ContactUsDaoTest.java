package com.app.test;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.dao.ContactUsRepository;
import com.app.pojos.ContactUs;

@SpringBootTest
public class _8_ContactUsDaoTest {

	@Autowired
	ContactUsRepository contactUs;

	@Test
	public void test() {
		List<ContactUs> list = Arrays.asList(
				new ContactUs("sudhir", "sudhir@gmail.com", "Booking related", 
						"Is that ok to book 2 cars at a time"),
				new ContactUs("sham", "sham@gmail.com", "Car related", 
						"Do you have luxury cars"),
				new ContactUs("radhe", "radhe@gmail.com", "Admin related",
						"I have a car, can i join JustDrive family as dealer?"),
				new ContactUs("sumit", "sumit@gmail.com", "City Related",
						"Is Justdrive available in mumbai, or when will be available?"),
				new ContactUs("sakshi", "sakshi@gmail.com", "Payment Related",
						"How much will cost for 7 seater for almost 5 day trip?"),
				new ContactUs("rakhi", "rakhi@gmail.com", "Trip related", 
						"Want to know more about jjustdrive")
		);
		contactUs.saveAll(list);

	}
}
