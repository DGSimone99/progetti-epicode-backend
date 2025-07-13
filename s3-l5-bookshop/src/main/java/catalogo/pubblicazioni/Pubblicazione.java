package catalogo.pubblicazioni;

import catalogo.enums.Tipologia;
import catalogo.prestiti.Prestito;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "pubblicazioni")
public abstract class Pubblicazione {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected long isbn;

    @Column(length = 50, nullable = false)
    protected String titolo;

    @OneToMany(mappedBy = "pubblicazione")
    private List<Prestito> prestiti;

    protected int annoPubblicazione;
    protected int numeroPagine;
    protected Tipologia tipologia;

    public Pubblicazione() {}
    public Pubblicazione(String titolo, int annoPubblicazione, int numeroPagine, Tipologia tipologia) {
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
        this.tipologia = tipologia;
    }
}
