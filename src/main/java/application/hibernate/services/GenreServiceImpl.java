package application.hibernate.services;

import java.util.List;

import application.hibernate.entities.Genre;
import application.hibernate.repos.GenreRepository;

public class GenreServiceImpl implements GenreService {
    GenreRepository genreRepository = new GenreRepository();

    @Override
    public Genre saveGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

}
