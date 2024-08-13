package com.example.firstJobApp.jobs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService {
	private JobRepository jobRepository;

	public JobServiceImpl(JobRepository jobRepository) {
		super();
		this.jobRepository = jobRepository;
	}

	@Override
	public Job createJob(Job job) {
		Job savedJob = (Job) jobRepository.save(job);
		return savedJob;
	}

	@Override
	public List<Job> getAllJobs() {
		// TODO Auto-generated method stub
		return jobRepository.findAll();
	}

	@Override
	public Job getJobById(int id) {
		return jobRepository.findById(id).orElse(null);
	}

	@Override
	public boolean updateJob(int id, Job job) {
		Optional<Job> JobToUpdate = jobRepository.findById(id);
		if (JobToUpdate.isPresent()) {
			Job updatedJob = JobToUpdate.get();
			updatedJob.setTitle(job.getTitle());
			updatedJob.setDescription(job.getDescription());
			updatedJob.setLocation(job.getLocation());
			updatedJob.setMinSalary(job.getMinSalary());
			updatedJob.setMaxSalary(job.getMaxSalary());
			jobRepository.save(updatedJob);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteJob(int id) {
		// We are using try catch because iof the id is not found it will throw null
		// pointer exception in that case we will send the response as id not found
		try {
			jobRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
