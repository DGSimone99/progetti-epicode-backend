package it.epicode.gestione_prenotazioni.service;

import it.epicode.gestione_prenotazioni.model.utente.Prenotazione;
import it.epicode.gestione_prenotazioni.repository.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    public Prenotazione salvaPrenotazione(Prenotazione prenotazione) {
        if (prenotazione.getDataPrenotazione().isBefore(LocalDate.now())) {
            prenotazione.setAttiva(false);
        } else {
            prenotazione.setAttiva(true);
        }
        return prenotazioneRepository.save(prenotazione);
    }
}
