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
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> e12daffcb4c3638f7c42dab513cbf9c7131a9573

    @Transactional
    void viderArticlesDuPanier(Long panierId);

    void delete(Panier panier);
<<<<<<< HEAD
=======
>>>>>>> a2320ffb2e017f1e0b79c2c0685237b518442982
=======
>>>>>>> e12daffcb4c3638f7c42dab513cbf9c7131a9573

    double calculerMontantTotal(List<Panier> paniers);
    boolean containsArticle(Long panierId, Long articleId);

    // List<Panier> getPaniesrByArticle(Article article);

    //double getTotalQuantiteByArticle(Article article);
    List<Panier> getPanierAvecIdPartenaire(Long partenId);
}
