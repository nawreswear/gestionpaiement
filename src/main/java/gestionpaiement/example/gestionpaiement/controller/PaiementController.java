package gestionpaiement.example.gestionpaiement.controller;
import gestionpaiement.example.gestionpaiement.model.Paiement;
import gestionpaiement.example.gestionpaiement.model.Panier;
import gestionpaiement.example.gestionpaiement.repository.PanierRepository;
import gestionpaiement.example.gestionpaiement.service.PaiementServiceImpl;
import gestionpaiement.example.gestionpaiement.service.PanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
<<<<<<< HEAD
<<<<<<< HEAD

import java.util.Date;
=======
>>>>>>> a2320ffb2e017f1e0b79c2c0685237b518442982
=======
>>>>>>> e12daffcb4c3638f7c42dab513cbf9c7131a9573
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/paiements")
public class PaiementController {

    @Autowired
    private PanierService panierService;
    @Autowired
    private PaiementServiceImpl paiementService;

    @PostMapping("/addPaiement")
    public Paiement addPaiement(@RequestBody Paiement paiement) {
        return paiementService.savePaiement(paiement);
    }

    @PostMapping("/checkout")
    public Paiement passerAuPaiement(@RequestBody Panier panier) {
        // Cr�ez un nouveau paiement � partir du panier
        Paiement paiement = new Paiement();
        paiement.setMontant(panier.getTotalP()); // D�finissez le montant du paiement sur le total du panier
        paiement.setStatut("En attente"); // D�finissez le statut du paiement sur "En attente"
        paiement.setDate(new Date()); // D�finissez la date du paiement sur la date actuelle
        paiement.setPanier(panier); // Liez le paiement au panier

        // Enregistrez le paiement dans la base de donn�es
        return paiementService.savePaiement(paiement);
    }
    @GetMapping("/getAllPaiements")
    public List<Paiement> getAllPaiements() {
        return paiementService.getAllPaiements();
    }

    @GetMapping("/getPaiementById/{id}")
    public Paiement getPaiementById(@PathVariable Long id) {
        return paiementService.getPaiementById(id);
    }

    @PutMapping("/updatePaiement/{id}")
    public Paiement updatePaiement(@RequestBody Paiement paiement, @PathVariable Long id) {
        return paiementService.updatePaiement(paiement, id);
    }
    @PostMapping("/paiement")
    public Paiement fairePaiement(@RequestBody Paiement request) {
        Paiement paiement = new Paiement();
        paiement.setMontant(request.getMontant());
        paiement.setStatut("en cours"); // ou un autre statut initial
        paiement.setDate(new Date());

        // Ajoutez la logique pour associer le paiement à un panier
        // par exemple, en utilisant request.getPanier() pour obtenir le panier associé

        Paiement nouveauPaiement = paiementService.savePaiement(paiement);

        return nouveauPaiement;
    }
    @DeleteMapping("/viderPanier/{paiementId}")
    public String viderPanier(@PathVariable Long paiementId) {
        try {
            // Récupérer le paiement par son ID
            Paiement paiement = paiementService.getPaiementById(paiementId);

            // Récupérer le panier associé au paiement
            Panier panier = paiement.getPanier();

            if (panier != null) {
                // Vider le panier en supprimant tous les articles
                panier.getArticles().clear();
                panier.setTotalP(0.0);
                panier.setQuantitecde(0);

                // Mettre à jour le panier dans la base de données
                panierService.save(panier);

                return "Panier vidé avec succès après le paiement";
            } else {
                return "Aucun panier trouvé pour le paiement avec l'ID : " + paiementId;
            }
        } catch (Exception e) {
            return "Erreur lors de la suppression des articles du panier après le paiement : " + e.getMessage();
        }
    }
    @PutMapping("/updatePaiementstatut/{id}")
    public ResponseEntity<Paiement> updatePaiementstatut(@PathVariable Long id) {
        // Récupérer le paiement par son ID
        Paiement paiement = paiementService.getPaiementById(id);

        // Vérifier si le paiement existe
        if (paiement != null) {
            // Mettre à jour le statut du paiement à "acceptée"
            paiement.setStatut("acceptée");

            // Enregistrer le paiement mis à jour dans la base de données
            Paiement updatedPaiement = paiementService.savePaiement(paiement);

            return ResponseEntity.ok().body(updatedPaiement);
        } else {
            // Si le paiement n'est pas trouvé, retourner une réponse 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/deletePaiement/{id}")
    public String deletePaiement(@PathVariable Long id) {
        return paiementService.deletePaiement(id);
    }

}
