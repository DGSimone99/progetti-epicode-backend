package it.epicode.gestione_prenotazioni.runner;

import it.epicode.gestione_prenotazioni.model.postazione.Edificio;
import it.epicode.gestione_prenotazioni.model.postazione.Postazione;
import it.epicode.gestione_prenotazioni.model.utente.Prenotazione;
import it.epicode.gestione_prenotazioni.model.utente.Utente;
import it.epicode.gestione_prenotazioni.repository.EdificioRepository;
import it.epicode.gestione_prenotazioni.repository.PostazioneRepository;
import it.epicode.gestione_prenotazioni.repository.PrenotazioneRepository;
import it.epicode.gestione_prenotazioni.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Order(1)
@RequiredArgsConstructor
@Component
public class SalvaDatiRunner implements CommandLineRunner {
    private final UtenteRepository utenteRepository;
    private final PrenotazioneRepository prenotazioneRepository;
    private final EdificioRepository edificioRepository;
    private final PostazioneRepository postazioneRepository;

    @Autowired
    private List<Utente> utenti;

    @Autowired
    private List<Edificio> edifici;

    @Autowired
    private List<Postazione> postazioni;

    @Autowired
    private List<Prenotazione> prenotazioni;


    @Override
    public void run(String... args) throws Exception {
        utenti.forEach(utenteRepository::save);
        edifici.forEach(edificioRepository::save);
        postazioni.forEach(postazioneRepository::save);
        prenotazioni.forEach(prenotazioneRepository::save);
    }
}