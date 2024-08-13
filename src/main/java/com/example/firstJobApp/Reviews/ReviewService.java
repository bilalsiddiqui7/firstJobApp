package com.example.firstJobApp.Reviews;

import java.util.List;

public interface ReviewService {

	List<Review> fetchAllReviewsByCompany(Long companyId);

	boolean addReview(Long companyId, Review review);

	Review getReviewById(Long companyId, Long reviewId);

	boolean updateReviewById(Long companyId, Long reviewId, Review review);

	boolean deleteReviewById(Long companyId, Long reviewId);

}
