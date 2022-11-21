package application.hibernate.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book extends Artwork {
    @Column
    private String title;

    @Column
    private String synopsis;

    @Column
    private int pagesNumber;

    // --------- Relations --------- //
    @ManyToMany
    @JoinTable(name = "book_genre", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

    // --------- Constructors --------- //

    public Book(double cost, String fileUrl, String coverImg, String title, String synopsis, int pagesNumber) {
        super(cost, fileUrl, coverImg);
        this.title = title;
        this.synopsis = synopsis;
        this.pagesNumber = pagesNumber;
    }

    public Book(double cost, String fileUrl, String coverImg, String title, String synopsis, int pagesNumber,
            List<Genre> genres) {
        super(cost, fileUrl, coverImg);
        this.title = title;
        this.synopsis = synopsis;
        this.pagesNumber = pagesNumber;
        this.genres = genres;
    }

    public Book(double cost, String fileUrl, String coverImg, User user, String title, String synopsis, int pagesNumber,
            List<Genre> genres) {
        super(cost, fileUrl, coverImg, user);
        this.title = title;
        this.synopsis = synopsis;
        this.pagesNumber = pagesNumber;
        this.genres = genres;
    }
}
