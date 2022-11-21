package application.hibernate.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    // --------- Relations --------- //
    @ManyToMany(mappedBy = "genres")
    private List<User> users;

    @ManyToMany(mappedBy = "genres")
    private List<Book> books;

    // --------- Constructors --------- //
    public Genre(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
