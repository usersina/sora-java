package application.hibernate.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Book extends Artwork {
    @Column
    private String title;

    @Column
    private String synopsis;

    @Column
    private int pagesNumber;

    // --------- Relations --------- //
}
