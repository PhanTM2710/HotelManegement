package com.projectHotel.PhanLam.config;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.projectHotel.PhanLam.entity.Account;
import com.projectHotel.PhanLam.entity.Role;
import com.projectHotel.PhanLam.repository.IAccount;
import com.projectHotel.PhanLam.repository.IRole;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private IRole role;
	@Autowired
	private IAccount account;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
	       if (role.findByRole("ROLE_ADMIN") == null) {
	    	   role.save(new Role("ROLE_ADMIN"));
	        }

	        if (role.findByRole("ROLE_SALER") == null) {
	        	role.save(new Role("ROLE_SALER"));
	        }

	        // Admin account
	        if (account.findByUserName("phan123") == null) {
	            Account admin = new Account();
	            admin.setUserName("phan123");
	            admin.setPassword(passwordEncoder.encode("123456"));
	            HashSet<Role> roles = new HashSet<>();
	            roles.add(role.findByRole("ROLE_ADMIN"));
	            admin.setRole(roles);
	            account.save(admin);
	        }

	        // Member account
	        if (account.findByUserName("tam123") == null) {
	            Account admin = new Account();
	            admin.setUserName("tam123");
	            admin.setPassword(passwordEncoder.encode("123456"));
	            HashSet<Role> roles = new HashSet<>();
	            roles.add(role.findByRole("ROLE_SALER"));
	            admin.setRole(roles);
	            account.save(admin);
	        }
		
	}
	
	
}
