package application.hibernate.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Designation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private float bonusPerc;

    // --------- Relations --------- //
    @OneToMany(mappedBy = "designation")
    private List<User> users;

    // --------- Constructors --------- //
    public Designation(String name, String description, float bonusPerc) {
        this.name = name;
        this.description = description;
        this.bonusPerc = bonusPerc;
    }
}
