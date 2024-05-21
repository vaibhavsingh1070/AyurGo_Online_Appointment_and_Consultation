package com.AyurGo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AyurGo.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	public Long countById(Integer id);
	public User findByEmail(String emaill);

}
