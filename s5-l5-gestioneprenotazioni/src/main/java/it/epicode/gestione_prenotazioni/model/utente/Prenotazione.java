package it.epicode.gestione_prenotazioni.model.utente;

import it.epicode.gestione_prenotazioni.model.postazione.Postazione;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@Table(name = "prenotazioni")

public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, name="prenotazione_in_corso")
    private boolean attiva;

    @Column(nullable = false, name="data_prenotazione")
    private LocalDate dataPrenotazione;

    @ManyToOne
    private Utente utente;

    @ManyToOne
    private Postazione postazione;

    public Prenotazione(LocalDate dataPrenotazione, Utente utente, Postazione postazione) {
        this.dataPrenotazione = dataPrenotazione;
        this.utente = utente;
        this.postazione = postazione;
    }
}