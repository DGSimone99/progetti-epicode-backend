package catalogo.prestiti;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
public class PrestitoDAO {
    private EntityManager em;

    public Prestito findById(Long id) {
        return em.find(Prestito.class, id);
    }

    public void insert(Prestito prestito) {
        em.persist(prestito);
    }

    public void insert(List<Prestito> prestiti) {
        prestiti.forEach(prestito -> em.persist(prestito));
    }

    public void delete(Long id) {
        Prestito prestito = findById(id);
        if (prestito != null) {
            em.remove(prestito);
        }
    }

    public void update(Prestito prestito) {
        em.merge(prestito);
    }

    public List<Prestito> findPrestitiByUtente(long numeroTessera) {
        return em.createQuery("SELECT prestito FROM Prestito prestito WHERE prestito.utente.numeroTessera = :tessera AND prestito.dataRestituzione IS NULL", Prestito.class)
                .setParameter("tessera", numeroTessera)
                .getResultList();
    }

    public List<Prestito> findPrestitiNonRestituiti() {
        return em.createQuery("SELECT prestito FROM Prestito prestito WHERE prestito.dataRestituzione IS NULL OR prestito.dataRestituzionePrevista < :oggi", Prestito.class)
                .setParameter("oggi", LocalDate.now())
                .getResultList();
    }

    public List<Prestito> findAll() {
        return em.createQuery("SELECT prestito FROM Prestito prestito", Prestito.class)
                .getResultList();
    }
}
