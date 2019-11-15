package com.example.demo.trySpring.login.domain.service;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.trySpring.login.domain.model.User;
import com.example.demo.trySpring.login.domain.repository.UserDao;

@Transactional
@Service
public class UserService {

	@Autowired
	@Qualifier("UserDaoJdbcImpl")
	UserDao dao;

	public boolean insert(User user) {
		int rowNumber = dao.insertOne(user);
		boolean result = false;
		if (rowNumber > 0) {
			result = true;
		}
		return result;
	}

	public int count() {
		return dao.count();
	}

	public List<User> selectMany() {
		return dao.selectMany();
	}

	public User selectOne(String userId) {
		return dao.selectOne(userId);
	}

	public boolean updateOne(User u) {
		int rowNum = dao.updateOne(u);
		return rowNum > 0;
	}

	public boolean deleteOne(String userId) {
		int rowNum = dao.deleteOne(userId);
		return rowNum > 0;
	}

	public void userCsvOut() throws DataAccessException {
		dao.userCsvOut();
	}

	public byte[] getFile(String fileName) throws IOException {
		FileSystem fs = FileSystems.getDefault();
		Path p = fs.getPath(fileName);
		byte[] bytes = Files.readAllBytes(p);
		return bytes;
	}

}
