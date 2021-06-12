package com.projectHotel.PhanLam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectHotel.PhanLam.entity.Payment;

public interface IPayment extends JpaRepository<Payment, Integer> {

}
