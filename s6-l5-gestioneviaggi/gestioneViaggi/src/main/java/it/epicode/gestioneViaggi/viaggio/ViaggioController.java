package it.epicode.gestioneViaggi.viaggio;

import it.epicode.gestioneViaggi.dipendente.Dipendente;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {
    @Autowired
    private ViaggioService viaggioService;

    @GetMapping("/{id}")
    public Viaggio getViaggioById(@PathVariable Long id) {
        return viaggioService.getViaggioById(id);
    }

    @GetMapping
    public List<Viaggio> getAllViaggi() {
        return viaggioService.getAllViaggi();
    }

    @PostMapping
    public Viaggio createViaggio(@Valid @RequestBody Viaggio viaggio) {
        viaggio.setStatoViaggio(viaggioService.statoViaggio(viaggio.getDataPartenza()));
        return viaggioService.createViaggio(viaggio);
    }

    @PutMapping("/{id}")
    public Viaggio updateViaggio(@PathVariable Long id, @Valid @RequestBody Viaggio viaggio) {
        viaggio.setId(id);
        return viaggioService.updateViaggio(viaggio);
    }

    @DeleteMapping("/{id}")
    public void deleteViaggio(@PathVariable Long id) {
        viaggioService.deleteViaggio(id);
    }

    @PutMapping("/{id}/stato")
    public Viaggio aggiornaStato(@PathVariable Long id, @RequestBody ViaggioSelectRequest viaggio) {
        return viaggioService.aggiornaStato(id, viaggio.getStatoViaggio());
    }
}
