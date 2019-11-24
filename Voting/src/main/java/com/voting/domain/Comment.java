package com.voting.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Comment {
	private CommentId primaryKey;
	private String text;
	
	@EmbeddedId
	public CommentId getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(CommentId primaryKey) {
		this.primaryKey = primaryKey;
	}
	
	@Column(length=5000)
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
