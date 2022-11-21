package application.hibernate.entities.composite;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import application.hibernate.entities.Artwork;
import application.hibernate.entities.Reviewer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This is implemented with a new ID (not a composite ID) since there can be
 * multiple reviews for the same artwork.
 * 
 * @see https://www.baeldung.com/jpa-many-to-many#1-modeling-relationship-attributes-1
 */
@Entity(name = "artwork_review")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ArtworkReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    private String summary;

    @Column(nullable = true)
    private String details;

    private boolean canBePublished;

    private Date createdAt;

    // --------- Relations --------- //
    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    Reviewer reviewer;

    @ManyToOne
    @JoinColumn(name = "artwork_id")
    Artwork artwork;

    // --------- Constructors --------- //
    public ArtworkReview(String summary, String details, boolean canBePublished, Reviewer reviewer, Artwork artwork) {
        this.summary = summary;
        this.details = details;
        this.canBePublished = canBePublished;
        this.reviewer = reviewer;
        this.artwork = artwork;
        this.createdAt = new Date();
    }
}
