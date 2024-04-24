package gestionpaiement.example.gestionpaiement.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    //private Date date;

    private int quantitecde ;

    @JsonIgnore
    @OneToMany(mappedBy = "panier")
    private List<Paiement> paiements;


    @OneToOne
    private Part_En parten;

    @JsonIgnoreProperties
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "panier_article", joinColumns = @JoinColumn(name = "panier_id"),inverseJoinColumns = @JoinColumn(name = "article_id"))
    private List<Article> articles = new ArrayList<>();

    public void addArticle(Article article) {
        articles.add(article);
        article.getPaniers().add(this);
    }


    // Méthode pour supprimer un article du panier
    public void removeArticle(Article article) {
        articles.remove(article);
        article.getPaniers().remove(this);
    }
    // Getter pour récupérer le partenId
    public Long getPartenId() {
        if (parten != null) {
            return parten.getId();
        } else {
            return null;
        }
    }
    public void setPartenId(Long partenId) {
        if (parten == null) {
            parten = new Part_En(this);
        }
        parten.setId(partenId);
    }

}