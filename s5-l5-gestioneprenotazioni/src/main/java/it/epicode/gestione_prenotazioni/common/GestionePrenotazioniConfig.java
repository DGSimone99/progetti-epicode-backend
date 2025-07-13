package it.epicode.gestione_prenotazioni.common;

import it.epicode.gestione_prenotazioni.model.postazione.Edificio;
import it.epicode.gestione_prenotazioni.model.postazione.Postazione;
import it.epicode.gestione_prenotazioni.model.postazione.TipoPostazione;
import it.epicode.gestione_prenotazioni.model.utente.Prenotazione;
import it.epicode.gestione_prenotazioni.model.utente.Utente;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class GestionePrenotazioniConfig {
    // UTENTI
    @Bean
    public Utente utente1() {
        Utente utente1 = new Utente();
        utente1.setUsername("luca.rossi");
        utente1.setNomeCompleto("Luca Rossi");
        utente1.setEmail("luca.rossi@example.com");
        return utente1;
    }

    @Bean
    public Utente utente2() {
        Utente utente2 = new Utente();
        utente2.setUsername("marta.bianchi");
        utente2.setNomeCompleto("Marta Bianchi");
        utente2.setEmail("marta.bianchi@example.com");
        return utente2;
    }

    // EDIFICI
    @Bean
    public Edificio edificio1() {
        Edificio edificio1 = new Edificio();
        edificio1.setNome("Edificio A");
        edificio1.setIndirizzo("Via Roma 1");
        edificio1.setCitta("Roma");
        return edificio1;
    }

    @Bean
    public Edificio edificio2() {
        Edificio edificio2 = new Edificio();
        edificio2.setNome("Edificio B");
        edificio2.setIndirizzo("Via Roma 2");
        edificio2.setCitta("Milano");
        return edificio2;
    }

    // POSTAZIONI
    @Bean
    public Postazione postazione1(Edificio edificio1) {
        Postazione postazione1 = new Postazione();
        postazione1.setDescrizione("Scrivania vicino alla finestra");
        postazione1.setTipoPostazione(TipoPostazione.PRIVATO);
        postazione1.setCapacita(1);
        postazione1.setEdificio(edificio1);
        return postazione1;
    }

    @Bean
    public Postazione postazione2(Edificio edificio1) {
        Postazione postazione2 = new Postazione();
        postazione2.setDescrizione("Sala riunioni 1° piano");
        postazione2.setTipoPostazione(TipoPostazione.SALA_RIUNIONI);
        postazione2.setCapacita(8);
        postazione2.setEdificio(edificio1);
        return postazione2;
    }

    @Bean
    public Postazione postazione3(Edificio edificio2) {
        Postazione postazione3 = new Postazione();
        postazione3.setDescrizione("Open space lato nord");
        postazione3.setTipoPostazione(TipoPostazione.OPENSPACE);
        postazione3.setCapacita(12);
        postazione3.setEdificio(edificio2);
        return postazione3;
    }

    // PRENOTAZIONI
    @Bean
    public Prenotazione prenotazione1(Utente utente1, Postazione postazione1) {
        Prenotazione prenotazione1 = new Prenotazione();
        prenotazione1.setDataPrenotazione(LocalDate.now().plusDays(1));
        prenotazione1.setUtente(utente1);
        prenotazione1.setPostazione(postazione1);
        return prenotazione1;
    }

    @Bean
    public Prenotazione prenotazione2(Utente utente2, Postazione postazione2) {
        Prenotazione prenotazione2 = new Prenotazione();
        prenotazione2.setDataPrenotazione(LocalDate.now().plusDays(3));
        prenotazione2.setUtente(utente2);
        prenotazione2.setPostazione(postazione2);
        return prenotazione2;
    }

    @Bean
    public Prenotazione prenotazione3(Utente utente1, Postazione postazione3) {
        Prenotazione prenotazione3 = new Prenotazione();
        prenotazione3.setDataPrenotazione(LocalDate.now().minusDays(2));
        prenotazione3.setUtente(utente1);
        prenotazione3.setPostazione(postazione3);
        return prenotazione3;
    }

    @Bean
    public List<Utente> utenti(List<Utente> utenti) {
        return utenti;
    }

    @Bean
    public List<Edificio> edifici(List<Edificio> edifici) {
        return edifici;
    }

    @Bean
    public List<Postazione> postazioni(List<Postazione> postazioni) {
        return postazioni;
    }

    @Bean
    public List<Prenotazione> prenotazioni(List<Prenotazione> prenotazioni) {
        return prenotazioni;
    }
}
