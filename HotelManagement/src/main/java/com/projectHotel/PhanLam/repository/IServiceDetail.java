package com.projectHotel.PhanLam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectHotel.PhanLam.entity.ServiceDetail;
@Repository
public interface IServiceDetail extends JpaRepository<ServiceDetail, Integer> {

}
