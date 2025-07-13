package it.epicode.gestioneViaggi.prenotazione;

import it.epicode.gestioneViaggi.dipendente.Dipendente;
import it.epicode.gestioneViaggi.viaggio.Viaggio;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Prenotazione")

public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull(message = "Il campo 'viaggio' non può essere vuoto.")
    @ManyToOne
    private Viaggio viaggio;

    @NotNull(message = "Il campo 'dipendente' non può essere vuoto.")
    @ManyToOne
    private Dipendente dipendente;

    @NotNull(message = "La data di prenotazione è obbligatoria.")
    @Column(name="data_prenotazione", nullable = false, length = 50)
    private LocalDate dataPrenotazione;

    @Column(length = 200)
    private String note;

    @Override
    public String toString() {
        return  id +
                " - Destinazione: " + viaggio.getDestinazione() +
                " - Data: " + viaggio.getDataPartenza() +
                " - Dipendente: " + dipendente.getNome() + " " + dipendente.getCognome() +
                " - Note: " + note;
    }
}