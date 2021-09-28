package com.app.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
@Service
@Transactional
public class EncryptPassword {
	private String encryptedpassword;

	public EncryptPassword() {
		super();
		this.encryptedpassword ="";
	}
	public String encryptPassword(String pass) {
		try   
        {  
            /* MessageDigest instance for MD5. */  
            MessageDigest m = MessageDigest.getInstance("MD5");  
              
            /* Add plain-text password bytes to digest using MD5 update() method. */  
            m.update(pass.getBytes());  
              System.out.println(m);
            /* Convert the hash value into bytes */   
            byte[] bytes = m.digest();  
              
            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */  
            StringBuilder s = new StringBuilder();  
            for(int i=0; i< bytes.length ;i++)  
            {  
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
            }  
              
            /* Complete hashed password in hexadecimal format */  
            encryptedpassword = s.toString();  
        }   
        catch (NoSuchAlgorithmException e)   
        {  
            e.printStackTrace();
//            System.out.println( e.printStackTrace());
        } 
		return encryptedpassword;
	}
}
