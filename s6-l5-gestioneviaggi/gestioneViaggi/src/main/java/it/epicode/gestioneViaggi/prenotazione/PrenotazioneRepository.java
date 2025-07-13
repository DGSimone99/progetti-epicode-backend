package it.epicode.gestioneViaggi.prenotazione;


import it.epicode.gestioneViaggi.dipendente.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    boolean existsByDipendenteAndViaggio_DataPartenza(Dipendente dipendente, LocalDate dataPrenotazione);
    List<Prenotazione> findAllByDipendente(Dipendente dipendente);
}