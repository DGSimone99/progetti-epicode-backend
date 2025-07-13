package it.epicode.gestione_prenotazioni.model.postazione;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "postazioni")
public class Postazione {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(nullable = false, length = 50)
    private String descrizione;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "tipo_postazione")
    private TipoPostazione tipoPostazione;

    @ManyToOne
    private Edificio edificio;

    private int capacita;

    public Postazione(String descrizione, TipoPostazione tipoPostazione, int capacita, Edificio edificio) {
        this.descrizione = descrizione;
        this.tipoPostazione = tipoPostazione;
        this.capacita = capacita;
        this.edificio = edificio;
    }
}
