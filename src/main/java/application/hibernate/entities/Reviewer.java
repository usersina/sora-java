package application.hibernate.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import application.hibernate.entities.composite.ArtworkReview;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reviewer extends User {
    @Column(nullable = true)
    private Date verifiedAt;

    // --------- Relations --------- /
    @OneToMany(mappedBy = "reviewer")
    private List<ArtworkReview> artworkReviews;

    // --------- Constructors --------- //
    public Reviewer(String firstName, String lastName, String email, String username, String password, double credit) {
        super(firstName, lastName, email, username, password, credit);
    }

    public Reviewer(String firstName, String lastName, String email, String username, String password, double credit,
            Date verifiedAt) {
        super(firstName, lastName, email, username, password, credit);
        this.verifiedAt = verifiedAt;
    }
}
