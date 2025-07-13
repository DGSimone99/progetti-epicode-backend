package it.epicode.gestioneViaggi.viaggio;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ViaggioService {
    @Autowired
    private ViaggioRepository viaggioRepository;

    public StatoViaggio statoViaggio(LocalDate dataPartenza) {
        if (LocalDate.now().isAfter(dataPartenza)) {
            return StatoViaggio.COMPLETATO;
        } else {
            return StatoViaggio.IN_PROGRAMMA;
        }
    }

    public Viaggio createViaggio(Viaggio viaggio) {
        viaggio.setStatoViaggio(statoViaggio(viaggio.getDataPartenza()));
        return viaggioRepository.save(viaggio);
    }

    public Viaggio updateViaggio(Viaggio viaggio) {
        viaggio.setStatoViaggio(statoViaggio(viaggio.getDataPartenza()));
        return viaggioRepository.save(viaggio);
    }

    public Viaggio getViaggioById(Long id) {
        return viaggioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Viaggio non trovato"));
    }

    public List<Viaggio> getAllViaggi() {
        return viaggioRepository.findAll();
    }

    public void deleteViaggio(Long id) {
        Viaggio viaggio = getViaggioById(id);
        viaggioRepository.delete(viaggio);
    }

    public Viaggio aggiornaStato(Long id, StatoViaggio nuovoStato) {
        Viaggio viaggio = getViaggioById(id);
        viaggio.setStatoViaggio(nuovoStato);
        return viaggioRepository.save(viaggio);
    }
}
