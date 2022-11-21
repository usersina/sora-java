package application.hibernate.entities.composite;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import org.hibernate.annotations.Type;

import application.hibernate.entities.Artwork;
import application.hibernate.entities.Audio;
import application.hibernate.entities.Book;
import application.hibernate.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "user_collection")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserCollection {
    @EmbeddedId
    private UserCollectionKey id;

    private Date boughtAt;

    @Column(nullable = true)
    private Date lastSeenAt;

    /**
     * Relevant only if artwork is an Audio, in milliseconds. This is set to
     * Integer so that it can be NULL in the database.
     */
    @Column(nullable = true)
    @Type(type = "int")
    private Integer durationCheckpoint;

    /**
     * Relevant only if artwork is a Book. This is set to Integer so that it
     * can be NULL in the database.
     */
    @Column(nullable = true)
    @Type(type = "int")
    private Integer pageCheckpoint;

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
    public UserCollection(Date boughtAt, User user, Artwork artwork) {
        this.id = new UserCollectionKey(user.getId(), artwork.getId());
        this.boughtAt = boughtAt;
        this.user = user;
        this.artwork = artwork;
        if (this.artwork instanceof Book) {
            this.pageCheckpoint = 0;
        } else if (this.artwork instanceof Audio) {
            this.durationCheckpoint = 0;
        }
    }
}
