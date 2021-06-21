package com.projectHotel.PhanLam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectHotel.PhanLam.entity.CreditCard;
@Repository
public interface ICreditCard  extends JpaRepository<CreditCard, Integer>{
	CreditCard findByCardNumber(String cardNumber);
}
