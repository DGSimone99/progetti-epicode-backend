package com.BackendS7L5.prenotazione;

import com.BackendS7L5.auth.user.AppUser;
import com.BackendS7L5.evento.Evento;
import com.BackendS7L5.evento.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {
    @Autowired
    private PrenotazioneService prenotazioneService;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    public List<PrenotazioneResponse> getPrenotazioniPerPartecipante(@AuthenticationPrincipal AppUser user) {
        return prenotazioneService.getPrenotazioniPerPartecipante(user);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPrenotazione(@RequestBody PrenotazioneDTO prenotazioneDTO, @AuthenticationPrincipal AppUser user) {
        Evento evento = eventoService.getEvento(prenotazioneDTO.getIdEvento());
        prenotazioneService.createPrenotazione(evento, user);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePrenotazione(@PathVariable Long id, @AuthenticationPrincipal AppUser user) {
        Prenotazione prenotazione = prenotazioneService.getPrenotazioneById(id);
        if (!prenotazione.getPartecipante().getId().equals(user.getId())) {
            throw new AccessDeniedException("Utente non autorizzato a eliminare questa prenotazione.");
        }
        prenotazioneService.deletePrenotazione(prenotazione); // ✅ passa direttamente l'oggetto
    }
}
