package gestionpaiement.example.gestionpaiement.repository;

import gestionpaiement.example.gestionpaiement.model.Paiement;
import gestionpaiement.example.gestionpaiement.model.Panier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement,Long> {
    Paiement getPaiementById(long id);



}
