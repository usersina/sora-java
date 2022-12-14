package application.hibernate.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import application.hibernate.entities.composite.ArtworkRating;
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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private double credit;

    @Column(nullable = true)
    private String phone;

    @Column(nullable = true)
    private String birthday;

    @Column(nullable = true)
    private String country;

    // --------- Relations --------- //
    @ManyToMany
    @JoinTable(name = "interests", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

    @ManyToOne
    private Designation designation;

    @OneToMany(mappedBy = "user"/* ,fetch = FetchType.EAGER, cascade = CascadeType.ALL */)
    private List<Artwork> artworks;

    @OneToMany(mappedBy = "user")
    private List<ArtworkRating> artworkRatings;

    @OneToMany(mappedBy = "user")
    private List<UserCollection> userCollections;

    // --------- Constructors --------- //
    public User(String firstName, String lastName, String email, String username, String password, double credit) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.credit = credit;
    }

    public User(String firstName, String lastName, String email, String username, String password, double credit,
            Designation designation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.credit = credit;
        this.designation = designation;
    }

    public User(String firstName, String lastName, String email, String username, String password, double credit,
            List<Genre> genres) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.credit = credit;
        this.genres = genres;
    }

    public User(String firstName, String lastName, String email, String username, String password, double credit,
            Designation designation,
            List<Genre> genres) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.credit = credit;
        this.designation = designation;
        this.genres = genres;
    }
}
