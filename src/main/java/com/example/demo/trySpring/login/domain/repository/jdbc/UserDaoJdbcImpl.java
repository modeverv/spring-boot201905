package com.example.demo.trySpring.login.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.trySpring.login.domain.model.User;
import com.example.demo.trySpring.login.domain.repository.UserDao;

@Repository("UserDaoJdbcImpl")
public class UserDaoJdbcImpl implements UserDao {

	@Autowired
	JdbcTemplate jdbc;

	@Autowired
	private NamedParameterJdbcTemplate npJdbc;

	@Override
	public int count() throws DataAccessException {
		return jdbc.queryForObject("SELECT COUNT(*) FROM m_user", Integer.class);
	}

	@Override
	public int insertOne(User user) throws DataAccessException {
		int rowNumber = jdbc.update(
				"INSERT INTO m_user(user_id," + " password, " + " user_name, " + " birthday, " + " age, "
						+ " marriage, " + " role) " + " VALUES (?,?,?,?,?,?,?)",
				user.getUserId(), user.getPassword(), user.getUserName(), user.getBirthday(), user.getAge(),
				user.isMarriage(), user.getRole());
		return rowNumber;
	}

	@Override
	public User selectOne(String userId) throws DataAccessException {
		Map<String, Object> params = new HashMap<>();
		params.put("userId", userId);
		String sql = "SELECT * FROM m_user WHERE user_id = :userId";
		List<User> list = npJdbc.query(sql, params, new BeanPropertyRowMapper<User>(User.class));
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public List<User> selectMany() throws DataAccessException {
		List<Map<String,Object>> getList = jdbc.queryForList("SELECT * FROM m_user");
		List<User> userList = new ArrayList<>();
		for (Map<String, Object> m : getList) {
			User u = new User();
			u.setUserId((String) m.get("user_id"));
			u.setPassword((String) m.get("password"));
			u.setUserName((String) m.get("user_name"));
			u.setBirthday((Date) m.get("birthday"));
			u.setAge((Integer) m.get("age"));
			u.setMarriage((Boolean) m.get("marriage"));
			u.setRole((String) m.get("role"));
			userList.add(u);
		}
		return userList;
	}

	@Override
	public int updateOne(User u) throws DataAccessException {
		String sql = ""
				+ " UPDATE M_USER "
				+ " SET password = :password, "
				+ " user_name = :userName, " + " birthday = :birthday, " + " age = :age, " + " marriage = :marriage "
				+ " WHERE user_id = :userId ";
		Map<String, Object> params = new HashMap<>();
		params.put("userId", u.getUserId());
		params.put("password", u.getPassword());
		params.put("userName", u.getUserName());
		params.put("birthday", u.getBirthday());
		params.put("age", u.getAge());
		params.put("marriage", u.isMarriage());
		int rowNum = npJdbc.update(sql, params);
		if (rowNum > 0) {
			// throw new DataAccessException("トランザクションテスト") {
			// };
		}
		return rowNum;
	}

	@Override
	public int deleteOne(String userId) throws DataAccessException {
		Map<String, Object> params = new HashMap<>();
		String sql = " DELETE FROM m_user WHERE user_id = :userId ";
		params.put("userId", userId);
		int rowNum = npJdbc.update(sql, params);
		return rowNum;
	}

	@Override
	public void userCsvOut() throws DataAccessException {
		String sql = "SELECT * FROM m_user";
		UserRowCallbackHandler handler = new UserRowCallbackHandler();
		jdbc.query(sql, handler);
	}
}
