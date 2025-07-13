package it.epicode.gestioneViaggi.programma.gestioneDipendenti;

import it.epicode.gestioneViaggi.dipendente.Dipendente;
import it.epicode.gestioneViaggi.dipendente.DipendenteService;
import it.epicode.gestioneViaggi.prenotazione.Prenotazione;
import it.epicode.gestioneViaggi.prenotazione.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class GestioneDipendenti {
    Scanner scanner = new Scanner(System.in);

    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private PrenotazioneService prenotazioneService;

    public void GestioneDipendenti() {
        while (true) {
            List<Dipendente> dipendenti = dipendenteService.getAllDipendenti();
            System.out.println("Seleziona operazione:");
            System.out.println("1. Lista Dipendenti");
            System.out.println("2. Sopprimi Dipendente");
            System.out.println("3. Lista Viaggi per Dipendenti");
            System.out.println("N. Torna indietro");
            System.out.println("0. Chiudi il programma");

            String scelta = scanner.next().toLowerCase();

            switch (scelta) {
                case "1":
                    dipendenti.forEach(System.out::println);
                    break;
                case "2":
                    System.out.println("Inserisci ID dipendente da sopprimere:");
                    Long idDipendente = scanner.nextLong();
                    scanner.nextLine();

                    if (!dipendenti.stream().anyMatch(dipendente -> dipendente.getId().equals(idDipendente))) {
                        System.out.println("ID non valido. Riprova.");
                        break;
                    }

                    dipendenteService.deleteDipendente(idDipendente);
                    System.out.println("Non è rimasto più nulla del Dipendente con ID " + idDipendente);
                    break;

                case "3":
                    for (Dipendente dipendente : dipendenti) {
                        System.out.println("Dipendente: " + dipendente.getNome() + " " + dipendente.getCognome());

                        List<Prenotazione> prenotazioni = prenotazioneService.getPrenotazioniByDipendente(dipendente);

                        if (prenotazioni.isEmpty()) {
                            System.out.println("  Nessun viaggio prenotato.");
                        } else {
                            for (Prenotazione prenotazione : prenotazioni) {
                                System.out.println("  - " + prenotazione.getViaggio().getDestinazione() + " | " + prenotazione.getViaggio().getDataPartenza());
                            }
                        }
                    }
                case "N":
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
