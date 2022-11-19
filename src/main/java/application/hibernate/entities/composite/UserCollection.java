package application.hibernate.entities.composite;

import java.util.Date;

import javax.persistence.Column;
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

@Entity(name = "user_collection")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCollection {
    @EmbeddedId
    private UserCollectionKey id;

    private Date boughtAt;

    @Column(nullable = true)
    private Date lastSeenAt;

    /**
     * Relevant only if artwork is an Audio, in milliseconds
     */
    @Column(nullable = true)
    private int durationCheckpoint;

    /**
     * Relevant only if artwork is a Book
     */
    @Column(nullable = true)
    private int pageCheckpoint;

    // --------- Relations --------- //
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("artworkId")
    @JoinColumn(name = "artwork_id")
    Artwork artwork;
}
