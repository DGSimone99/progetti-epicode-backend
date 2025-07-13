package it.s2l5.programma;

import java.util.Scanner;

import static it.s2l5.programma.Aggiungi.aggiungi;
import static it.s2l5.programma.Archivio.archivio;

public class Modifica {
    public static void modifica() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Scegli il tipo di modifica che vuoi effettuare:");
        System.out.println("1 - Aggiungi al catalogo");
        System.out.println("2 - Rimuovi dal catalogo");
        System.out.println("3 - Modifica dal catalogo");
        System.out.println("N - Torna indietro");
        System.out.println("0 - Chiudi terminale");

        String modifica = scanner.next().toLowerCase();

        switch (modifica) {
            case "1":
                aggiungi();
                break;
            case "2":
                while (true) {
                    System.out.println("Inserisci ISBN da rimuovere");
                    long removeIsbn = scanner.nextLong();

                    if (!archivio.stream().anyMatch(elemento -> elemento.getIsbn() == removeIsbn)) {
                        System.out.println("ISBN non trovato");
                    } else {
                        Archivio.rimuoviPerIsbn(removeIsbn);
                        System.out.println("Pubblicazione rimossa con successo!");
                        System.out.println("----------------------------");
                        break;
                    }
                }
                AvviaProgramma.avviaProgramma();
                break;

            case "3":
                while (true) {
                    System.out.println("Inserisci l'ISBN della pubblicazione che vuoi modificare:");
                    long modificaIbsn = scanner.nextLong();

                    if (!archivio.stream().anyMatch(elemento -> elemento.getIsbn() == modificaIbsn)) {
                        System.out.println("ISBN non trovato");
                    } else {
                        Aggiorna.aggiorna(modificaIbsn);
                        break;
                    }
                }
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
        }
    }

