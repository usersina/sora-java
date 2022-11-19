package application.hibernate.services;

import java.util.List;

import application.hibernate.entities.Reviewer;
import application.hibernate.repos.ReviewerRepository;

public class ReviewerServiceImpl implements ReviewerService {
    ReviewerRepository reviewerRepository = new ReviewerRepository();

    @Override
    public Reviewer saveReviewer(Reviewer reviewer) {
        return reviewerRepository.save(reviewer);
    }

    @Override
    public List<Reviewer> getAllReviewers() {
        return reviewerRepository.findAll();
    }
}
