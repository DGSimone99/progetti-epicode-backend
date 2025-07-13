package it.s2l5.programma;

import it.s2l5.enums.Tipologia;
import it.s2l5.exceptions.PubblicazioneNonTrovataException;
import it.s2l5.pubblicazioni.Pubblicazioni;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Archivio {
    public static Set<Pubblicazioni> archivio = new HashSet<>();

    public static void aggiungiPubblicazione(Pubblicazioni pubblicazione) {
        if (!archivio.stream().anyMatch(elemento -> elemento.getIsbn() == pubblicazione.getIsbn())) {
            archivio.add(pubblicazione);
        }
    }

    public static void ricercaPerIsbn(long isbn) throws PubblicazioneNonTrovataException {
        Pubblicazioni pubblicazionePerIsbn = archivio.stream()
                .filter(pubblicazione -> pubblicazione.getIsbn() == isbn)
                .findAny()
                .orElseThrow(() -> new PubblicazioneNonTrovataException("ISBN non trovato"));

            System.out.println("Pubblicazione trovata:");
            System.out.println("Titolo: " + pubblicazionePerIsbn.getTitolo());
            System.out.println("Tipologia: " + pubblicazionePerIsbn.getTipologia());
            System.out.println("Anno di Pubblicazione: " + pubblicazionePerIsbn.getAnnoPubblicazione());
            System.out.println("Numero pagine: " + pubblicazionePerIsbn.getNumeroPagine());
            if (pubblicazionePerIsbn.getTipologia().equals(Tipologia.LIBRO)) {
                System.out.println("Autore: " + pubblicazionePerIsbn.getAutore());
                System.out.println("Genere: " + pubblicazionePerIsbn.getGenere());
            } else {
                System.out.println("Periodicità: " + pubblicazionePerIsbn.getPeriodicità());
        }
    }

    public static void ricercaPerData(int data) throws PubblicazioneNonTrovataException {
        List<Pubblicazioni> pubblicazioniPerData = archivio.stream().filter(pubblicazione -> pubblicazione.getAnnoPubblicazione() == data).
                collect((Collectors.toList()));

                if(pubblicazioniPerData.isEmpty()) {
                    throw new PubblicazioneNonTrovataException("Nessuna pubblicazione nell'anno " + data);
                } else {
                    System.out.println("Pubblicazioni nell'anno " + data + ":");
                    pubblicazioniPerData.forEach(pubblicazione -> System.out.println("- " + pubblicazione.getTitolo()));
                }
    }

    public static void ricercaPerAutore(String autore) throws PubblicazioneNonTrovataException {
        List<Pubblicazioni> libriPerAutore = archivio.stream().filter(pubblicazione -> pubblicazione.getAutore() != null && pubblicazione.getAutore().toLowerCase().contains(autore)).
                collect((Collectors.toList()));

                if(libriPerAutore.isEmpty()) {
                    throw new PubblicazioneNonTrovataException("Autore non trovato");
                } else {
                    System.out.println("Ricerca autore: " + autore + ":");
                    libriPerAutore.forEach(pubblicazioni ->
                        System.out.println("- " + pubblicazioni.getTitolo() + " - Autore: " + pubblicazioni.getAutore()));
                    }
    }

    public static void rimuoviPerIsbn(long isbn) {
        archivio.removeIf(pubblicazione -> pubblicazione.getIsbn() == isbn);
    }
}


