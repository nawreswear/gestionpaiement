package gestionpaiement.example.gestionpaiement.controller;
import gestionpaiement.example.gestionpaiement.model.Article;
import gestionpaiement.example.gestionpaiement.model.Paiement;
import gestionpaiement.example.gestionpaiement.model.Panier;
import gestionpaiement.example.gestionpaiement.model.Part_En;
import gestionpaiement.example.gestionpaiement.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    private  ArticleService articleService;

    private PaiementService paiementService;

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
                // la quantité dans le panier ne dépasse pas la quantité en stock dans l'article
               // if (panier.getQuantitecde() <= article.getQuantiter()) {
                    // Ajouter l'article au panier
                    panier.addArticle(article);
                    // Augmenter la quantité commandée
                    panier.setQuantitecde(panier.getQuantitecde() + 1); // Mettre à jour la quantité
                    // Mettre à jour le partenId dans le panier
                    panier.setPartenId(partenId);
                    panier.setTotalP(article.getPrixvente() + panier.getTotalP()); // Mettre à jour le total
                    panierService.save(panier);
                    // Initialiser la collection paniers dans l'article
                    article.getPaniers().size();
                    return ResponseEntity.ok(panier);
                } else {
                    // la quantité dans le panier dépasse la quantité en stock dans l'article
                    return ResponseEntity.badRequest().body("La quantité dans le panier dépasse la quantité disponible dans l'article.");
                }
          /*  } else {
                //  l'article n'existe pas
                return ResponseEntity.notFound().build();
            }*/
        } else {
            // le panier n'existe pas
            return ResponseEntity.notFound().build();
        }
    }
    /*@DeleteMapping("/{panierId}/supprimer-article/{articleId}")
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
    }*/
    @DeleteMapping("/{panierId}/supprimer-article/{articleId}")
    @Transactional
    public ResponseEntity<Panier> supprimerArticleDuPanier(@PathVariable Long panierId, @PathVariable Long articleId) {
        // Récupérer le panier
        Panier panier = panierService.getPanierById(panierId);

        // Récupérer l'article
        Article article = articleService.getArticleById(articleId);

        // Vérifier si le panier et l'article existent
        if (panier != null && article != null) {
            // Vérifier si l'article existe dans le panier
            if (panier.containsArticle(articleId)) {
                // Supprimer l'article du panier
                panier.removeArticle(article);

                // Mettre à jour la quantitecde et le totalP pour l'article supprimé
                panier.setQuantitecde(panier.getQuantitecde() - 1);
                double articlePrice = article.getPrixvente();
                panier.setTotalP(panier.getTotalP() - articlePrice);

                // Si la quantité commandée devient 0, supprimer complètement le panier
                if (panier.getQuantitecde() == 0 && panier.getTotalP() == 0) {
                    panierService.deletePanier(panierId);
                    return ResponseEntity.ok().build();
                }

                // Enregistrer les modifications du panier
                panierService.save(panier);

                return ResponseEntity.ok(panier);
            } else {
                // L'article n'est pas trouvé dans le panier
                return ResponseEntity.notFound().build();
            }
        } else {
            // Si le panier ou l'article n'existe pas
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        Article createdArticle = articleService.createArticle(article);
        return new ResponseEntity<>(createdArticle, HttpStatus.CREATED);
    }

    @DeleteMapping("/panier/{id}")
    public ResponseEntity<String> viderPanier(@PathVariable Long id) {
        try {
            // Vérifier si le panier existe
            Optional<Panier> panierOptional = panierService.findById(id);
            if (panierOptional.isEmpty()) {
                return new ResponseEntity<>("Le panier n'existe pas", HttpStatus.NOT_FOUND);
            }

            // Récupérer le panier depuis Optional
            Panier panier = panierOptional.get();

            // Supprimer tous les articles du panier
            panier.getArticles().clear();

            // Sauvegarder le panier mis à jour
            panierService.delete(panier);

            return new ResponseEntity<>("Le panier a été vidé avec succès", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Une erreur s'est produite lors du vidage du panier", HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
