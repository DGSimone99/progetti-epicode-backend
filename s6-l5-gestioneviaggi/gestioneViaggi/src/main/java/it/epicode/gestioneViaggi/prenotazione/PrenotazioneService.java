package it.epicode.gestioneViaggi.prenotazione;

import it.epicode.gestioneViaggi.dipendente.Dipendente;
import it.epicode.gestioneViaggi.dipendente.DipendenteService;
import it.epicode.gestioneViaggi.viaggio.Viaggio;
import it.epicode.gestioneViaggi.viaggio.ViaggioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private ViaggioService viaggioService;

    @Autowired
    private DipendenteService dipendenteService;

    public Prenotazione createPrenotazione(Prenotazione prenotazione) {
        return prenotazioneRepository.save(prenotazione);
    }

    public Prenotazione updatePrenotazione(Prenotazione prenotazione) {
        getPrenotazioneById(prenotazione.getId());
        return prenotazioneRepository.save(prenotazione);
    }

    public Prenotazione getPrenotazioneById(Long id) {
        return prenotazioneRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Prenotazione non trovata"));
    }

    public List<Prenotazione> getAllPrenotazioni() {
        return prenotazioneRepository.findAll();
    }

    public void deletePrenotazione(Long id) {
        Prenotazione prenotazione = getPrenotazioneById(id);
        prenotazioneRepository.delete(prenotazione);
    }

    public Prenotazione assegnaDipendenteAViaggio(Long viaggioId, Long dipendenteId) {
        Viaggio viaggio = viaggioService.getViaggioById(viaggioId);
        Dipendente dipendente = dipendenteService.getDipendenteById(dipendenteId);

        if (prenotazioneRepository.existsByDipendenteAndViaggio_DataPartenza(dipendente, viaggio.getDataPartenza())) {
            throw new IllegalStateException("Il dipendente ha già una prenotazione per quella data.");
        }

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setDipendente(dipendente);
        prenotazione.setViaggio(viaggio);
        prenotazione.setDataPrenotazione(LocalDate.now());

        return prenotazioneRepository.save(prenotazione);
    }

    public List<Prenotazione> getPrenotazioniByDipendente(Dipendente dipendente) {
        return prenotazioneRepository.findAllByDipendente(dipendente);
    }
}
