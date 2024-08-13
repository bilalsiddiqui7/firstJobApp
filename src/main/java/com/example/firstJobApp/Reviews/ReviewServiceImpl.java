package com.example.firstJobApp.Reviews;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.firstJobApp.Companies.Company;
import com.example.firstJobApp.Companies.CompanyRepository;

@Service
public class ReviewServiceImpl implements ReviewService {

	private ReviewRepository reviewRepository;
	private CompanyRepository companyRepository;

	public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyRepository companyRepository) {
		super();
		this.reviewRepository = reviewRepository;
		this.companyRepository = companyRepository;
	}

	@Override
	public List<Review> fetchAllReviewsByCompany(Long companyId) {
		List<Review> allReviewsByCompany = reviewRepository.findByCompanyId(companyId);
		return allReviewsByCompany;
	}

	@Override
	public boolean addReview(Long companyId, Review review) {
		Optional<Company> company = companyRepository.findById(companyId);
		if (company.isPresent()) {
			review.setCompany(company.get());
			reviewRepository.save(review);
			return true;
		}
		return false;
	}

	@Override
	public Review getReviewById(Long companyId, Long reviewId) {
		Optional<Company> company = companyRepository.findById(companyId);
		Optional<Review> review = reviewRepository.findById(reviewId);
		if (company.isPresent() && review.isPresent()) {
			return review.get();
		}
		return null;
	}

	@Override
	public boolean updateReviewById(Long companyId, Long reviewId, Review review) {
		Optional<Company> company = companyRepository.findById(companyId);
		Optional<Review> reviewToUpdate = reviewRepository.findById(reviewId);
		if (company.isPresent() && reviewToUpdate.isPresent()) {
			review.setCompany(company.get());
			review.setId(reviewToUpdate.get().getId());
			reviewRepository.save(review);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteReviewById(Long companyId, Long reviewId) {
		Optional<Company> company = companyRepository.findById(companyId);
		Optional<Review> reviewToDelete = reviewRepository.findById(reviewId);

		if (company.isPresent() && reviewToDelete.isPresent()) {
			Long companyIdVerify = company.get().getId();
			Long CompanyIdOfReviewToDeleteVerify = reviewToDelete.get().getCompany().getId();
			if (companyIdVerify.equals(CompanyIdOfReviewToDeleteVerify)) {
				reviewRepository.deleteById(reviewId);
				return true;
			}
		}
		return false;
	}

}
