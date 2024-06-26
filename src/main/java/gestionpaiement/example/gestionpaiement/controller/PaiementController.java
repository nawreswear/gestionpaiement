package gestionpaiement.example.gestionpaiement.controller;
import gestionpaiement.example.gestionpaiement.model.Paiement;
import gestionpaiement.example.gestionpaiement.service.PaiementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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

}
