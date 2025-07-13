package it.epicode.gestione_prenotazioni.repository;


import it.epicode.gestione_prenotazioni.model.postazione.Postazione;
import it.epicode.gestione_prenotazioni.model.utente.Prenotazione;
import it.epicode.gestione_prenotazioni.model.utente.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    Prenotazione findByUtenteAndDataPrenotazione(Optional<Utente> utente, LocalDate dataPrenotazione);
    Prenotazione findByDataPrenotazioneAndPostazione(LocalDate dataPrenotazione, Postazione postazione);
    List<Prenotazione> findByPostazione_Edificio_NomeIgnoreCase(String nome);
}