package application.hibernate.entities.composite;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class UserCollectionKey implements Serializable {
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
        return this.userId == ((UserCollectionKey) other).userId
                && this.artworkId == ((UserCollectionKey) other).artworkId;
    }
}
