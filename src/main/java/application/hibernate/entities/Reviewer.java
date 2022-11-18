package application.hibernate.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

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

    // --------- Relations --------- //
}
