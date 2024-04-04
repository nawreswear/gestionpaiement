package gestionpaiement.example.gestionpaiement.controller;


import gestionpaiement.example.gestionpaiement.model.Paiement;
import gestionpaiement.example.gestionpaiement.service.PaiementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paiements")
public class PaiementController {

    @Autowired
    private PaiementServiceImpl paiementService;

    @PostMapping("/addPaiement")
    public Paiement addPaiement(@RequestBody Paiement paiement) {
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

    @DeleteMapping("/deletePaiement/{id}")
    public String deletePaiement(@PathVariable Long id) {
        return paiementService.deletePaiement(id);
    }

    /*@GetMapping("/findByArticle")
    public List<Lignepanier> findByArticle(@RequestParam Long articleId) {
        Optional<Article> optionalArticle = articleRepository.findById(articleId);

        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            return lignepanierService.findByArticle(article);
        } else {
            throw new ArticleNotFoundException("L'article avec l'ID " + articleId + " n'a pas été trouvé.");
        }
    }*/
}
