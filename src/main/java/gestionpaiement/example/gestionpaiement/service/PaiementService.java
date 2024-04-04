package gestionpaiement.example.gestionpaiement.service;

import gestionpaiement.example.gestionpaiement.model.Paiement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaiementService {
    Paiement getPaiementById(long id);
    Paiement savePaiement(Paiement paiement);

    String deletePaiement(long id);
    List<Paiement> getAllPaiements();
    Paiement findById(long id);
    List<Paiement> getPaiementsByNomUtilisateur(String nomUtilisateur);
    double calculerMontant(int quantite, double prixInitial);
    Paiement updatePaiement(Paiement paiement, long id);

    List<Paiement> findByNomUtilisateur(String nomUser);
}
