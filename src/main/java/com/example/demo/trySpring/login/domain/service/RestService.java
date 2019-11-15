package com.example.demo.trySpring.login.domain.service;

import java.util.List;

import com.example.demo.trySpring.login.domain.model.User;

public interface RestService {
	public boolean insert(User u);

	public User selectOne(String userId);

	public List<User> selectMany();

	public boolean update(User u);

	public boolean delete(String userId);

}
