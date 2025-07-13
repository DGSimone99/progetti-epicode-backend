package it.epicode.gestione_prenotazioni.runner.programma;

import it.epicode.gestione_prenotazioni.model.postazione.Postazione;
import it.epicode.gestione_prenotazioni.model.utente.Prenotazione;
import it.epicode.gestione_prenotazioni.model.utente.Utente;
import it.epicode.gestione_prenotazioni.repository.PostazioneRepository;
import it.epicode.gestione_prenotazioni.repository.PrenotazioneRepository;
import it.epicode.gestione_prenotazioni.repository.UtenteRepository;
import it.epicode.gestione_prenotazioni.runner.AvvioProgrammaRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

@Component
public class NuovaPrenotazione {

    private final AvvioProgrammaRunner avvioProgrammaRunner = new AvvioProgrammaRunner();

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private PostazioneRepository postazioneRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    public void NuovaPrenotazione() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Creazione Nuova Prenotazione");

        while (true) {
            utenteRepository.findAll().forEach(utente -> {
                System.out.println("ID: " + utente.getId() + " - Nome: " + utente.getNomeCompleto() + " - Email: " + utente.getEmail());
            });
            System.out.println("Inserisci l'ID dell'utente:");
            Long userId = scanner.nextLong();
            scanner.nextLine();
            if (utenteRepository.findById(userId).isEmpty()) {
                System.out.println("Utente non trovato. Riprova.");
                continue;
            }
            Optional<Utente> utenteOptional = utenteRepository.findById(userId);
            Utente utente = utenteOptional.get();

            postazioneRepository.findAll().forEach(postazione -> {
                System.out.println("ID: " + postazione.getId() + " - " +  postazione.getEdificio().getNome() + " - Città: " + postazione.getEdificio().getCitta() + " - Email: " + postazione.getTipoPostazione());
            });

            System.out.println("Inserisci l'ID della sede:");
            String idSede = scanner.next();
            if (postazioneRepository.findById(Long.parseLong(idSede)).isEmpty()) {
                System.out.println("Postazione non trovata. Riprova.");
                continue;
            }
            Optional<Postazione> postazioneOptional = postazioneRepository.findById(Long.parseLong(idSede));
            Postazione postazione = postazioneOptional.get();

            System.out.println("Inserisci la data della prenotazione (formato: yyyy-MM-dd):");
            String data = scanner.next();

            try {
                if (LocalDate.parse(data).isBefore(LocalDate.now())) {
                    System.out.println("La data non può essere prima di oggi.");
                    continue;
                }

                if (prenotazioneRepository.findByDataPrenotazioneAndPostazione(LocalDate.parse(data), postazione) != null) {
                    System.out.println("Questa postazione è già prenotata per questa data.");
                    continue;
                }

                Prenotazione prenotazioneUtente = prenotazioneRepository
                        .findByUtenteAndDataPrenotazione(Optional.of(utente), LocalDate.parse(data));
                if (prenotazioneUtente != null) {
                    System.out.println("L'utente ha già una prenotazione per questa data.");
                    continue;
                }

                Prenotazione prenotazione = new Prenotazione();
                prenotazione.setDataPrenotazione(LocalDate.parse(data));
                prenotazione.setUtente(utente);
                prenotazione.setPostazione(postazione);

                prenotazioneRepository.save(prenotazione);
                System.out.println("Prenotazione creata con successo!");

            } catch (java.time.format.DateTimeParseException e) {
                System.out.println("Formato data non valido. Usa il formato yyyy-MM-dd.");
            }
            return;
        }
    }
}
