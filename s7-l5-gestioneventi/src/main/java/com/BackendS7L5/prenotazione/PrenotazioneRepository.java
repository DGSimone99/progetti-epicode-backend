package com.BackendS7L5.prenotazione;


import com.BackendS7L5.auth.user.AppUser;
import com.BackendS7L5.evento.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    List<Prenotazione> findByPartecipante(AppUser partecipante);
    boolean existsByEventoAndPartecipante(Evento evento, AppUser partecipante);
    void deleteByEventoAndPartecipante(Evento evento, AppUser partecipante);
}