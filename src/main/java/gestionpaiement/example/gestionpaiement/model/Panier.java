package gestionpaiement.example.gestionpaiement.model;

import java.util.ArrayList;
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

    private Double totalP =0.0;


    private int quantitecde ;

    @JsonIgnore
    @OneToMany(mappedBy = "panier")
    private List<Paiement> paiements;


    @OneToOne
    private Part_En parten;

    @JsonIgnoreProperties("articles")
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "panier_article", joinColumns = @JoinColumn(name = "panier_id"),inverseJoinColumns = @JoinColumn(name = "article_id"))
    private List<Article> articles = new ArrayList<>();

    public void addArticle(Article article) {
        articles.add(article);
        article.getPaniers().add(this);
    }
    public Double getTotalP() {
        return totalP != null ? totalP : 0.0;
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
    // Méthode pour rechercher un article dans le panier_article pour un ID de panier spécifique
<<<<<<< HEAD
<<<<<<< HEAD
    public boolean containsArticle(Long articleId) {
=======
    public boolean containsArticle(Long panierId, Long articleId) {
>>>>>>> a2320ffb2e017f1e0b79c2c0685237b518442982
=======
    public boolean containsArticle(Long articleId) {
>>>>>>> e12daffcb4c3638f7c42dab513cbf9c7131a9573
        // Parcourir la liste des articles dans le panier
        for (Article article : articles) {
            // Vérifier si l'ID de l'article correspond à celui recherché
            if (article.getId().equals(articleId)) {
<<<<<<< HEAD
<<<<<<< HEAD
                return true; // L'article est trouvé dans le panier avec l'ID spécifié
=======
                // Vérifier si l'ID du panier associé à l'article correspond à celui recherché
                if (article.getPaniers().contains(panierId)) {
                    return true; // L'article est trouvé dans le panier avec l'ID spécifié
                }
>>>>>>> a2320ffb2e017f1e0b79c2c0685237b518442982
=======
                return true; // L'article est trouvé dans le panier avec l'ID spécifié
>>>>>>> e12daffcb4c3638f7c42dab513cbf9c7131a9573
            }
        }
        return false; // L'article n'est pas trouvé dans le panier avec l'ID spécifié
    }
<<<<<<< HEAD
<<<<<<< HEAD


=======
>>>>>>> a2320ffb2e017f1e0b79c2c0685237b518442982
=======


>>>>>>> e12daffcb4c3638f7c42dab513cbf9c7131a9573
}