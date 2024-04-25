package gestionpaiement.example.gestionpaiement.controller;

import gestionpaiement.example.gestionpaiement.model.Article;
import gestionpaiement.example.gestionpaiement.model.Panier;
import gestionpaiement.example.gestionpaiement.model.Part_En;
import gestionpaiement.example.gestionpaiement.repository.ArticleRepository;
import gestionpaiement.example.gestionpaiement.repository.PanierRepository;
import gestionpaiement.example.gestionpaiement.service.PanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/panier")
public class PanierController {

    @Autowired
    private PanierService panierService;
    @Autowired
    private PanierRepository panierRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/{partenId}")
    public ResponseEntity<List<Panier>> getPaniersByPartenaire(@PathVariable Long partenId) {
        List<Panier> paniers = panierRepository.findByParten_Id(partenId);

        for (Panier panier : paniers) {
            List<Article> articles = articleRepository.findByPaniersId(panier.getId());
            panier.setArticles(articles);
        }
        return ResponseEntity.ok().body(paniers);
    }
    @PostMapping("/addPanier/{partenId}")
    public ResponseEntity<Long> createPanier(@PathVariable Long partenId) {
        Panier panier = new Panier();
        panier.setPartenId(partenId);
        panierService.save(panier);
        return new ResponseEntity<>(panier.getId(), HttpStatus.OK);
    }
    @GetMapping("/partenaire/{partenId}")
    public ResponseEntity<List<Panier>> getPanierAvecIdPartenaire(@PathVariable Long partenId) {
        List<Panier> paniers = panierRepository.findByParten_Id(partenId);

        for (Panier panier : paniers) {
            List<Article> articles = articleRepository.findByPaniersId(panier.getId());
            panier.setArticles(articles);
        }
        return ResponseEntity.ok().body(paniers);
    }
    @PostMapping("/addPanier")
    public ResponseEntity<Long> createPanier() {
        Panier panier = new Panier();
        // Vous pouvez ajouter d'autres initialisations pour le panier si nécessaire
        panierService.save(panier); // Supposons que vous avez une méthode save() dans votre service pour enregistrer le panier
        return new ResponseEntity<>(panier.getId(), HttpStatus.OK);
    }

    @GetMapping("/getAllPaniers")
    public List<Panier> getAllPaniers() {
        return panierService.getAllPaniers();
    }

    @GetMapping("/getPanierById/{id}")
    public Panier getPanierById(@PathVariable Long id) {
        return panierService.findById(id).orElse(null);
    }

    @PutMapping("/updatePanier/{id}")
    public Panier updatePanier(@RequestBody Panier panier, @PathVariable Long id) {
        return panierService.updatePanier(id, panier);
    }

    @DeleteMapping("/deletePanier/{id}")
    public String deletePanier(@PathVariable Long id) {
        return panierService.deletePanier(id);
    }

    @GetMapping("/calculerMontantTotal")
    public double calculerMontantTotal() {
        List<Panier> paniers = panierService.getAllPaniers();
        return panierService.calculerMontantTotal(paniers);
    }


}