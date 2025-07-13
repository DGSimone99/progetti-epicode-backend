package it.epicode.gestione_prenotazioni.runner;

import it.epicode.gestione_prenotazioni.repository.PrenotazioneRepository;
import it.epicode.gestione_prenotazioni.runner.programma.ControlloPrenotazioni;
import it.epicode.gestione_prenotazioni.runner.programma.NuovaPrenotazione;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Order(2)
@RequiredArgsConstructor
@Component
public class AvvioProgrammaRunner implements CommandLineRunner {
    @Autowired
    private ControlloPrenotazioni controlloPrenotazioni;

    @Autowired
    private NuovaPrenotazione nuovaPrenotazione;

    public void AvvioProgrammaRunner() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Scegli operazione:");
            System.out.println("1. Controlla Prenotazioni");
            System.out.println("2. Crea Prenotazione");
            System.out.println("0. Chiudi programma");

            String scelta = scanner.next();


            switch(scelta) {
                case "1": controlloPrenotazioni.ControlloPrenotazioni();
                break;
                case "2":
                    nuovaPrenotazione.NuovaPrenotazione();
                break;
                case "0": System.exit(0);
                default:
                    System.out.println("Comando non riconosciuto.");
            }
        }
    }

    @Override
    public void run(String... args) throws Exception {
        AvvioProgrammaRunner();
    }
}
