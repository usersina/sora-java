package application.hibernate.services;

import java.util.List;

import application.hibernate.entities.Reviewer;

public interface ReviewerService {
    Reviewer saveReviewer(Reviewer reviewer);

    List<Reviewer> getAllReviewers();
}
