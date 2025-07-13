package it.epicode.gestioneViaggi.programma;

import it.epicode.gestioneViaggi.programma.gestioneDipendenti.GestioneDipendenti;
import it.epicode.gestioneViaggi.programma.gestionePrenotazioni.GestionePrenotazioni;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Order(4)
@RequiredArgsConstructor
@Component
public class ProgrammaRunner implements CommandLineRunner {
    Scanner scanner = new Scanner(System.in);

    @Autowired
    private GestionePrenotazioni gestionePrenotazioni;

    @Autowired
    private GestioneDipendenti gestioneDipendenti;

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            System.out.println("Seleziona Operazione:");
            System.out.println("1. Gestione Prenotazioni");
            System.out.println("2. Gestione Dipendenti");
            System.out.println("0. Chiudi il programma");

            String scelta = scanner.nextLine();

            switch (scelta) {
                case "1":
                    gestionePrenotazioni.GestionePrenotazioni();
                    break;
                case "2":
                    gestioneDipendenti.GestioneDipendenti();
                    break;
                case "0":
                    System.out.println("Chiusura del programma...");
                    break;
                default:
                    System.out.println("Scelta non valida");
            }
        }
    }
}
