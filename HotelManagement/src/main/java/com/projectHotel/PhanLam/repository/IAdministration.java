package com.projectHotel.PhanLam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectHotel.PhanLam.entity.Administration;
@Repository
public interface IAdministration extends JpaRepository<Administration, Integer> {

}
