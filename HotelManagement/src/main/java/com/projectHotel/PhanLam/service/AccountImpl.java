package com.projectHotel.PhanLam.service;


import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projectHotel.PhanLam.entity.Account;
import com.projectHotel.PhanLam.entity.Role;
import com.projectHotel.PhanLam.repository.IAccount;

@Service
public class AccountImpl implements UserDetailsService{
	
	@Autowired
	private IAccount account;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account accounts = account.findByUserName(username);
		 if (accounts == null) {
	            throw new UsernameNotFoundException("Account not found");
	        }
		 Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
	        Set<Role> roles = accounts.getRole();
	        for (Role role : roles) {
	            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
	        }

	        return new org.springframework.security.core.userdetails.User(
	        		accounts.getUserName(), accounts.getPassword(), grantedAuthorities);
	}
}
