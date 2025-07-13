package it.epicode.gestioneViaggi.prenotazione;

import it.epicode.gestioneViaggi.dipendente.Dipendente;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {
    @Autowired
    private PrenotazioneService prenotazioneService;

    @GetMapping("/{id}")
    public Prenotazione getPrenotazioneById(@PathVariable Long id) {
        return prenotazioneService.getPrenotazioneById(id);
    }

    @GetMapping
    public List<Prenotazione> getAllPrenotazioni() {
        return prenotazioneService.getAllPrenotazioni();
    }

    @PostMapping
    public Prenotazione createPrenotazione(@Valid @RequestBody Prenotazione prenotazione) {
        return prenotazioneService.createPrenotazione(prenotazione);
    }

    @PutMapping("/{id}")
    public Prenotazione updatePrenotazione(@PathVariable Long id, @Valid @RequestBody Prenotazione prenotazione) {
        prenotazione.setId(id);
        return prenotazioneService.updatePrenotazione(prenotazione);
    }

    @DeleteMapping("/{id}")
    public void deletePrenotazione(@PathVariable Long id) {
        prenotazioneService.deletePrenotazione(id);
    }

    @PostMapping("/assegna")
    public Prenotazione assegnaDipendenteAViaggio(@RequestBody PrenotazioneSelectRequest prenotazione) {
        return prenotazioneService.assegnaDipendenteAViaggio(prenotazione.getViaggioId(), prenotazione.getDipendenteId());
    }
}