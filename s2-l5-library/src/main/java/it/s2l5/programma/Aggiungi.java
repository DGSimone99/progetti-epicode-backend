package it.s2l5.programma;

import it.s2l5.enums.Genere;
import it.s2l5.enums.Periodicità;
import it.s2l5.pubblicazioni.Libri;
import it.s2l5.pubblicazioni.Riviste;

import java.time.LocalDate;
import java.util.Scanner;

import static it.s2l5.programma.Archivio.aggiungiPubblicazione;
import static it.s2l5.programma.Archivio.archivio;

public class Aggiungi {
    public static void aggiungi() {
        Scanner scanner = new Scanner(System.in);

        String pubblicazione = null;
        while (true) {
            System.out.println("Che tipo di pubblicazione vuoi inserire:");
            System.out.println("1 - Libro");
            System.out.println("2 - Rivista");
            pubblicazione = scanner.next();

            if(!pubblicazione.equals("1") && !pubblicazione.equals("2")) {
                System.out.println("Comando non valido");
            } else {
                break;
            }
        }
        
        long isbn = 0;
        while (true) {
            System.out.println("Inserisci codice ISBN:");
            isbn = scanner.nextLong();

            long isbnInput = isbn;
            if (archivio.stream().anyMatch(elemento -> elemento.getIsbn() == isbnInput)) {
                System.out.println("ISBN già utilizzato");
            } else {
                break;
            }
        }

        System.out.println("Inserisci il titolo:");
        scanner.nextLine();
        String titolo = scanner.nextLine();

        int anno = 0;
        while (true) {
            System.out.println("Inserisci anno di pubblicazione:");
            anno = scanner.nextInt();

            if (anno > LocalDate.now().getYear()) {
                System.out.println("Anno non disponibile");
            } else {
                break;
            }
        };

        System.out.println("Inserisci numero di pagine:");
        int pagine = scanner.nextInt();

        switch (pubblicazione) {
            case "1":
                System.out.println("Inserisci Autore:");
                scanner.nextLine();
                String autore = scanner.nextLine();
                String genere = "";

                while (true) {
                    System.out.println("Inserisci Genere:");
                    System.out.println("Generi disponibili: 1 - ROMANZO, 2 - FANTASY, 3 - DRAMA, 4 - AVVENTURA, 5- COMMEDIA, 6 - POESIA");
                    String sceltaGenere = scanner.next();
                    switch (sceltaGenere) {
                        case "1" -> genere = "ROMANZO";
                        case "2" -> genere = "FANTASY";
                        case "3" -> genere = "DRAMA";
                        case "4" -> genere = "AVVENTURA";
                        case "5" -> genere = "COMMEDIA";
                        case "6" -> genere = "POESIA";
                        default -> {
                            System.out.println("Genere non valido.");
                            continue;
                        }
                    }
                    Libri nuovoLibro = new Libri(isbn, titolo, anno,pagine, autore, Genere.valueOf(genere));
                    aggiungiPubblicazione(nuovoLibro);
                    System.out.println("Libro aggiunto con successo!");
                    System.out.println("----------------------------");
                    AvviaProgramma.avviaProgramma();
                    break;
                }
            case "2": System.out.println("Scegli periodicità:");
                while (true) {
                    System.out.println("1 - Settiminale");
                    System.out.println("2 - Mensile");
                    System.out.println("3 - Semestrale");

                    String sceltaPeriodicità = scanner.next();
                    String periodicità = "SETTIMANALE";

                    switch(sceltaPeriodicità) {
                        case "1" -> periodicità = "SETTIMANALE";
                        case "2" -> periodicità = "MENSILE";
                        case "3" -> periodicità = "SEMESTRALE";
                        default -> {
                            System.out.println("Comando non valido");
                            continue;
                        }
                    }
                    Riviste nuovaRivista = new Riviste(isbn, titolo, anno,pagine, Periodicità.valueOf(periodicità));
                    aggiungiPubblicazione(nuovaRivista);
                    System.out.println("Rivista aggiunta con successo!");
                    System.out.println("----------------------------");
                    AvviaProgramma.avviaProgramma();
                    break;
                }
        }
    }
}
