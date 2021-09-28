package com.app.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.AdminRepository;
import com.app.pojos.Admin;
@Service
@Transactional
public class AdminServiceImpl implements IAdminService {

	@Autowired
	private AdminRepository adminRepo;

	@Override
	public Admin loginAdmin(String email, String password) {
		return adminRepo.findByEmailAndPassword(email, password);
	}

}
