package application.hibernate.entities.composite;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ArtworkReviewKey implements Serializable {
    @Column(name = "reviewer_id")
    Long reviewerId;

    @Column(name = "artwork_id")
    Long artworkId;

    @Override
    public int hashCode() {
        return Long.hashCode(this.reviewerId * 10 + this.artworkId);
    }

    @Override
    public boolean equals(Object other) {
        return this.reviewerId == ((ArtworkReviewKey) other).reviewerId
                && this.artworkId == ((ArtworkReviewKey) other).artworkId;
    }
}
