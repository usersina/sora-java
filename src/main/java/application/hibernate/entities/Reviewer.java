package application.hibernate.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Reviewer extends User {
    @Column(nullable = true)
    private Date verifiedAt;

    // --------- Relations --------- //
}
