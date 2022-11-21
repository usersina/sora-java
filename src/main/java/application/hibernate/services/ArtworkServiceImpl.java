package application.hibernate.services;

import java.util.List;

import application.hibernate.entities.Artwork;
import application.hibernate.repos.ArtworkRepository;

public class ArtworkServiceImpl implements ArtworkService {
    ArtworkRepository artworkRepository = new ArtworkRepository();

    @Override
    public List<Artwork> getAllArtworks() {
        return artworkRepository.findAll();
    }

    @Override
    public List<Artwork> getPublishedArtworks() {
        return artworkRepository.findAllPublished();
    }

    @Override
    public List<Artwork> getRecentlyPublishedArtworks(int limit) {
        return artworkRepository.findRecentlyPublished(limit);
    }
}
