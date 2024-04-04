package gestionpaiement.example.gestionpaiement.controller;

import gestionpaiement.example.gestionpaiement.model.Article;
import gestionpaiement.example.gestionpaiement.model.Panier;
import gestionpaiement.example.gestionpaiement.service.ArticleService;
import gestionpaiement.example.gestionpaiement.service.PanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    private  ArticleService articleService;
    @Autowired
    private PanierService panierService;
    // Ajouter un article au panier
    @PostMapping("{articleId}/{panierId}/ajouter-article")
    public ResponseEntity<Panier> ajouterArticleAuPanier(@PathVariable Long panierId, @PathVariable Long articleId) {
        // Récupérer le panier
        Panier panier = panierService.getPanierById(panierId);
        // Vérifier si le panier existe
        if (panier != null) {
            // Récupérer l'article
            Article article = articleService.getArticleById(articleId);
            // Vérifier si l'article existe
            if (article != null) {
                // Ajouter l'article au panier
                panier.addArticle(article);
                panierService.save(panier);
                return ResponseEntity.ok(panier);
            } else {
                // Retourner une réponse d'erreur si l'article n'existe pas
                return ResponseEntity.notFound().build();
            }
        } else {
            // Retourner une réponse d'erreur si le panier n'existe pas
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer un article du panier
    @DeleteMapping("/{panierId}/supprimer-article")
    public ResponseEntity<Panier> supprimerArticleDuPanier(@PathVariable Long panierId, @RequestBody Long articleId) {
        // Récupérer le panier
        Panier panier = panierService.getPanierById(panierId);

        // Récupérer l'article
        Article article = articleService.getArticleById(articleId);

        // Vérifier si le panier et l'article existent
        if (panier != null && article != null) {
            // Supprimer l'article du panier
            panier.removeArticle(article);
            panierService.save(panier);

            return ResponseEntity.ok(panier);
        } else {
            // Retourner une réponse d'erreur si le panier ou l'article n'existe pas
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        Article createdArticle = articleService.createArticle(article);
        return new ResponseEntity<>(createdArticle, HttpStatus.CREATED);
    }

    // Endpoint pour récupérer tous les articles
    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articles = articleService.getAllArticles();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    // Endpoint pour récupérer un article par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable long id) {
        return articleService.getArticleById(id)
                .map(article -> new ResponseEntity<>(article, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint pour mettre à jour un article
    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id, @RequestBody Article newArticleData) {
        Article updatedArticle = articleService.updateArticle(id, newArticleData);
        if (updatedArticle != null) {
            return new ResponseEntity<>(updatedArticle, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint pour supprimer un article
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id) {
        articleService.deleteArticle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
