package it.epicode.gestioneViaggi.prenotazione;

import com.github.javafaker.Faker;
import it.epicode.gestioneViaggi.dipendente.Dipendente;
import it.epicode.gestioneViaggi.dipendente.DipendenteService;
import it.epicode.gestioneViaggi.viaggio.Viaggio;
import it.epicode.gestioneViaggi.viaggio.ViaggioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
@Order(3)
@AllArgsConstructor
public class PrenotazioneRunner implements CommandLineRunner {
    @Autowired
    private Faker faker;

    @Autowired
    private PrenotazioneService prenotazioneService;

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private ViaggioService viaggioService;

    @Autowired
    private DipendenteService dipendenteService;

    @Override
    public void run(String... args) throws Exception {
        Random random = new Random();

        List<Viaggio> viaggi = viaggioService.getAllViaggi();
        List<Dipendente> dipendenti = dipendenteService.getAllDipendenti();

        if(prenotazioneRepository.count() == 0) {
            for (int i = 0; i < 10; i++) {
                Prenotazione prenotazione = new Prenotazione();
                prenotazione.setViaggio(viaggi.get(random.nextInt(viaggi.size())));
                prenotazione.setDipendente(dipendenti.get(random.nextInt(dipendenti.size())));
                prenotazione.setDataPrenotazione(prenotazione.getViaggio().getDataPartenza().minusDays(random.nextInt(365)));
                prenotazione.setNote(faker.lorem().sentence());
                prenotazioneService.createPrenotazione(prenotazione);
            }
        }
    }
}
