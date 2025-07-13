package com.BackendS7L5.prenotazione;

import com.BackendS7L5.evento.EventoResponse;
import lombok.Data;

@Data
public class PrenotazioneResponse {
    private long id;
    private String partecipante;
    private EventoResponse evento;
}
