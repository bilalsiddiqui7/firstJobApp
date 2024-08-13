package com.example.firstJobApp.Reviews;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies/{companyId}/reviews")
public class ReviewController {

	private ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		super();
		this.reviewService = reviewService;
	}

	@PostMapping
	public ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review review) {
		boolean isReviewAdded = reviewService.addReview(companyId, review);
		if (isReviewAdded)
			return new ResponseEntity<String>("Review added !", HttpStatus.OK);
		return new ResponseEntity<String>("Review not added, conmany does not exist !", HttpStatus.OK);

	}

	@GetMapping
	public ResponseEntity<List<Review>> getAllReviewsByCompany(@PathVariable Long companyId) {
		List<Review> allReviewsByCompany = reviewService.fetchAllReviewsByCompany(companyId);
		if (allReviewsByCompany != null)
			return new ResponseEntity<List<Review>>(allReviewsByCompany, HttpStatus.OK);
		else
			return new ResponseEntity(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/{reviewId}")
	public ResponseEntity<Review> getReviewById(@PathVariable Long companyId, @PathVariable Long reviewId) {
		Review review = reviewService.getReviewById(companyId, reviewId);
		if (review != null) {
			return new ResponseEntity<Review>(review, HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/{reviewId}")
	public ResponseEntity<String> updateReviewById(@PathVariable Long companyId, @PathVariable Long reviewId,
			@RequestBody Review review) {
		boolean isUpdated = reviewService.updateReviewById(companyId, reviewId, review);
		if (isUpdated)
			return new ResponseEntity<String>("Review updated !", HttpStatus.OK);
		return new ResponseEntity<String>("Review not updated !", HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{reviewId}")
	public ResponseEntity<String> deleteReviewById(@PathVariable Long companyId, @PathVariable Long reviewId) {
		boolean isDeleted = reviewService.deleteReviewById(companyId, reviewId);
		if (isDeleted)
			return new ResponseEntity<String>("Review deleted !", HttpStatus.OK);
		return new ResponseEntity<String>("Review not deleted !", HttpStatus.NOT_FOUND);
	}
}
