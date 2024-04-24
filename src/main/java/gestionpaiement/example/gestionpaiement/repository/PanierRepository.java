package gestionpaiement.example.gestionpaiement.repository;

import gestionpaiement.example.gestionpaiement.model.Article;
import gestionpaiement.example.gestionpaiement.model.Panier;
import gestionpaiement.example.gestionpaiement.model.Part_En;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PanierRepository extends JpaRepository<Panier,Long> {
   // List<Panier> findByPartenId(Long partenId);
   //  List<Panier> findByPartenId(Long partenId);
   List<Panier> findByParten_Id(Long partenId);
}