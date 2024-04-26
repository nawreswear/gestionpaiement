package gestionpaiement.example.gestionpaiement.controller;
import gestionpaiement.example.gestionpaiement.model.Article;
import gestionpaiement.example.gestionpaiement.model.Panier;
import gestionpaiement.example.gestionpaiement.model.Part_En;
import gestionpaiement.example.gestionpaiement.service.ArticleService;
import gestionpaiement.example.gestionpaiement.service.PanierService;
import gestionpaiement.example.gestionpaiement.service.Part_EnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    private  ArticleService articleService;
    @Autowired
    private Part_EnService PartenService;
    @Autowired
    private PanierService panierService;

    @PostMapping("{articleId}/{panierId}/{partenId}/ajouter-article")
    @Transactional
    public ResponseEntity<?> ajouterArticleAuPanier(@PathVariable Long panierId, @PathVariable Long articleId, @PathVariable Long partenId) {
        // Récupérer le panier
        Panier panier = panierService.getPanierById(panierId);
        // Vérifier si le panier existe
        if (panier != null) {
            // Récupérer l'article
            Article article = articleService.getArticleById(articleId);
            // Vérifier si l'article existe
            if (article != null) {
                // Vérifier si la quantité dans le panier ne dépasse pas la quantité en stock dans l'article
                if (panier.getQuantitecde() <= article.getQuantiter()) {
                    // Ajouter l'article au panier
                    panier.addArticle(article);
                    // Augmenter la quantité commandée
                    panier.setQuantitecde(panier.getQuantitecde() + 1); // Mettre à jour la quantité
                    // Mettre à jour le partenId dans le panier
                    panier.setPartenId(partenId);
                    panier.setTotalP(article.getPrixvente() * panier.getQuantitecde()); // Mettre à jour le total
                    panierService.save(panier);
                    // Initialiser la collection paniers dans l'article
                    article.getPaniers().size();
                    return ResponseEntity.ok(panier);
                } else {
                    // si la quantité dans le panier dépasse la quantité en stock dans l'article
                    return ResponseEntity.badRequest().body("La quantité dans le panier dépasse la quantité disponible dans l'article.");
                }
            } else {
                // si l'article n'existe pas
                return ResponseEntity.notFound().build();
            }
        } else {
            // si le panier n'existe pas
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{panierId}/supprimer-article/{articleId}")
    @Transactional
    public ResponseEntity<Panier> supprimerArticleDuPanier(@PathVariable Long panierId, @PathVariable Long articleId) {
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
            // si le panier ou l'article n'existe pas
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        Article createdArticle = articleService.createArticle(article);
        return new ResponseEntity<>(createdArticle, HttpStatus.CREATED);
    }

    //  récupérer tous les articles
    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articles = articleService.getAllArticles();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    //  récupérer un article par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable long id) {
        return articleService.getArticleById(id)
                .map(article -> new ResponseEntity<>(article, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // mettre à jour un article
    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id, @RequestBody Article newArticleData) {
        Article updatedArticle = articleService.updateArticle(id, newArticleData);
        if (updatedArticle != null) {
            return new ResponseEntity<>(updatedArticle, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //  supprimer un article
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id) {
        articleService.deleteArticle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
