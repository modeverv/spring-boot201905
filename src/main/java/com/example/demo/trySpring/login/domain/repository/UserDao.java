package com.example.demo.trySpring.login.domain.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.demo.trySpring.login.domain.model.User;

public interface UserDao {
	public int count() throws DataAccessException;

	public int insertOne(User user) throws DataAccessException;

	public User selectOne(String userId) throws DataAccessException;

	public List<User> selectMany() throws DataAccessException;

	public int updateOne(User use) throws DataAccessException;

	public int deleteOne(String userId) throws DataAccessException;

	public void userCsvOut() throws DataAccessException;
}
