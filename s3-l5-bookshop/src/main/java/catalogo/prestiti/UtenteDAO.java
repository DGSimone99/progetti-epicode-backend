package catalogo.prestiti;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class UtenteDAO {
    private EntityManager em;

    public Utente findByNumeroTessera(Long numeroTessera) {
        return em.find(Utente.class, numeroTessera);
    }

    public void insert(Utente utente) {
        em.persist(utente);
    }

    public void insert(List<Utente> utenti) {
        utenti.forEach(utente -> em.persist(utente));
    }

    public void delete(Long numeroTessera) {
        Utente utente = findByNumeroTessera(numeroTessera);
        if (utente != null) {
            em.remove(utente);
        }
    }

    public void update(Utente utente) {
        em.merge(utente);
    }
}
