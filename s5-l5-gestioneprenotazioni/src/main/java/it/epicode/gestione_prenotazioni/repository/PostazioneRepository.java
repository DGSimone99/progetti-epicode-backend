package it.epicode.gestione_prenotazioni.repository;


import it.epicode.gestione_prenotazioni.model.postazione.Postazione;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostazioneRepository extends JpaRepository<Postazione, Long> {
}