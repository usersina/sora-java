package application.hibernate.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Either an AudioBook or a Podcast depending on the book reference
 */
@Entity
public class Audio extends Artwork {
    @Column
    private String description;

    /**
     * Duration of the audio in milliseconds
     */
    @Column
    private int duration;

    // --------- Relations --------- //
}
