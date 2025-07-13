package catalogo.prestiti;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data

@Entity
@Table(name = "utenti")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long numeroTessera;

    @Column(length = 50, nullable = false)
    private String nome;

    @Column(length = 50, nullable = false)
    private String cognome;

    private LocalDate dataDiNascita;

    @OneToMany(mappedBy = "utente")
    private List<Prestito> prestiti;

    public Utente() {}
    public Utente(String nome, String cognome, LocalDate dataDiNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.prestiti = new ArrayList<>();
    }

    public static Utente utente1 = new Utente("Mario", "Rossi", LocalDate.of(1990, 5, 15));
    public static Utente utente2 = new Utente("Luigi", "Verdi", LocalDate.of(1985, 3, 22));
    public static Utente utente3 = new Utente("Giulia", "Bianchi", LocalDate.of(1992, 8, 30));

    public static List<Utente> utenti = List.of(utente1, utente2, utente3);
}
