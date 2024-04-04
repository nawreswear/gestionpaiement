package gestionpaiement.example.gestionpaiement.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Panier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double TotalP ;

    private Date date;

    private int quantitecde ;
    @JsonIgnore
    @OneToMany(mappedBy = "panier")
    private List<Paiement> paiements;

    @OneToOne
    private Part_En parten;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "panier_article",
            joinColumns = @JoinColumn(name = "panier_id"),
            inverseJoinColumns = @JoinColumn(name = "article_id"))
    private List<Article> articles = new ArrayList<>();

    // Méthode pour ajouter un article au panier
    public void addArticle(Article article) {
        articles.add(article);
        article.getPaniers().add(this);
    }

    // Méthode pour supprimer un article du panier
    public void removeArticle(Article article) {
        articles.remove(article);
        article.getPaniers().remove(this);
    }
}
