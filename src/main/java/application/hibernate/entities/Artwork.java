package application.hibernate.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import application.hibernate.entities.composite.ArtworkRating;
import application.hibernate.entities.composite.ArtworkReview;
import application.hibernate.entities.composite.UserCollection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class Artwork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String title;

    @Column
    private double cost;

    @Column
    private String fileUrl;

    @Column
    private String coverImg;

    @Column(nullable = true)
    private Date publishedAt;

    // --------- Relations --------- //
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "artwork")
    private List<UserCollection> userCollections;

    @OneToMany(mappedBy = "artwork")
    private List<ArtworkRating> artworkRatings;

    @OneToMany(mappedBy = "artwork")
    private List<ArtworkReview> artworkReviews;

    // --------- Constructors --------- //

    public Artwork(String title, double cost, String fileUrl, String coverImg) {
        this.title = title;
        this.cost = cost;
        this.fileUrl = fileUrl;
        this.coverImg = coverImg;
    }

    public Artwork(String title, double cost, String fileUrl, String coverImg, User user) {
        this.title = title;
        this.cost = cost;
        this.fileUrl = fileUrl;
        this.coverImg = coverImg;
        this.user = user;
    }
}
