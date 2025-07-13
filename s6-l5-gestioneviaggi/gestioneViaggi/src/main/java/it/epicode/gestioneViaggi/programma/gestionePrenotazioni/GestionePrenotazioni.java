package it.epicode.gestioneViaggi.programma.gestionePrenotazioni;

import it.epicode.gestioneViaggi.prenotazione.PrenotazioneRepository;
import it.epicode.gestioneViaggi.prenotazione.PrenotazioneService;
import it.epicode.gestioneViaggi.viaggio.StatoViaggio;
import it.epicode.gestioneViaggi.viaggio.Viaggio;
import it.epicode.gestioneViaggi.viaggio.ViaggioRepository;
import it.epicode.gestioneViaggi.viaggio.ViaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class GestionePrenotazioni {
    Scanner scanner = new Scanner(System.in);

    @Autowired
    private ViaggioService viaggioService;

    @Autowired
    private PrenotazioneService prenotazioneService;

    @Autowired
    private NuovaPrenotazione nuovaPrenotazione;

    @Autowired
    private ViaggioRepository viaggioRepository;

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    public void GestionePrenotazioni() {
        while (true) {
            System.out.println("Seleziona operazione:");
            System.out.println("1. Lista Prenotazioni");
            System.out.println("2. Nuova Prenotazione");
            System.out.println("3. Modifica stato viaggio");
            System.out.println("N. Torna indietro");
            System.out.println("0. Chiudi il programma");

            String scelta = scanner.next().toLowerCase();

            switch (scelta) {
                case "1":
                    System.out.println("Lista Prenotazioni:");
                    if(prenotazioneRepository.findAll().isEmpty()) {
                        System.out.println("Nessuna prenotazione trovata.");
                        break;
                    }
                    prenotazioneService.getAllPrenotazioni().forEach(System.out::println);
                    break;
                case "2":
                    nuovaPrenotazione.NuovaPrenotazione();
                    break;
                case "3":
                    System.out.println("Lista Viaggi:");
                    if(viaggioRepository.findAll().isEmpty()) {
                        System.out.println("Nessun viaggio trovato.");
                        break;
                    }
                    viaggioService.getAllViaggi().forEach(System.out::println);

                    System.out.println("Seleziona l'ID del viaggio: ");
                    Long idViaggio = scanner.nextLong();

                    if(!viaggioRepository.existsById(idViaggio)) {
                        System.out.println("ID non valido. Riprova.");
                        break;
                    }

                    Viaggio viaggio = viaggioService.getViaggioById(idViaggio);

                    if(viaggio.getStatoViaggio().equals(StatoViaggio.IN_PROGRAMMA)) {
                        viaggioService.aggiornaStato(idViaggio, StatoViaggio.COMPLETATO);
                        System.out.println("Viaggio con ID: " + idViaggio + " Completato.");
                    } else {
                        viaggioService.aggiornaStato(idViaggio, StatoViaggio.IN_PROGRAMMA);
                        System.out.println("Viaggio con ID: " + idViaggio + " In Programma.");
                    }
                    break;
                case "n":
                    return;
                case "0":
                    System.out.println("Chiusura del programma...");
                    break;
                default:
                    System.out.println("Scelta non valida");
            }
        }
    }
}
