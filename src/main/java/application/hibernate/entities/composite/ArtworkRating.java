package application.hibernate.entities.composite;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import application.hibernate.entities.Artwork;
import application.hibernate.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "artwork_rating")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ArtworkRating {
    @EmbeddedId
    private ArtworkRatingKey id;

    private int score;

    private String content;

    // --------- Relations --------- //
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("artworkId")
    @JoinColumn(name = "artwork_id")
    Artwork artwork;

    // --------- Constructors --------- //
    public ArtworkRating(int score, String content, User user, Artwork artwork) {
        this.id = new ArtworkRatingKey(user.getId(), artwork.getId());
        this.score = score;
        this.content = content;
        this.user = user;
        this.artwork = artwork;
    }
}
