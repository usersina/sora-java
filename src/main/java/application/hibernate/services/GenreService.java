package application.hibernate.services;

import java.util.List;

import application.hibernate.entities.Genre;

public interface GenreService {
    Genre saveGenre(Genre genre);

    List<Genre> getAllGenres();
}
