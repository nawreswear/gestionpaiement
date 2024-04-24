package gestionpaiement.example.gestionpaiement.service;

import gestionpaiement.example.gestionpaiement.model.Article;
import gestionpaiement.example.gestionpaiement.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;


    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    // Create
    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    // Read
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }
    @Override
    public Article getArticleById(Long articleId) {
        // Utiliser la méthode findById du repository pour récupérer l'article par son ID
        Optional<Article> optionalArticle = articleRepository.findById(articleId);

        // Vérifier si l'article existe dans la base de données
        if (optionalArticle.isPresent()) {
            return optionalArticle.get();
        } else {
            //  l'article n'existe pas
            throw new NotFoundException("Article not found with ID: " + articleId);
        }
    }

    public Optional<Article> getArticleById(long id) {
        return articleRepository.findById(id);
    }

    // Update
    public Article updateArticle(long id, Article newArticleData) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isPresent()) {
            Article existingArticle = optionalArticle.get();
            existingArticle.setTitre(newArticleData.getTitre());
            existingArticle.setPrix(newArticleData.getPrix());
            existingArticle.setDescription(newArticleData.getDescription());
            // Ajoutez d'autres attributs que vous souhaitez mettre à jour
            return articleRepository.save(existingArticle);
        } else {
            return null; //  l'article n'a pas été trouvé
        }
    }

    // Delete
    public void deleteArticle(long id) {

        articleRepository.deleteById(id);
    }
}