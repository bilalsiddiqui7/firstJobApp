package com.example.firstJobApp.Companies;

import java.util.List;

import com.example.firstJobApp.Reviews.Review;
import com.example.firstJobApp.jobs.Job;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String description;

	@JsonIgnore
	@OneToMany(mappedBy = "company")
	private List<Job> jobs;

	@OneToMany(mappedBy = "company")
	private List<Review> reviews;

	public Company() {
		super();
	}

	public Company(long id, String name, String description, List<Job> jobs, List<Review> reviews) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.jobs = jobs;
		this.reviews = reviews;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
