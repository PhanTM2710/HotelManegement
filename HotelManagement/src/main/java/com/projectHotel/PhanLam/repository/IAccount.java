package com.projectHotel.PhanLam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectHotel.PhanLam.entity.Account;

public interface IAccount extends JpaRepository<Account, Integer>{
	
}
