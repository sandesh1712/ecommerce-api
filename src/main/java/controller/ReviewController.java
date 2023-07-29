package controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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


import DTO.ReviewDTO;
import entity.Review;
import service.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
	private ReviewService reviewService;
	
    private ModelMapper modelMapper;

	public ReviewController(ReviewService reviewService,ModelMapper modelMapper) {
		super();
		this.reviewService = reviewService;
		this.modelMapper = modelMapper;
	}

	@GetMapping
	public ResponseEntity<List<ReviewDTO>> getAllReviews() {
		List<Review> list = this.reviewService.findAll();
		return new ResponseEntity<>(this.convertListToDTO(list), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ReviewDTO> getReview(@PathVariable int id) {
		Review review = this.reviewService.findById(id);
		return new ResponseEntity<>(this.convertToDTO(review), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Review> createReview(@RequestBody Review Review) {
		Review newReview = this.reviewService.create(Review);
		return new ResponseEntity<Review>(newReview, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Review> updateReview(@PathVariable int id, @RequestBody Review review) {
		review.setId(id);
		Review newReview = this.reviewService.update(review);
		return new ResponseEntity<Review>(newReview, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity deleteUser(@PathVariable int id){
	   this.reviewService.delete(id);
	   return new ResponseEntity(HttpStatus.OK);
	};
	
	private List<ReviewDTO> convertListToDTO(List<Review> list){
		return list.stream().map(this::convertToDTO).collect(Collectors.toList());
	}
	
	private ReviewDTO convertToDTO(Review review){
	 ReviewDTO dto = modelMapper.map(review, ReviewDTO.class);
	 dto.getUser().setId(review.getUser().getId());
	 dto.getProduct().setId(review.getProduct().getId());
	 return dto;
    }
}
