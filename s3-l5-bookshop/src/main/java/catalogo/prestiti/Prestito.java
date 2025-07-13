package catalogo.prestiti;

import catalogo.pubblicazioni.Pubblicazione;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

import static catalogo.prestiti.Utente.*;
import static catalogo.pubblicazioni.Libro.libro1;
import static catalogo.pubblicazioni.Libro.libro2;

import java.util.List;

@Data

@Entity
@Table(name = "prestiti")
public class Prestito {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utente;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "pubblicazione_id", nullable = false)
    private Pubblicazione pubblicazione;

    private LocalDate dataPrestito;

    private LocalDate dataRestituzionePrevista;
    private LocalDate dataRestituzione;


    public Prestito() {}
    public Prestito(Utente utente, Pubblicazione pubblicazione, LocalDate dataPrestito, LocalDate dataRestituzione) {
        this.utente = utente;
        this.pubblicazione = pubblicazione;
        this.dataPrestito = dataPrestito;
        this.dataRestituzionePrevista = dataPrestito.plusDays(30);
        this.dataRestituzione = dataRestituzione;
    }

    public static Prestito prestito1 = new Prestito(utente1, libro1, LocalDate.of(2025, 1, 12),null);
    public static Prestito prestito2 = new Prestito(utente1, libro2, LocalDate.of(2025, 3, 15),null);
    public static Prestito prestito3 = new Prestito(utente3, libro1, LocalDate.of(2025, 3, 18), LocalDate.of(2025, 3, 25));

    public static List<Prestito> prestiti = List.of(prestito1, prestito2, prestito3);

    @Override
    public String toString() {
        return "Prestito{" +
                "id=" + id +
                ", utente=" + utente.getNome() + " " + utente.getCognome() +
                ", pubblicazione=" + pubblicazione.getTitolo() +
                ", dataPrestito=" + dataPrestito +
                ", dataRestituzionePrevista=" + dataRestituzionePrevista +
                ", dataRestituzione=" + dataRestituzione +
                '}';
    }
}
