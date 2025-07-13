package it.s2l5.programma;

import it.s2l5.pubblicazioni.Libri;
import it.s2l5.pubblicazioni.Pubblicazioni;
import it.s2l5.pubblicazioni.Riviste;

import java.util.Comparator;
import java.util.Scanner;

import static it.s2l5.programma.Archivio.archivio;

public class AvviaProgramma {
    public static void avviaProgramma() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleziona operazione:");
        System.out.println("1 - Mostra catalogo");
        System.out.println("2 - Ricerca pubblicazione");
        System.out.println("3 - Modifica catalogo");
        System.out.println("4 - Statistiche del catalogo");
        System.out.println("0 - Esci");

        String scelta = scanner.next();

        switch (scelta) {
            case "1":
                archivio.forEach(pubblicazione -> {
                    System.out.println(pubblicazione.getIsbn() + " - " + pubblicazione.getTitolo() + " - " + pubblicazione.getTipologia());
                });
                System.out.println("Vuoi cercare una pubblicazione? s/n");
                String scelta2 = scanner.next().toLowerCase();
                switch (scelta2) {
                    case "s":
                        Ricerca.ricerca();
                        break;
                    case "n":
                        avviaProgramma();
                        break;
                    default:
                        System.out.println("Comando invalido");
                        avviaProgramma();
                        break;
                };
                break;
            case "2":
                Ricerca.ricerca();
                break;
            case "3":
                Modifica.modifica();
                break;
            case "4":
                System.out.println("Lunghezza del catalogo: " + archivio.size());
                System.out.println("Numero libri: " + Libri.libri.size());
                System.out.println("Numero riviste: " + Riviste.riviste.size());

                Pubblicazioni maxPagine = archivio.stream()
                        .max(Comparator.comparingInt(Pubblicazioni::getNumeroPagine))
                        .orElse(null);
                System.out.println("Pubblicazione con il maggior numero di pagine: " + maxPagine.getTitolo() + " con " + maxPagine.getNumeroPagine() + " pagine");

                double mediaPagine = archivio.stream()
                        .mapToDouble(Pubblicazioni::getNumeroPagine)
                        .average()
                        .orElse(0);
                System.out.println("Media del numero di pagine: " + Math.floor(mediaPagine));

                System.out.println("Vuoi modificare il catalogo? s/n");
                String scelta3 = scanner.next().toLowerCase();
                switch (scelta3) {
                    case "s":
                        Modifica.modifica();
                        break;
                    case "n":
                        avviaProgramma();
                        break;
                    default:
                        System.out.println("Comando non valido");
                        avviaProgramma();
                        break;
                };
            break;
            case "0":
                System.out.println("Chiusura in corso...");
                System.exit(0);
                break;
            default:
                System.out.println("Comando non trovato");
                break;
        }
    }
}
