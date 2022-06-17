package com.waiyanhtet.test;
/*
 * import static org.junit.jupiter.api.Assertions.*;
 * 
 * import java.util.ArrayList; import java.util.Arrays;
 * 
 * import org.junit.jupiter.api.BeforeAll; import
 * org.junit.jupiter.api.BeforeEach; import
 * org.junit.jupiter.api.MethodOrderer.*; import org.junit.jupiter.api.Order;
 * import org.junit.jupiter.api.Test; import
 * org.junit.jupiter.api.TestMethodOrder; import
 * com.waiyanhtet.connection.ConnectionManager; import
 * com.waiyanhtet.connection.DatabaseInitializer; import
 * com.waiyanhtet.dao.MessageDao; import com.waiyanhtet.dto.Message;
 * 
 * @TestMethodOrder(OrderAnnotation.class) class MessageDaoTest {
 * 
 * //Message message = new Message("Hello","Hello William");
 * 
 * MessageDao dao;
 * 
 * @BeforeAll static void setUpBeforeClass() throws Exception {
 * DatabaseInitializer.truncateTable("message"); }
 * 
 * @BeforeEach void setUp() throws Exception { dao = new
 * MessageDao(ConnectionManager.getInstance()); }
 * 
 * @Test
 * 
 * @Order(1) void testCreateMessage() { var result = dao.createMessage(message);
 * assertEquals(1, result.id()); }
 * 
 * @Test
 * 
 * @Order(2) void testFindByIdFound() { var result = dao.findById(1);
 * 
 * assertNotNull(result);
 * 
 * assertEquals(message.title(), result.title());
 * assertEquals(message.message(), result.message());
 * assertNotNull(result.date());
 * 
 * }
 * 
 * @Test
 * 
 * @Order(3) void testFindByIdNotFound() { var result = dao.findById(0);
 * assertNull(result); }
 * 
 * @Test
 * 
 * @Order(4) void testUpdateFound() { var title = "New Title"; var message =
 * "New Message";
 * 
 * var updateResult = dao.update(1,title, message);
 * assertEquals(1,updateResult);
 * 
 * var result = dao.findById(1);
 * 
 * assertNotNull(result);
 * 
 * assertEquals(title, result.title()); assertEquals(message, result.message());
 * assertNotNull(result.date()); }
 * 
 * @Test
 * 
 * @Order(5) void testUpdateNotFound() { var title = "New Title"; var message =
 * "New Message";
 * 
 * var updateResult = dao.update(3,title, message);
 * assertNotEquals(1,updateResult);
 * 
 * }
 * 
 * @Test
 * 
 * @Order(6) void testDeleteFound() {
 * 
 * var deleteResult = dao.delete(1); assertEquals(1,deleteResult);
 * 
 * var result = dao.findById(1); assertNull(result);
 * 
 * }
 * 
 * @Test
 * 
 * @Order(7) void testDeleteNotFound() {
 * 
 * var deleteResult = dao.delete(1); assertNotEquals(1,deleteResult);
 * 
 * var result = dao.findById(1); assertNull(result);
 * 
 * }
 * 
 * @Test
 * 
 * @Order(8) void testcreateMessageNotEmpty() { var messages = Arrays.asList(new
 * Message("Title 1", "Message 1"),new Message("Title 2", "Message 2"),new
 * Message("Title 3", "Message 3")); var list = dao.createMessages(messages);
 * assertNotNull(list); assertEquals(2,list.get(0).id());
 * assertEquals(3,list.get(1).id()); assertEquals(4,list.get(2).id());
 * 
 * }
 * 
 * @Test
 * 
 * @Order(9) void testcreateMessageEmpty() { var messages = new
 * ArrayList<Message>(); var list = dao.createMessages(messages);
 * 
 * assertTrue(list.isEmpty());
 * 
 * }
 * 
 * @Test
 * 
 * @Order(10) void testcreateMessageNull() { var list =
 * dao.createMessages(null); assertTrue(list.isEmpty());
 * 
 * }
 * 
 * }
 */