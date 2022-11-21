package application.hibernate.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    private String synopsis;

    @Column
    private int pagesNumber;

    // --------- Relations --------- //
    /**
     * This will cause a LazyInitializationException without the eager fetch.
     * 
     * @see https://stackoverflow.com/questions/22821695/how-to-fix-hibernate-lazyinitializationexception-failed-to-lazily-initialize-a
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "book_genre", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

    // --------- Constructors --------- //

    public Book(String title, double cost, String fileUrl, String coverImg, String synopsis, int pagesNumber) {
        super(title, cost, fileUrl, coverImg);
        this.synopsis = synopsis;
        this.pagesNumber = pagesNumber;
    }

    public Book(String title, double cost, String fileUrl, String coverImg, String synopsis, int pagesNumber,
            List<Genre> genres) {
        super(title, cost, fileUrl, coverImg);
        this.synopsis = synopsis;
        this.pagesNumber = pagesNumber;
        this.genres = genres;
    }

    public Book(String title, double cost, String fileUrl, String coverImg, User user, String synopsis, int pagesNumber,
            List<Genre> genres) {
        super(title, cost, fileUrl, coverImg, user);
        this.synopsis = synopsis;
        this.pagesNumber = pagesNumber;
        this.genres = genres;
    }
}
