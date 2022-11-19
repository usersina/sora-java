package application.hibernate.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Either an AudioBook or a Podcast depending on the book reference
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Audio extends Artwork {
    @Column
    private String description;

    /**
     * Duration of the audio in milliseconds
     */
    @Column
    private int duration;

    // --------- Relations --------- //
    @OneToOne(optional = true)
    private Book book;
}
