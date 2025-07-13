package it.epicode.gestioneViaggi.prenotazione;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrenotazioneSelectRequest {
    private Long viaggioId;
    private Long dipendenteId;
}
