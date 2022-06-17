package com.waiyanhtet.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.waiyanhtet.connection.ConnectionManager;
import com.waiyanhtet.connection.MessageDBException;
import com.waiyanhtet.dto.Member;
import com.waiyanhtet.dto.Member.Role;
import com.waiyanhtet.utils.StringUtils;

public class MemberDao {

	ConnectionManager manager;

	public MemberDao(ConnectionManager manager) {
		super();
		this.manager = manager;
	}

	public int createMember(Member member) {

		if (null == member) {
			throw new IllegalArgumentException();
		}

		if (StringUtils.isEmpty(member.email())) {
			throw new MessageDBException("Email must not be empty.");
		}

		if (StringUtils.isEmpty(member.name())) {
			throw new MessageDBException("Member name must not be empty.");
		}

		if (StringUtils.isEmpty(member.password())) {
			throw new MessageDBException("Password must not be empty.");
		}

		if (null == member.dob()) {
			throw new MessageDBException("Date of birth must not be empty.");
		}

		var sql = "insert into member(email, name, password, dob, role) values (?,?,?,?,?)";
		try (var con = manager.getConnection(); var stmt = con.prepareStatement(sql)) {

			stmt.setString(1, member.email());
			stmt.setString(2, member.name());
			stmt.setString(3, member.password());
			stmt.setDate(4, Date.valueOf(member.dob()));
			stmt.setString(5, member.role().toString());

			return stmt.executeUpdate();

		} catch (SQLException e) {
			throw new MessageDBException("Email has already been used.");
		}
	}

	public Member findByEmail(String email) {
		if (StringUtils.isEmpty(email)) {
			throw new IllegalArgumentException();
		}

		try (var con = manager.getConnection();
				var stmt = con.prepareStatement("select * from member where email = ?")) {
			stmt.setString(1, email);

			var result = stmt.executeQuery();
			while (result.next()) {
				return new Member(result.getString(1), result.getString(2), result.getString(3),
						result.getDate(4).toLocalDate(), Role.valueOf(result.getString(5)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int changePassword(String email, String oldPass, String newPass) {

		if (StringUtils.isEmpty(email)) {
			throw new MessageDBException("Email must not be empty.");
		}

		if (StringUtils.isEmpty(oldPass)) {
			throw new MessageDBException("Old password must not be empty.");
		}

		if (StringUtils.isEmpty(newPass)) {
			throw new MessageDBException("New password must not be empty.");
		}

		if (oldPass.equals(newPass)) {
			throw new MessageDBException("New and old password are the password.");
		}

		try (var con = manager.getConnection();
				var stmt = con.prepareStatement("select * from member where email = ? ", ResultSet.TYPE_FORWARD_ONLY,
						ResultSet.CONCUR_UPDATABLE)) {

			stmt.setString(1, email);
			var result = stmt.executeQuery();

			if (result.next()) {
				if (!oldPass.equals(result.getString("password"))) {
					throw new MessageDBException("Please check your old password.");
				}

				result.updateString("password", newPass);
				result.updateRow();
				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new MessageDBException("Please check email.");
	}
}
