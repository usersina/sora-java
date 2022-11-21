package application.hibernate.services.composite;

import java.util.List;

import application.hibernate.entities.composite.ArtworkReview;
import application.hibernate.repos.composite.ArtworkReviewRepository;

public class ArtworkReviewServiceImpl implements ArtworkReviewService {
    ArtworkReviewRepository artworkReviewRepository = new ArtworkReviewRepository();

    @Override
    public ArtworkReview saveArtworkReview(ArtworkReview artworkRating) {
        return artworkReviewRepository.save(artworkRating);
    }

    @Override
    public List<ArtworkReview> getAllArtworkReviews() {
        return artworkReviewRepository.findAll();
    }
}
