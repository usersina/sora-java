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
     * Duration of the audio in seconds
     */
    @Column
    private int duration;

    // --------- Relations --------- //
    @OneToOne(optional = true)
    private Book book;

    // --------- Constructors --------- //

    /**
     * AudioBook constructor
     * 
     * @param cost
     * @param fileUrl
     * @param description
     * @param duration
     * @param book
     */
    public Audio(String title, double cost, String fileUrl, String coverImg, String description, int duration,
            Book book) {
        super(title, cost, fileUrl, coverImg);
        this.description = description;
        this.duration = duration;
        this.book = book;
    }

    /**
     * Podcast constructor
     * 
     * @param cost
     * @param fileUrl
     * @param description
     * @param duration
     */
    public Audio(String title, double cost, String fileUrl, String coverImg, String description, int duration) {
        super(title, cost, fileUrl, coverImg);
        this.description = description;
        this.duration = duration;
    }
}
