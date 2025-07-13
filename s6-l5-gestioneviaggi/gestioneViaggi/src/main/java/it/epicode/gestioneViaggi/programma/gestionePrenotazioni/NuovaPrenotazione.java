package it.epicode.gestioneViaggi.programma.gestionePrenotazioni;

import it.epicode.gestioneViaggi.dipendente.Dipendente;
import it.epicode.gestioneViaggi.dipendente.DipendenteService;
import it.epicode.gestioneViaggi.prenotazione.PrenotazioneRepository;
import it.epicode.gestioneViaggi.prenotazione.PrenotazioneService;
import it.epicode.gestioneViaggi.viaggio.StatoViaggio;
import it.epicode.gestioneViaggi.viaggio.Viaggio;
import it.epicode.gestioneViaggi.viaggio.ViaggioRepository;
import it.epicode.gestioneViaggi.viaggio.ViaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class NuovaPrenotazione {
    Scanner scanner = new Scanner(System.in);

    @Autowired
    private PrenotazioneService prenotazioneService;

    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private ViaggioService viaggioService;

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private ViaggioRepository viaggioRepository;

    public void NuovaPrenotazione() {
        while (true) {
            List<Viaggio> viaggiDisponibili = viaggioRepository.getAllByStatoViaggio(StatoViaggio.IN_PROGRAMMA);
            List<Dipendente> dipendenti = dipendenteService.getAllDipendenti();

            if(viaggiDisponibili.isEmpty()) {
                System.out.println("Nessun viaggio disponibile");
                break;
            }

            System.out.println("Viaggi disponibili:");
            viaggiDisponibili.forEach(System.out::println);

            System.out.println("Seleziona l'ID del viaggio: ");
            Long idViaggio = scanner.nextLong();
            scanner.nextLine();

            if (!viaggiDisponibili.stream().anyMatch(viaggio -> viaggio.getId().equals(idViaggio))) {
                System.out.println("ID non valido. Riprova.");
                break;
            }

            dipendenti.forEach(System.out::println);
            System.out.println("Inserisci ID del dipendente: ");
            Long idDipendente = scanner.nextLong();
            scanner.nextLine();

            if (!dipendenti.stream().anyMatch(dipendente -> dipendente.getId().equals(idDipendente))) {
                System.out.println("ID non valido. Riprova.");
                break;
            }

            Viaggio viaggio = viaggioService.getViaggioById(idViaggio);
            Dipendente dipendente = dipendenteService.getDipendenteById(idDipendente);

            if (prenotazioneRepository.existsByDipendenteAndViaggio_DataPartenza(dipendente, viaggio.getDataPartenza())) {
                System.out.println("Il dipendente ha già una prenotazione per quel giorno.");
                break;
            }

            prenotazioneService.assegnaDipendenteAViaggio(viaggio.getId(), dipendente.getId());
            System.out.println("Prenotazione creata con successo.");
            return;
        }
    }
}
