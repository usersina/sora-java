package application.hibernate.entities.composite;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ArtworkRatingKey implements Serializable {
    @Column(name = "user_id")
    Long userId;

    @Column(name = "artwork_id")
    Long artworkId;

    @Override
    public int hashCode() {
        return Long.hashCode(this.userId * 10 + this.artworkId);
    }

    @Override
    public boolean equals(Object other) {
        return this.userId == ((ArtworkRatingKey) other).userId
                && this.artworkId == ((ArtworkRatingKey) other).artworkId;
    }
}
