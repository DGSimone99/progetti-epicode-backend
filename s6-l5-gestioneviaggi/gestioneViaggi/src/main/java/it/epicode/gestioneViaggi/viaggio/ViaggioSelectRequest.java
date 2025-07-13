package it.epicode.gestioneViaggi.viaggio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViaggioSelectRequest {
    private Long id;
    private StatoViaggio statoViaggio;
}
