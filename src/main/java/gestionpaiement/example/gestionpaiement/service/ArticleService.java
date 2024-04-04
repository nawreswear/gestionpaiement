package gestionpaiement.example.gestionpaiement.service;

import gestionpaiement.example.gestionpaiement.model.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    // Créer un article
    Article createArticle(Article article);
    Article getArticleById(Long articleId);

    // Lire tous les articles
    List<Article> getAllArticles();

    // Lire un article par son identifiant
    Optional<Article> getArticleById(long id);

    // Mettre à jour un article
    Article updateArticle(long id, Article newArticleData);

    // Supprimer un article
    void deleteArticle(long id);

    // Méthode pour ajouter un article au panier avec une quantité spécifiée
   // Lignepanier ajouterArticleAuPanier(Long articleId, int quantite);
}
