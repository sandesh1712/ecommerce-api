package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.Review;
import exceptions.NotAllowedException;
import exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import repository.ReviewRepository;

@Service
@Transactional
public class ReviewService implements ServiceInterface<Review> {
	private ReviewRepository repo;

	@Autowired
	public ReviewService(ReviewRepository repo) {
		super();
		this.repo = repo;
	}

	@Override
	public List<Review> findAll() {
		return this.repo.findAll();
	}

	@Override
	public Review findById(int id) {
		Optional<Review> review = this.repo.findById(id);
		if (review.isEmpty())
			throw new NotFoundException("review Not Found!!");
		return review.get();
	}

	@Override
	public Review create(Review review) {
		if (review.getId() != null)
			throw new NotAllowedException("Id shouldn't be passed!");
		return this.repo.save(review);
	}

	@Override
	public Review update(Review review) {
		findById(review.getId());
		return this.repo.save(review);
	}

	@Override
	public void delete(int id) {
		Review review = this.findById(id);
		this.repo.delete(review);
	}
}
