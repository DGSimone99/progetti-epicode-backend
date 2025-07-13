package catalogo.pubblicazioni;

import catalogo.enums.Genere;
import catalogo.enums.Tipologia;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Data

@Entity
@Table(name = "libri")
public class Libro extends Pubblicazione {
    @Getter
    @Setter

    @Column(length = 50, nullable = false)
    private String autore;

    @Enumerated(EnumType.STRING)
    private Genere genere;

    public Libro() {}
    public Libro(String titolo, int annoPubblicazione, int numeroPagine, String autore, Genere genere) {
        super(titolo, annoPubblicazione, numeroPagine, Tipologia.LIBRO);
        this.autore = autore;
        this.genere = genere;
    }

    public static Libro libro1 = new Libro("Il Signore degli Anelli", 1954, 1000,"J.R.R. Tolkien", Genere.FANTASY);
    public static Libro libro2 = new Libro("Il Nome della Rosa", 1980, 500, "Umberto Eco", Genere.GIALLO);

    public static List<Libro> libri = List.of(libro1, libro2);

    @Override
    public String toString() {
        return "Libro{" +
                "isbn=" + getIsbn() +
                ", titolo= " + getTitolo() + '\'' +
                ", annoPubblicazione=" + getAnnoPubblicazione() +
                ", numeroPagine=" + getNumeroPagine() +
                ", tipologia=" + getTipologia() +
                ", autore='" + autore + '\'' +
                ", genere=" + genere +
                '}';
    }
}
