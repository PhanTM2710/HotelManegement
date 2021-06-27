package com.projectHotel.PhanLam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectHotel.PhanLam.entity.Role;

public interface IRole extends JpaRepository<Role, Integer> {
	Role findByRole(String role);
}
