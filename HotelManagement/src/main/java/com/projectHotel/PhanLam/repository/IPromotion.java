package com.projectHotel.PhanLam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectHotel.PhanLam.entity.Promotion;
@Repository
public interface IPromotion extends JpaRepository<Promotion, Integer>{

		Promotion findByCode(String code);
}
