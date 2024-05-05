package gestionpaiement.example.gestionpaiement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @OneToMany(mappedBy = "parten", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> users;

    public Part_En(Panier panier) {
        this.panier = panier;
        this.users = new ArrayList<>();
    }
    public Part_En() {
        // Constructeur par défaut sans paramètres
    }

}
