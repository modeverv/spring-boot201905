package com.example.demo.trySpring.login.domain.repository.jdbc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;

public class UserRowCallbackHandler implements RowCallbackHandler {

	@Override
	public void processRow(ResultSet rs) throws SQLException {
		try {
			File file = new File("sample.csv");
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			do {
				StringBuilder sb = new StringBuilder();
				sb.append(rs.getString("user_id") + ",");
				sb.append(rs.getString("password") + ",");
				sb.append(rs.getString("user_name") + ",");
				sb.append(rs.getDate("birthday") + ",");
				sb.append(rs.getBoolean("marriage") + ",");
				sb.append(rs.getString("role"));
				bw.write(sb.toString());
				bw.newLine();
			} while (rs.next());
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new SQLException();
		}
	}

}
