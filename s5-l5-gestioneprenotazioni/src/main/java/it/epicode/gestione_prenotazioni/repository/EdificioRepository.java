package it.epicode.gestione_prenotazioni.repository;


import it.epicode.gestione_prenotazioni.model.postazione.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EdificioRepository extends JpaRepository<Edificio, Long> {
}