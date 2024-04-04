package gestionpaiement.example.gestionpaiement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Part_En {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
     private  Panier panier;

    @JsonIgnore
    @OneToMany(mappedBy = "parten", cascade = CascadeType.ALL)
    private List<User> users;

    public Part_En() {

        this.users = new ArrayList<>();
    }
}
