package com.AyurGo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AyurGo.entity.appointment;

public interface AppointmentRepo extends JpaRepository<appointment, Integer> {
	 public Long countById(Integer id);
}
