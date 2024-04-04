package gestionpaiement.example.gestionpaiement.service;

import gestionpaiement.example.gestionpaiement.model.Paiement;
import gestionpaiement.example.gestionpaiement.repository.PaiementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaiementServiceImpl {

    private final PaiementRepository paiementRepository;

    public PaiementServiceImpl(PaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }

    public Paiement savePaiement(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    public List<Paiement> getAllPaiements() {
        return paiementRepository.findAll();
    }

    public Paiement getPaiementById(long id) {
        return paiementRepository.getPaiementById(id);
    }

    public String deletePaiement(long id) {
        paiementRepository.deleteById(id);
        return "Paiement supprimé avec succès !";
    }

    public Paiement updatePaiement(Paiement paiement, long id) {
        // Vérifier si le paiement avec l'ID spécifié existe dans la base de données
        Paiement existingPaiement = paiementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Paiement introuvable avec l'ID : " + id));

        // Mettre à jour les attributs du paiement existant avec les nouvelles valeurs
        existingPaiement.setMontant(paiement.getMontant());
        existingPaiement.setStatut(paiement.getStatut());
        existingPaiement.setDate(paiement.getDate());

        // Enregistrer les modifications dans la base de données
        return paiementRepository.save(existingPaiement);
    }




    // Méthode pour rechercher les paiements par nom d'utilisateur
   /* public List<Paiement> findByNomUtilisateur(String nomUtilisateur) {
        return paiementRepository.findByNomUtilisateur(nomUtilisateur);
    }*/
}
