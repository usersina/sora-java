package application.hibernate.entities.composite;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import application.hibernate.entities.Artwork;
import application.hibernate.entities.Reviewer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "artwork_review")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArtworkReview {
    @EmbeddedId
    private ArtworkReviewKey id;

    private String summary;

    @Column(nullable = true)
    private String details;

    private Date canBePublished;

    // --------- Relations --------- //
    @ManyToOne
    @MapsId("reviewerId")
    @JoinColumn(name = "reviewer_id")
    Reviewer reviewer;

    @ManyToOne
    @MapsId("artworkId")
    @JoinColumn(name = "artwork_id")
    Artwork artwork;
}
