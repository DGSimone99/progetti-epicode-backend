package it.s2l5.programma;

import it.s2l5.exceptions.DatoNonValidoException;
import it.s2l5.exceptions.PubblicazioneNonTrovataException;

import java.util.Scanner;

public class Ricerca {
    public static void ricerca() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Scegli criterio di ricerca");
            System.out.println("1 - Ricerca per codice ISBN");
            System.out.println("2 - Ricerca per autore");
            System.out.println("3 - Ricerca per anno di pubblicazione");
            System.out.println("N - Torna indietro");
            System.out.println("0 - Chiudi terminale");

            String ricerca = scanner.next().toLowerCase();

            switch (ricerca) {
                case "1":
                    System.out.println("Inserisci codice ISBN:");
                    long isbn = scanner.nextLong();
                    Archivio.ricercaPerIsbn(isbn);
                    break;
                case "2":
                    System.out.println("Inserisci nome dell'autore:");
                    String autore = scanner.next().toLowerCase();
                    Archivio.ricercaPerAutore(autore);
                    break;
                case "3":
                    System.out.println("Inserisci l'anno di pubblicazione");
                    int anno = scanner.nextInt();
                    Archivio.ricercaPerData(anno);
                    break;
                case "n": AvviaProgramma.avviaProgramma();
                    break;

                case "0":
                    System.out.println("Chiusura in corso...");
                    System.exit(0);
                default:
                    System.out.println("Comando non trovato");
                    break;
            }
        } catch (
                PubblicazioneNonTrovataException e) {
            System.out.println(e.getMessage());
        }
    }
}
