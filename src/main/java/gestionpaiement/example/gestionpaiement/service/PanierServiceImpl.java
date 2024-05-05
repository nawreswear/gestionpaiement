package gestionpaiement.example.gestionpaiement.service;

import gestionpaiement.example.gestionpaiement.model.Article;
import gestionpaiement.example.gestionpaiement.model.Panier;
import gestionpaiement.example.gestionpaiement.repository.PanierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
<<<<<<< HEAD
<<<<<<< HEAD
=======
import java.util.Collections;
>>>>>>> a2320ffb2e017f1e0b79c2c0685237b518442982
=======
>>>>>>> e12daffcb4c3638f7c42dab513cbf9c7131a9573
import java.util.List;
import java.util.Optional;

@Service
public class PanierServiceImpl implements PanierService {
    @Autowired
    private PanierRepository panierRepository;
    @Override
    public Panier save(Panier panier) {
        return panierRepository.save(panier);
    }

    @Override
    public String deletePanier(long id) {
        panierRepository.deleteById(id);
        return "Panier supprimé avec succès !";
    }

    @Override
    public List<Panier> getAllPaniers() {
        return panierRepository.findAll();
    }

    @Override
    public Panier updatePanier(Long id, Panier panier) {
        Optional<Panier> existingPanierOptional = panierRepository.findById(id);
        if (existingPanierOptional.isPresent()) {
                  Panier existingPanier = existingPanierOptional.get();
                  existingPanier.setPaiements(panier.getPaiements());
                   existingPanier.setTotalP(panier.getTotalP());
            existingPanier.setQuantitecde(panier.getQuantitecde());
            return panierRepository.save(existingPanier);
        } else {
            return null;
        }
    }
    @Override
    @Transactional
    public boolean containsArticle(Long panierId, Long articleId) {
        // Obtenir le panier spécifique à partir de son ID
        Panier panier = panierRepository.findById(panierId).orElse(null);
        if (panier != null) {
            // Appeler la méthode containsArticle sur le panier obtenu
<<<<<<< HEAD
<<<<<<< HEAD
            return panier.containsArticle(panierId);
=======
            return panier.containsArticle(panierId, articleId);
>>>>>>> a2320ffb2e017f1e0b79c2c0685237b518442982
=======
            return panier.containsArticle(panierId);
>>>>>>> e12daffcb4c3638f7c42dab513cbf9c7131a9573
        }
        return false;
    }
    @Override
    public Panier getPanierById(Long panierId) {
        // Utiliser la méthode findById du repository pour récupérer le panier par son ID
        return panierRepository.findById(panierId).orElse(null);
    }

    @Override
    public Optional<Panier> findById(long id) {
        return panierRepository.findById(id);
    }

    @Override
    @Transactional
    public void viderArticlesDuPanier(Long panierId) {
        // Supprimer toutes les entrées correspondantes dans la table de liaison article-panier
        panierRepository.deleteById(panierId);
    }
    @Override
    public void delete(Panier panier) {
        // Supprimer le panier de la base de données
        panierRepository.delete(panier);
    }
    // Méthode pour calculer le montant total
    @Override
    public double calculerMontantTotal(List<Panier> paniers) {
        double montantTotal = 0.0;
        // Parcourir la liste des paniers et ajouter le total de chacun au montant total
        for (Panier panier : paniers) {
            // Ajouter le total de chaque panier au montant total
            montantTotal += panier.getTotalP();
        }
        return montantTotal;
    }
    @Override
    public List<Panier> getPanierAvecIdPartenaire(Long partenId) {
        return panierRepository.findByParten_Id(partenId);
    }
}
