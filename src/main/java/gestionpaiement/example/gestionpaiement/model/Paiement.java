package gestionpaiement.example.gestionpaiement.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double montant;
    private String statut;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "panier_id")
    private Panier panier;

    public void setStatut(String statut) {
        this.statut = statut;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 72aabd6a3e2c894c0d151a5549459112acf4f4ef
