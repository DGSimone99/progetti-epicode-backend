package com.BackendS7L5.evento;

import com.BackendS7L5.auth.user.AppUser;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Evento")

public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 50, nullable = false)
    private String titolo;
    @Column(length = 150, nullable = false)
    private String descrizione;
    @Column(length = 50, nullable = false)
    private String luogo;

    @Column(nullable = false)
    private LocalDate data;

    @Column(name = "posti_disponibili", nullable = false)
    private int postiDisponibili;

    @Column(name = "posti_prenotati", nullable = false)
    private int postiPrenotati;

    @ManyToOne
    @JoinColumn(name = "organizzatore_id")
    private AppUser organizzatore;
}