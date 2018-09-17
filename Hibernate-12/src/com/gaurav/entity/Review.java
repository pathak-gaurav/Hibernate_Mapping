package com.gaurav.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "REVIEW")
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_id_generator")
	@SequenceGenerator(name = "review_id_generator", allocationSize = 1, initialValue = 401, sequenceName = "review_id_sequ")
	@Column(name = "REVIEW_ID")
	private int reviewId;

	@Column(name = "COMMENTS")
	private String comments;

	public Review() {
	}

	public Review(String comments) {
		this.comments = comments;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", comments=" + comments + "]";
	}
}
