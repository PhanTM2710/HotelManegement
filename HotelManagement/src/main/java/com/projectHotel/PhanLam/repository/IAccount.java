package com.projectHotel.PhanLam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectHotel.PhanLam.entity.Account;
@Repository
public interface IAccount extends JpaRepository<Account, Integer>{
	Account findByUserName(String userName);
}
