package catalogo.pubblicazioni;

import catalogo.enums.Periodicità;
import catalogo.enums.Tipologia;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Data

@Entity

@Table(name = "riviste")
public class Rivista extends Pubblicazione {
    @Getter
    @Setter

    @Enumerated(EnumType.STRING)
    private Periodicità periodicità;

    public Rivista() {}
    public Rivista(String titolo, int annoPubblicazione, int numeroPagine, Periodicità periodicità) {
        super(titolo, annoPubblicazione, numeroPagine, Tipologia.RIVISTA);
        this.periodicità = periodicità;
    }

    public static Rivista rivista1 = new Rivista("National Geographic", 2023, 100, Periodicità.MENSILE);
    public static Rivista rivista2 = new Rivista("Time", 2023, 80, Periodicità.SETTIMANALE);
    public static Rivista rivista3 = new Rivista("Scientific American", 2023, 120, Periodicità.SEMESTRALE);

    public static List<Rivista> riviste = List.of(rivista1, rivista2, rivista3);

    @Override
    public String toString() {
        return "Rivista{" +
                "isbn=" + getIsbn() +
                ", titolo='" + getTitolo() + '\'' +
                ", annoPubblicazione=" + getAnnoPubblicazione() +
                ", numeroPagine=" + getNumeroPagine() +
                ", tipologia=" + getTipologia() +
                ", periodicità=" + periodicità +
                '}';
    }
}
