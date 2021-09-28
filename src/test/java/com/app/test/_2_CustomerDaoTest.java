package com.app.test;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.dao.CustomerRepository;
import com.app.pojos.Customer;
import com.app.service.EncryptPassword;

@SpringBootTest
public class _2_CustomerDaoTest {
	@Autowired
	CustomerRepository custRepo;
	
	@Autowired
	private EncryptPassword encryptPass;
	
	@Test
	void testAddNewCustomer() {
		List<Customer> customer=Arrays.asList(
				new Customer("ramesh", "nayar", "ramesh@gmail.com", encryptPass.encryptPassword("pass123"),8798989898L, " DL-1420110012345", "ramesh_image"),
				new Customer("swati", "sathe", "swati@gmail.com", encryptPass.encryptPassword("pass123"),7788994410L, " DL-3520110012345", "swati_image"),
				new Customer("sam", "cooper", "sam@gmail.com",encryptPass.encryptPassword("pass123"),6688997780L, " DL-1230110012222", "sam_image"),
				new Customer("saurabh", "jadhav", "saurabh@gmail.com",encryptPass.encryptPassword("pass123"),7934567000L, " DL-4580110012963", "sam_image"),
				new Customer("ishan", "shinde", "ishan@gmail.com", encryptPass.encryptPassword("pass123"),9634567891L, " DL-466610012963", "ishan_image"),
				new Customer("diya", "jain", "diya@gmail.com",encryptPass.encryptPassword("pass123"),8634567891L, " DL-852011001278", "diya_image"),
				new Customer("aditi", "shinde", "aditi@gmail.com", encryptPass.encryptPassword("pass123"),8834567891L, " DL-7890110012123", "adity_image"),
				new Customer("ashwin", "kumar", "ashwin@gmail.com", encryptPass.encryptPassword("pass123"),9934567891L, " DL-582011001200", "ashwin_image"),
				new Customer("avan", "patel", "avan@gmail.com", encryptPass.encryptPassword("pass123"),4568567891L, " DL-7890110012321", "avan_image"),
				new Customer("vaibhav", "shinde", "vaibhav@gmail.com", encryptPass.encryptPassword("pass123"),9834567891L, " DL-7550110012258", "vaibhav_image"),
				new Customer("arun", "oza", "arun@gmail.com", encryptPass.encryptPassword("pass123"),7894567891L, " DL-7740458900257", "arun_image"),
				new Customer("akshay", "bankar", "akshay@gmail.com", encryptPass.encryptPassword("pass123"),9988369800L, " DL-45780121203", "akshay_image"),
				new Customer("vishal", "jadhav", "vishal@gmail.com",encryptPass.encryptPassword("pass123"),9988552290L, " DL-1230121203", "vishal_image"),
				new Customer("sujay", "bankar", "sujay@gmail.com", encryptPass.encryptPassword("pass123"),9988552200L, " DL-45745781203", "sujay_image"),
				new Customer("suraj", "sathe", "suraj@gmail.com",encryptPass.encryptPassword("pass123"),4563217894L, " DL-458956234125", "suraj_image"),
				new Customer("anand", "tapase", "anand@gmail.com", encryptPass.encryptPassword("pass123"),7856126345L, " DL-6320128952", "anand_image"),
				new Customer("shashi", "khan", "shashi@gmail.com", encryptPass.encryptPassword("pass123"),7182937182L, " DL-4780121203", "shashi_image"),
				new Customer("chanda", "kumari", "chanda@gmail.com",encryptPass.encryptPassword("pass123"),8574965210L, " DL-63210121203", "chanda_image"),
				new Customer("bharat", "rahi", "bharat@gmail.com", encryptPass.encryptPassword("pass123"),4523522002L, " DL-7856121203", "bharat_image"),
				new Customer("dhara", "gaikwad", "dhara@gmail.com", encryptPass.encryptPassword("pass123"),5248552200L, " DL-55220121203", "dhara_image"),
				new Customer("devi", "patel", "devi@gmail.com", encryptPass.encryptPassword("pass123"),4582478595L, " DL-45785623121203", "devi_image"),
				new Customer("arjun", "reddy", "arjun@gmail.com", encryptPass.encryptPassword("pass123"),8528552200L, " DL-632121203", "arjun_image"),
				new Customer("kabir", "singh", "kabir@gmail.com", encryptPass.encryptPassword("pass123"),7895552200L, " DL-9632121203", "kabir_image"),
				new Customer("raghav", "bakshi", "raghav@gmail.com",encryptPass.encryptPassword("pass123"),5241552200L, " DL-85458121203", "raghav_image"),
				new Customer("rahul", "mandal", "rahul@gmail.com", encryptPass.encryptPassword("pass123"),9998566600L, " DL-85240121203", "rahul_image")
				);
		custRepo.saveAll(customer);

	}
	}


