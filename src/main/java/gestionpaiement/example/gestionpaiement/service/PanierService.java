package gestionpaiement.example.gestionpaiement.service;



import gestionpaiement.example.gestionpaiement.model.Article;
import gestionpaiement.example.gestionpaiement.model.Panier;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface PanierService {
    Panier save(Panier panier);

    String deletePanier(long id);

    List<Panier> getAllPaniers();

    Panier updatePanier(Long id, Panier panier);

    Optional<Panier> findById(long id);

     Panier getPanierById(Long panierId);

    @Transactional
    void viderArticlesDuPanier(Long panierId);

    void delete(Panier panier);

    double calculerMontantTotal(List<Panier> paniers);
    boolean containsArticle(Long panierId, Long articleId);

    // List<Panier> getPaniesrByArticle(Article article);

    //double getTotalQuantiteByArticle(Article article);
    List<Panier> getPanierAvecIdPartenaire(Long partenId);
}
