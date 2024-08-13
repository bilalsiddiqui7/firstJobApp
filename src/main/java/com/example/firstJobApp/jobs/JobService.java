package com.example.firstJobApp.jobs;

import java.util.List;
import java.util.Optional;

public interface JobService {

	List<Job> getAllJobs();

	Job getJobById(int id);

	Job createJob(Job job);

	boolean updateJob(int id, Job job);

	boolean deleteJob(int id);

}
