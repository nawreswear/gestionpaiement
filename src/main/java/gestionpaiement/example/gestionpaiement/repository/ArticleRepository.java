package gestionpaiement.example.gestionpaiement.repository;

import gestionpaiement.example.gestionpaiement.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article , Long> {

    List<Article> findByPaniersId(Long panierId);
}
