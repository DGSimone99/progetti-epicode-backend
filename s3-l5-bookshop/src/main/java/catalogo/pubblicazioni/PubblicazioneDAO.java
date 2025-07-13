package catalogo.pubblicazioni;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PubblicazioneDAO {
    private EntityManager em;


    public Pubblicazione findByIsbn(Long isbn) {
        return em.find(Pubblicazione.class, isbn);
    }

    public void insert(Pubblicazione pubblicazione) {
        em.persist(pubblicazione);
    }

    public void insert(List<Pubblicazione> pubblicazioni) {
        pubblicazioni.forEach(pubblicazione -> em.persist(pubblicazione));
    }

    public void delete(Long isbn) {
        Pubblicazione pubblicazione = findByIsbn(isbn);
        if (pubblicazione != null) {
            em.remove(pubblicazione);
        }
    }

    public void update(Pubblicazione pubblicazione) {
        em.merge(pubblicazione);
    }

    public List<Pubblicazione> findByAnno(int anno) {
        return em.createQuery("SELECT pubblicazione FROM Pubblicazione pubblicazione WHERE pubblicazione.annoPubblicazione = :anno", Pubblicazione.class)
                .setParameter("anno", anno)
                .getResultList();
    }

     public List<Libro> findByAutore(String nomeAutore) {
        return em.createQuery("SELECT libro FROM Libro libro WHERE LOWER(libro.autore) LIKE LOWER(:autore)", Libro.class)
                .setParameter("autore", "%" + nomeAutore + "%")
                .getResultList();
    }

    public List<Pubblicazione> findByTitolo(String titolo) {
        return em.createQuery("SELECT pubblicazione FROM Pubblicazione pubblicazione WHERE LOWER(pubblicazione.titolo) LIKE LOWER(:titolo)", Pubblicazione.class)
                .setParameter("titolo", "%" + titolo + "%")
                .getResultList();
    }

    public List<Pubblicazione> findAll() {
        return em.createQuery("SELECT pubblicazione FROM Pubblicazione pubblicazione", Pubblicazione.class)
                .getResultList();
    }
}
