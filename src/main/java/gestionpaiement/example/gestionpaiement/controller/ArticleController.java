package gestionpaiement.example.gestionpaiement.controller;
import gestionpaiement.example.gestionpaiement.model.Article;
import gestionpaiement.example.gestionpaiement.model.Panier;
<<<<<<< HEAD
<<<<<<< HEAD
import gestionpaiement.example.gestionpaiement.service.*;
=======
=======
>>>>>>> e12daffcb4c3638f7c42dab513cbf9c7131a9573
import gestionpaiement.example.gestionpaiement.model.Part_En;
import gestionpaiement.example.gestionpaiement.service.ArticleService;
import gestionpaiement.example.gestionpaiement.service.PanierService;
import gestionpaiement.example.gestionpaiement.service.Part_EnService;
<<<<<<< HEAD
>>>>>>> a2320ffb2e017f1e0b79c2c0685237b518442982
=======
>>>>>>> e12daffcb4c3638f7c42dab513cbf9c7131a9573
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

    private PaiementService paiementService;

    @Autowired
    private Part_EnService PartenService;
    @Autowired
    private Part_EnService PartenService;
    @Autowired
    private Part_EnService PartenService;
    @Autowired
    private PanierService panierService;

<<<<<<< HEAD
<<<<<<< HEAD

=======
>>>>>>> a2320ffb2e017f1e0b79c2c0685237b518442982
=======

>>>>>>> e12daffcb4c3638f7c42dab513cbf9c7131a9573
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
<<<<<<< HEAD
<<<<<<< HEAD
                // la quantité dans le panier ne dépasse pas la quantité en stock dans l'article
               // if (panier.getQuantitecde() <= article.getQuantiter()) {
=======
                // Vérifier si la quantité dans le panier ne dépasse pas la quantité en stock dans l'article
                if (panier.getQuantitecde() <= article.getQuantiter()) {
>>>>>>> a2320ffb2e017f1e0b79c2c0685237b518442982
=======
                // la quantité dans le panier ne dépasse pas la quantité en stock dans l'article
               // if (panier.getQuantitecde() <= article.getQuantiter()) {
>>>>>>> e12daffcb4c3638f7c42dab513cbf9c7131a9573
                    // Ajouter l'article au panier
                    panier.addArticle(article);
                    // Augmenter la quantité commandée
                    panier.setQuantitecde(panier.getQuantitecde() + 1); // Mettre à jour la quantité
                    // Mettre à jour le partenId dans le panier
                    panier.setPartenId(partenId);
<<<<<<< HEAD
<<<<<<< HEAD
                    panier.setTotalP(article.getPrixvente() + panier.getTotalP()); // Mettre à jour le total
=======
                    panier.setTotalP(article.getPrixvente() * panier.getQuantitecde()); // Mettre à jour le total
>>>>>>> a2320ffb2e017f1e0b79c2c0685237b518442982
=======
                    panier.setTotalP(article.getPrixvente() * panier.getQuantitecde()); // Mettre à jour le total
>>>>>>> e12daffcb4c3638f7c42dab513cbf9c7131a9573
                    panierService.save(panier);
                    // Initialiser la collection paniers dans l'article
                    article.getPaniers().size();
                    return ResponseEntity.ok(panier);
                } else {
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> e12daffcb4c3638f7c42dab513cbf9c7131a9573
                    // la quantité dans le panier dépasse la quantité en stock dans l'article
                    return ResponseEntity.badRequest().body("La quantité dans le panier dépasse la quantité disponible dans l'article.");
                }
          /*  } else {
                //  l'article n'existe pas
<<<<<<< HEAD
=======
                    // si la quantité dans le panier dépasse la quantité en stock dans l'article
                    return ResponseEntity.badRequest().body("La quantité dans le panier dépasse la quantité disponible dans l'article.");
                }
            } else {
                // si l'article n'existe pas
>>>>>>> a2320ffb2e017f1e0b79c2c0685237b518442982
                return ResponseEntity.notFound().build();
            }*/
        } else {
<<<<<<< HEAD
=======
                return ResponseEntity.notFound().build();
            }*/
        } else {
>>>>>>> e12daffcb4c3638f7c42dab513cbf9c7131a9573
            // le panier n'existe pas
            return ResponseEntity.notFound().build();
        }
    }
    /*@DeleteMapping("/{panierId}/supprimer-article/{articleId}")
<<<<<<< HEAD
=======
            // si le panier n'existe pas
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{panierId}/supprimer-article/{articleId}")
>>>>>>> a2320ffb2e017f1e0b79c2c0685237b518442982
=======
>>>>>>> e12daffcb4c3638f7c42dab513cbf9c7131a9573
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
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> e12daffcb4c3638f7c42dab513cbf9c7131a9573
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
<<<<<<< HEAD
=======
>>>>>>> a2320ffb2e017f1e0b79c2c0685237b518442982
=======
>>>>>>> e12daffcb4c3638f7c42dab513cbf9c7131a9573
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

<<<<<<< HEAD
<<<<<<< HEAD
    @DeleteMapping("/panier/{id}")
    public ResponseEntity<String> viderPanier(@PathVariable Long id) {
        try {
            // Vérifier si le panier existe
            Optional<Panier> panierOptional = panierService.findById(id);
            if (panierOptional.isEmpty()) {
                return new ResponseEntity<>("Le panier n'existe pas", HttpStatus.NOT_FOUND);
            }

=======
>>>>>>> e12daffcb4c3638f7c42dab513cbf9c7131a9573
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

<<<<<<< HEAD

=======
>>>>>>> a2320ffb2e017f1e0b79c2c0685237b518442982
=======
>>>>>>> e12daffcb4c3638f7c42dab513cbf9c7131a9573
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
