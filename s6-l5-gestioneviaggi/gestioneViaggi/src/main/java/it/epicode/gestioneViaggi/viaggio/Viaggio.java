package it.epicode.gestioneViaggi.viaggio;

import it.epicode.gestioneViaggi.dipendente.Dipendente;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Viaggi")

public class Viaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Il campo 'destinazione' non può essere vuoto.")
    private String destinazione;

    @Column(name="data_partenza", nullable = false, length = 50)
    @NotNull(message = "La data di partenzza è obbligatoria.")
    private LocalDate dataPartenza;

    @Enumerated(EnumType.STRING)
    private StatoViaggio statoViaggio;

    @Override
    public String toString() {
        return id + " - " + destinazione + " - " + dataPartenza + " - " + statoViaggio;
    }
}