package it.s2l5.pubblicazioni;

import it.s2l5.enums.Genere;
import it.s2l5.enums.Periodicità;
import it.s2l5.enums.Tipologia;
import lombok.Data;

import java.time.LocalDate;

@Data
public abstract class Pubblicazioni {
    protected long isbn;
    protected String titolo;
    protected int AnnoPubblicazione;
    protected int numeroPagine;
    protected Tipologia tipologia;
    protected String autore;
    protected Genere genere;
    protected Periodicità periodicità;


    public Pubblicazioni(long isbn, String titolo, int AnnoPubblicazione, int numeroPagine) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.AnnoPubblicazione = AnnoPubblicazione;
        this.numeroPagine = numeroPagine;
        this.tipologia = tipologia;
    }
}
