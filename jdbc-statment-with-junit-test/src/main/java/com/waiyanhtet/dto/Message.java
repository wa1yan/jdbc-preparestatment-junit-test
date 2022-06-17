package com.waiyanhtet.dto;

import java.time.LocalDate;

public record Message(int id, String email, String title, String message, LocalDate postAt, Member member) {

	public Message(String email, String title, String message, Member member) {
		this(0, email, title, message, null, member);
	}

	public Message CloneWithId(int id) {
		return new Message(id, this.email, this.title, this.message, this.postAt, this.member);
	}
}
