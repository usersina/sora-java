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
import javax.persistence.OneToMany;

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
    private int id;

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

    @OneToMany(mappedBy = "user"/* ,fetch = FetchType.EAGER, cascade = CascadeType.ALL */)
    private List<Artwork> artworks;
}
