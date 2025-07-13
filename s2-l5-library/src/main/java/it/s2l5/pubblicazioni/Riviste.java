package it.s2l5.pubblicazioni;

import it.s2l5.enums.Periodicità;
import it.s2l5.enums.Tipologia;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

public class Riviste extends Pubblicazioni {
    @Getter
    @Setter
    private Periodicità periodicità;

    public Riviste(long isbn, String titolo, int AnnoPubblicazione, int numeroPagine, Periodicità periodicità) {
        super(isbn, titolo, AnnoPubblicazione, numeroPagine);
        this.periodicità = periodicità;
        this.tipologia = Tipologia.RIVISTA;
    }

    public static List<Riviste> riviste = Arrays.asList (
        new Riviste(201L, "National Geographic", 2021, 120, Periodicità.MENSILE),
        new Riviste(202L, "Time", 2021, 80, Periodicità.SETTIMANALE),
        new Riviste(203L, "Vogue", 2021, 150, Periodicità.SEMESTRALE)
    );
}
