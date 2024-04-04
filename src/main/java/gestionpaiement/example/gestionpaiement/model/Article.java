package gestionpaiement.example.gestionpaiement.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;

    @Column(length = 1000)
    private String photo;
    private String description;
    private int quantiter;

    private double prix;
    private boolean Livrable = false;
    private String statut;
    private double prixvente;

    @ManyToMany(mappedBy = "articles", cascade = CascadeType.ALL)
    private List<Panier> paniers = new ArrayList<>();

}
