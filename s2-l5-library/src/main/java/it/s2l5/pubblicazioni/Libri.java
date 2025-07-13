package it.s2l5.pubblicazioni;

import it.s2l5.enums.Genere;
import it.s2l5.enums.Tipologia;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

public class Libri extends Pubblicazioni {
    @Getter
    @Setter
    private String autore;


    public Libri(long isbn, String titolo, int AnnoPubblicazione, int numeroPagine, String autore, Genere genere) {
        super(isbn, titolo, AnnoPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
        this.tipologia = Tipologia.LIBRO;
    }

    public static List<Libri> libri = Arrays.asList(
            new Libri(101L, "Il Signore degli Anelli", 1954, 423, "J.R.R. Tolkien", Genere.FANTASY),
            new Libri(101L, "Lo Hobbit", 1954, 400, "J.R.R. Tolkien", Genere.FANTASY),
            new Libri(102L, "Il Nome della Rosa", 1980, 502, "Umberto Eco", Genere.ROMANZO),
            new Libri(103L, "La Divina Commedia", 1321, 100, "Dante Alighieri", Genere.POESIA)
    );
}
