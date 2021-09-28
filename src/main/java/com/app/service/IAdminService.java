package com.app.service;

import com.app.pojos.Admin;

public interface IAdminService {
	
	Admin loginAdmin(String email,String password);
}
