package gestionpaiement.example.gestionpaiement.repository;


import gestionpaiement.example.gestionpaiement.model.Part_En;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Part_EnRepository extends JpaRepository<Part_En,Long> {

}
