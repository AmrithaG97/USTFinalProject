package com.ust.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.ust.model.Login;

public class LoginDao {

	JdbcTemplate template;

	public JdbcTemplate getTemplate() {
		return template;
	}

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	
	
	// Inserting new user
	public int saveUser(Login login) {
		String sql = "insert into LoginTable values('" + login.getUserId()
				+ "','" + login.getUsername() + "','" + login.getUserPassword()
				+ "')";
		return template.update(sql);
	}

	// updating existing user
	public int updateUser(Login login) {
		String sql = "update LoginTable set userName = '" + login.getUsername()
				+ "', userPassword = '" + login.getUserPassword() + "' "
				+ "where userId= '" + login.getUserId() + "'";
		return template.update(sql);
	}

	// Selecting all user
	public List<Login> getUser() {
		return template.query(
				"select userId,username,userPassword from LoginTable",
				new RowMapper<Login>() {
					public Login mapRow(ResultSet rs, int row)
							throws SQLException {
						Login login = new Login();
						login.setUserId(rs.getString(1));
						login.setUsername(rs.getString(2));
						login.setUserPassword(rs.getString(3));
						return login;
					}
				});
	}

}
