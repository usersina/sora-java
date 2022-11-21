package application.hibernate.services;

import java.util.List;

import application.hibernate.entities.Artwork;

public interface ArtworkService {
    List<Artwork> getAllArtworks();

    List<Artwork> getPublishedArtworks();

    List<Artwork> getRecentlyPublishedArtworks(int limit);
}
