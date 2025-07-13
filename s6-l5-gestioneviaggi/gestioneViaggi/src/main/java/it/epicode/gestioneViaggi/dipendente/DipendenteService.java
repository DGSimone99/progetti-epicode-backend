package it.epicode.gestioneViaggi.dipendente;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DipendenteService {
    @Autowired
    private DipendenteRepository dipendenteRepository;

    public Dipendente createDipendente(Dipendente dipendente) {
        return dipendenteRepository.save(dipendente);
    }

    public Dipendente updateDipendente(Dipendente dipendente) {
        return dipendenteRepository.save(dipendente);
    }

    public Dipendente getDipendenteById(Long id) {
        return dipendenteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato"));
    }

    public List<Dipendente> getAllDipendenti() {
        return dipendenteRepository.findAll();
    }

    public void deleteDipendente(Long id) {
        if (!dipendenteRepository.existsById(id)) {
            throw new EntityNotFoundException("Dipendente con ID " + id + " non trovato.");
        }
        dipendenteRepository.deleteById(id);
    }
}
