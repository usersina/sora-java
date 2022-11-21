package application.hibernate.services.composite;

import java.util.List;

import application.hibernate.entities.composite.ArtworkRating;

public interface ArtworkRatingService {
    ArtworkRating saveArtworkRating(ArtworkRating artworkRating);

    List<ArtworkRating> getAllArtworkRatings();
}
