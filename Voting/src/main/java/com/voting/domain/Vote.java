package com.voting.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Vote {
	private VoteId primaryKey;
	private Boolean upvote;

	public Boolean getUpvote() {
		return upvote;
	}

	public void setUpvote(Boolean upvote) {
		this.upvote = upvote;
	}
	
	@EmbeddedId
	public VoteId getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(VoteId primaryKey) {
		this.primaryKey = primaryKey;
	}
	
}
