package it.epicode.gestione_prenotazioni.runner.programma;

import it.epicode.gestione_prenotazioni.model.utente.Prenotazione;
import it.epicode.gestione_prenotazioni.repository.EdificioRepository;
import it.epicode.gestione_prenotazioni.repository.PrenotazioneRepository;
import it.epicode.gestione_prenotazioni.repository.UtenteRepository;
import it.epicode.gestione_prenotazioni.runner.AvvioProgrammaRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Component
public class ControlloPrenotazioni {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private EdificioRepository edificioRepository;

    private AvvioProgrammaRunner avvioProgrammaRunner = new AvvioProgrammaRunner();

    public void ControlloPrenotazioni() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Scegli operazione:");
            System.out.println("1. Lista Prenotazioni");
            System.out.println("2. Lista Prenotazioni per Sede");
            System.out.println("3. Modifica Prenotazione");
            System.out.println("4. Cancella Prenotazione");
            System.out.println("N. Torna indietro");
            System.out.println("0. Chiudi il programma");

            String scelta = scanner.next().toLowerCase();
            scanner.nextLine();
            switch (scelta) {
                case "1": prenotazioneRepository.findAll().forEach(prenotazione -> {
                    System.out.println("ID: " + prenotazione.getId() + " - Data: " + prenotazione.getDataPrenotazione() + " - Postazione: Sede di " + prenotazione.getPostazione().getEdificio().getCitta() + " " + " " + prenotazione.getPostazione().getEdificio().getNome() + " - " + prenotazione.getPostazione().getTipoPostazione() + " con: " + prenotazione.getUtente().getNomeCompleto());
                });
                break;
                case "2":
                    edificioRepository.findAll().forEach(edificio -> {
                        System.out.println("Nome: " + edificio.getNome() + " - Città " + edificio.getCitta());
                    });
                    System.out.println("Digita il nome della sede: (Era meglio ID, ma è giusto per testare più query)");
                    String sede = scanner.nextLine();
                    if (prenotazioneRepository.findByPostazione_Edificio_NomeIgnoreCase(sede).isEmpty()) {
                        System.out.println("Sede non trovata.");
                        continue;
                    }
                    List<Prenotazione> prenotazioni = prenotazioneRepository.findByPostazione_Edificio_NomeIgnoreCase(sede);
                    System.out.println("Prenotazioni in sede " + prenotazioni.get(0).getPostazione().getEdificio().getNome());
                    prenotazioni.forEach(prenotazione -> {
                        System.out.println("Data: " + prenotazione.getDataPrenotazione() + " Tipo: " + prenotazione.getPostazione().getTipoPostazione() + " con: " + prenotazione.getUtente().getNomeCompleto());
                    });
                    break;
                case "3":
                    while(true) {
                        System.out.println("Seleziona ID della prenotazione da modificare:");
                        String id = scanner.next();
                        try {
                            if (prenotazioneRepository.findById(Long.parseLong(id)).isPresent()) {
                                Prenotazione prenotazione = prenotazioneRepository.findById(Long.parseLong(id)).get();
                                while (true) {
                                    System.out.println("Inserisci la nuova data (formato: yyyy-MM-dd):");
                                    String data = scanner.next();
                                    try {
                                        LocalDate nuovaData = LocalDate.parse(data);


                                        if (nuovaData.isBefore(LocalDate.now())) {
                                            System.out.println("La data non può essere prima di oggi.");
                                            continue;
                                        }

                                        Prenotazione prenotazioniStessaPostazione = prenotazioneRepository
                                                .findByDataPrenotazioneAndPostazione(nuovaData, prenotazione.getPostazione());
                                        if (prenotazioniStessaPostazione != null) {
                                            System.out.println("Esiste già una prenotazione per questa postazione alla data inserita.");
                                            continue;
                                        }

                                        Prenotazione prenotazioniUtente = prenotazioneRepository
                                                .findByUtenteAndDataPrenotazione(utenteRepository.findById(prenotazione.getUtente().getId()), nuovaData);
                                        if (prenotazioniUtente != null) {
                                            System.out.println("L'utente ha già una prenotazione per questa data.");
                                            continue;
                                        }

                                        prenotazione.setDataPrenotazione(nuovaData);
                                        prenotazioneRepository.save(prenotazione);
                                        System.out.println("Data della prenotazione modificata con successo!");
                                        break;
                                    } catch (java.time.format.DateTimeParseException e) {
                                        System.out.println("Data non valida. Usa il formato yyyy-MM-dd.");
                                    }
                                }
                                break;
                            } else {
                                System.out.println("Prenotazione non trovata");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("ID non valido, inserisci un numero.");
                        }
                    }
                    break;
                case "4":
                    while(true) {
                    System.out.println("Seleziona ID della prenotazione da cancellare:");
                    String id = scanner.next();
                    try {
                        if (prenotazioneRepository.findById(Long.parseLong(id)).isPresent()) {
                            Prenotazione prenotazione = prenotazioneRepository.findById(Long.parseLong(id)).get();

                            System.out.println("Sei sicuro di voler cancellare questa prenotazione? (s/n)");
                            String conferma = scanner.next();

                            if (conferma.equalsIgnoreCase("s")) {
                                prenotazioneRepository.delete(prenotazione);
                                System.out.println("Prenotazione cancellata con successo!");
                            } else {
                                System.out.println("Cancellazione annullata.");
                            }
                            break;
                        } else {
                            System.out.println("Prenotazione non trovata");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("ID non valido, inserisci un numero.");
                    }
                }
                break;
                case "n": return;
                case "0": System.exit(0);
                default:
                    System.out.println("Comnando non riconosciuto.");
            }
        }
    }
}
