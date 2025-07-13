package it.s2l5.programma;

import it.s2l5.enums.Genere;
import it.s2l5.enums.Periodicità;
import it.s2l5.enums.Tipologia;
import it.s2l5.pubblicazioni.Pubblicazioni;

import java.util.Scanner;

import static it.s2l5.programma.Archivio.archivio;

public class Aggiorna {
    public static void aggiorna(long isbn) {
        Scanner scanner = new Scanner(System.in);

        Pubblicazioni modificaPubblicazione;
        modificaPubblicazione = archivio.stream()
                    .filter(pubblicazione -> pubblicazione.getIsbn() == isbn)
                    .findFirst()
                    .orElse(null);

        System.out.println("Pubblicazione trovata: " + modificaPubblicazione.getTitolo());
        System.out.println("Cosa vuoi aggiornare?");
        System.out.println("1 - Titolo");
        System.out.println("2 - Anno di pubblicazione");
        System.out.println("3 - Numero di pagine");
        if(modificaPubblicazione.getTipologia().equals(Tipologia.LIBRO)) {
            System.out.println("4 - Autore");
            System.out.println("5 - Genere");
        } else {
            System.out.println("4 - Periodicità");
        }
        System.out.println("N - Annulla");

        String scelta = scanner.next().toLowerCase();

        switch (scelta) {
            case "1":
                System.out.println("Inserisci il nuovo titolo:");
                scanner.nextLine();
                String nuovoTitolo = scanner.nextLine();
                modificaPubblicazione.setTitolo(nuovoTitolo);
                System.out.println("Titolo aggiornato con successo.");
                break;

            case "2":
                System.out.println("Inserisci il nuovo anno di pubblicazione:");
                int nuovoAnno = scanner.nextInt();
                modificaPubblicazione.setAnnoPubblicazione(nuovoAnno);
                System.out.println("Anno di pubblicazione aggiornato con successo.");
                break;

            case "3":
                System.out.println("Inserisci il nuovo numero di pagine:");
                int nuovePagine = scanner.nextInt();
                modificaPubblicazione.setNumeroPagine(nuovePagine);
                System.out.println("Numero di pagine con successo.");
                break;

            case "4":
                if(modificaPubblicazione.getTipologia().equals(Tipologia.LIBRO)) {
                    System.out.println("Inserisci il nuovo autore:");
                    String nuovoAutore = scanner.next();
                    modificaPubblicazione.setAutore(nuovoAutore);
                    System.out.println("Autore con successo.");
                    break;
                } else {
                    while (true) {
                        System.out.println("Inserisci la nuova periodicità:");
                        System.out.println("1 - Settiminale");
                        System.out.println("2 - Mensile");
                        System.out.println("3 - Semestrale");

                        String sceltaPeriodicità = scanner.next();
                        String periodicità = "SETTIMANALE";

                        switch (sceltaPeriodicità) {
                            case "1" -> periodicità = "SETTIMANALE";
                            case "2" -> periodicità = "MENSILE";
                            case "3" -> periodicità = "SEMESTRALE";
                            default -> {
                                System.out.println("Comando non valido");
                                continue;
                            }
                        }
                        modificaPubblicazione.setPeriodicità(Periodicità.valueOf(periodicità.toUpperCase()));
                        System.out.println("Periodicità con successo.");
                        break;
                    }
                }

            case "5":
                if(modificaPubblicazione.getTipologia().equals(Tipologia.LIBRO)) {
                    while (true) {
                        System.out.println("Inserisci il nuovo Genere:");
                        System.out.println("Generi disponibili: 1 - ROMANZO, 2 - FANTASY, 3 - DRAMA, 4 - AVVENTURA, 5- COMMEDIA, 6 - POESIA");
                        String nuovoGenere = scanner.next();
                        switch (nuovoGenere) {
                            case "1" -> nuovoGenere = "ROMANZO";
                            case "2" -> nuovoGenere = "FANTASY";
                            case "3" -> nuovoGenere = "DRAMA";
                            case "4" -> nuovoGenere = "AVVENTURA";
                            case "5" -> nuovoGenere = "COMMEDIA";
                            case "6" -> nuovoGenere = "POESIA";
                            default -> {
                                System.out.println("Genere non valido.");
                                continue;
                            }
                        }
                        modificaPubblicazione.setGenere(Genere.valueOf(nuovoGenere.toUpperCase()));
                        System.out.println("Genere aggiornato con successo.");
                    }
                } else {
                    System.out.println("Comando non valido.");
                    Modifica.modifica();
                }
                break;

            case "n":
                Modifica.modifica();
                break;

            case "0": System.exit(0);

            default:
                System.out.println("Comando non valido.");
                Modifica.modifica();
                break;
        }
    }
}
