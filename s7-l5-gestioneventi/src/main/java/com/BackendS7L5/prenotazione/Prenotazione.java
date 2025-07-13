package com.BackendS7L5.prenotazione;

import com.BackendS7L5.auth.user.AppUser;
import com.BackendS7L5.evento.Evento;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Prenotazione")

public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "partecipante_id")
    private AppUser partecipante;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;
}