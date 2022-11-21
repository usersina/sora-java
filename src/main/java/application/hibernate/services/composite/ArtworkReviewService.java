package application.hibernate.services.composite;

import java.util.List;

import application.hibernate.entities.composite.ArtworkReview;

public interface ArtworkReviewService {
    ArtworkReview saveArtworkReview(ArtworkReview artworkRating);

    List<ArtworkReview> getAllArtworkReviews();
}
