package com.waiyanhtet.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import com.waiyanhtet.connection.ConnectionManager;
import com.waiyanhtet.connection.DatabaseInitializer;
import com.waiyanhtet.connection.MessageDBException;
import com.waiyanhtet.dao.MemberDao;
import com.waiyanhtet.dto.Member;
import com.waiyanhtet.dto.Member.Role;

@TestMethodOrder(OrderAnnotation.class)
class MemberDaoTest {

	MemberDao dao;
	static Member member;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		DatabaseInitializer.truncateTable("member");
		member = new Member("waiyanhtet@gmail.com", "WaiYanHtet", "welcome", LocalDate.of(1995, 4, 26), Role.Member);
	}

	@BeforeEach
	void setUp() throws Exception {
		dao = new MemberDao(ConnectionManager.getInstance());
	}

	@Test
	@Order(1)
	void testCreateMemberOK() {

		var result = dao.createMember(member);
		assertEquals(1, result);
	}

	@Test
	@Order(2)
	void testCreateMemberDuplicateKey() {

		var exception = assertThrows(MessageDBException.class, () -> dao.createMember(member));
		assertEquals("Email has already been used.", exception.getMessage());
	}

	@Test
	@Order(3)
	void testCreateMemberNull() {
		assertThrows(IllegalArgumentException.class, () -> dao.createMember(null));
	}

	@Test
	@Order(4)
	void testMemberEmailNull() {

		var data1 = new Member(null, "WaiYan", "welcome", LocalDate.of(1995, 4, 26), Role.Member);
		var exception1 = assertThrows(MessageDBException.class, () -> dao.createMember(data1));
		assertEquals("Email must not be empty.", exception1.getMessage());

		var data2 = new Member("", "WaiYan", "welcome", LocalDate.of(1995, 4, 26), Role.Member);
		var exception2 = assertThrows(MessageDBException.class, () -> dao.createMember(data2));
		assertEquals("Email must not be empty.", exception2.getMessage());
	}

	@Test
	@Order(5)
	void testMemberNameNull() {

		var data1 = new Member("waiyanhtet@gmail.com", null, "welcome", LocalDate.of(1995, 4, 26), Role.Member);
		var exception1 = assertThrows(MessageDBException.class, () -> dao.createMember(data1));
		assertEquals("Member name must not be empty.", exception1.getMessage());

		var data2 = new Member("waiyanhtet@gmail.com", "", "welcome", LocalDate.of(1995, 4, 26), Role.Member);
		var exception2 = assertThrows(MessageDBException.class, () -> dao.createMember(data2));
		assertEquals("Member name must not be empty.", exception2.getMessage());
	}

	@Test
	@Order(6)
	void testMemberPasswordNull() {

		var data1 = new Member("waiyanhtet@gmail.com", "WaiYanHtet", null, LocalDate.of(1995, 4, 26), Role.Member);
		var exception1 = assertThrows(MessageDBException.class, () -> dao.createMember(data1));
		assertEquals("Password must not be empty.", exception1.getMessage());

		var data2 = new Member("waiyanhtet@gmail.com", "WaiYanHtet", "", LocalDate.of(1995, 4, 26), Role.Member);
		var exception2 = assertThrows(MessageDBException.class, () -> dao.createMember(data2));
		assertEquals("Password must not be empty.", exception2.getMessage());
	}

	@Test
	@Order(7)
	void testMemberDOBNull() {

		var data1 = new Member("waiyanhtet@gmail.com", "WaiYanHtet", "welcome", null, Role.Member);
		var exception1 = assertThrows(MessageDBException.class, () -> dao.createMember(data1));
		assertEquals("Date of birth must not be empty.", exception1.getMessage());
	}

	@Test
	@Order(8)
	void testFindByEmail() {
		var result = dao.findByEmail(member.email());
		assertEquals(member, result);
	}

	@Test
	@Order(9)
	void testFindByEmailNotFound() {
		var result = dao.findByEmail("%s1".formatted(member.email()));
		assertNull(result);
	}

	@Test
	@Order(10)
	void testFindByEmailNull() {
		assertThrows(IllegalArgumentException.class, () -> dao.findByEmail(null));
	}

	@Test
	@Order(11)
	void testChangePassword() {
		var newPass = "newpassword";
		var result = dao.changePassword(member.email(), member.password(), newPass);
		assertEquals(1, result);

	}

	@Test
	@Order(12)
	void testChangePasswordNotFound() {
		var exception = assertThrows(MessageDBException.class,
				() -> dao.changePassword("test@gmail.com", "welcome", "newpass"));
		assertEquals("Please check email.", exception.getMessage());

	}

	@Test
	@Order(13)
	void testChangePasswordEmailNull() {
		var exception1 = assertThrows(MessageDBException.class, () -> dao.changePassword(null, "welcome", "newpass"));
		assertEquals("Email must not be empty.", exception1.getMessage());

		var exception2 = assertThrows(MessageDBException.class, () -> dao.changePassword("", "welcome", "newpass"));
		assertEquals("Email must not be empty.", exception2.getMessage());

	}

	@Test
	@Order(14)
	void testChangePasswordOldPassNull() {
		var exception1 = assertThrows(MessageDBException.class,
				() -> dao.changePassword(member.email(), null, "newpass"));
		assertEquals("Old password must not be empty.", exception1.getMessage());

		var exception2 = assertThrows(MessageDBException.class,
				() -> dao.changePassword(member.email(), "", "newpass"));
		assertEquals("Old password must not be empty.", exception2.getMessage());

	}

	@Test
	@Order(15)
	void testChangePasswordNewPassNull() {
		var exception1 = assertThrows(MessageDBException.class,
				() -> dao.changePassword(member.email(), member.password(), null));
		assertEquals("New password must not be empty.", exception1.getMessage());

		var exception2 = assertThrows(MessageDBException.class,
				() -> dao.changePassword(member.email(), member.password(), ""));
		assertEquals("New password must not be empty.", exception2.getMessage());

	}

	@Test
	@Order(16)
	void testChangePasswordUnmatchPasswords() {
		var exception1 = assertThrows(MessageDBException.class,
				() -> dao.changePassword(member.email(), "welcome", "newwelcome"));
		assertEquals("Please check your old password.", exception1.getMessage());

	}

	@Test
	@Order(17)
	void testChangePasswordSamePasswords() {
		var exception1 = assertThrows(MessageDBException.class,
				() -> dao.changePassword(member.email(), "newpassword", "newpassword"));
		assertEquals("New and old password are the password.", exception1.getMessage());

	}

}
