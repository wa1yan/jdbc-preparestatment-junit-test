package com.waiyanhtet.dao;

import java.lang.reflect.Member;
import java.util.List;

import com.waiyanhtet.connection.ConnectionManager;
import com.waiyanhtet.dto.Message;

public class MessageDao {

	ConnectionManager manager;

	public MessageDao(ConnectionManager manager) {
		super();
		this.manager = manager;
	}

	public Message createMessage(Message message) {
		return null;
	}

	public List<Message> search(String memberName, String keyword) {
		return null;
	}

	public List<Message> searchByMember(Member member) {
		return null;
	}

	public int save(Message message) {
		return 0;
	}

	public Message findById(int id) {
		return null;
	}

}
