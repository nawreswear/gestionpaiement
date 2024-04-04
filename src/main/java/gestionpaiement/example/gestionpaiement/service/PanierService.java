package gestionpaiement.example.gestionpaiement.service;



import gestionpaiement.example.gestionpaiement.model.Article;
import gestionpaiement.example.gestionpaiement.model.Panier;

import java.util.List;
import java.util.Optional;

public interface PanierService {
   // void addArticleToCart(Article article);
    Panier save(Panier panier);

    String deletePanier(long id);

    List<Panier> getAllPaniers();

    Panier updatePanier(Long id, Panier panier);

    Optional<Panier> findById(long id);

 Panier getPanierById(Long panierId);

 // Méthode pour calculer le montant total
    double calculerMontantTotal(List<Panier> paniers);

    // List<Panier> getPaniesrByArticle(Article article);

    //double getTotalQuantiteByArticle(Article article);
}
