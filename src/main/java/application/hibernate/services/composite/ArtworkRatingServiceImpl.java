package application.hibernate.services.composite;

import java.util.List;

import application.hibernate.entities.composite.ArtworkRating;
import application.hibernate.repos.composite.ArtworkRatingRepository;

public class ArtworkRatingServiceImpl implements ArtworkRatingService {
    ArtworkRatingRepository artworkRatingRepository = new ArtworkRatingRepository();

    @Override
    public ArtworkRating saveArtworkRating(ArtworkRating artworkRating) {
        return artworkRatingRepository.save(artworkRating);
    }

    @Override
    public List<ArtworkRating> getAllArtworkRatings() {
        return artworkRatingRepository.findAll();
    }
}
