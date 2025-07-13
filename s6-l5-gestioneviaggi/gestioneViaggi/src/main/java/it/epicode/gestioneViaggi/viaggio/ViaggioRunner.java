package it.epicode.gestioneViaggi.viaggio;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
@Order(2)
@AllArgsConstructor
public class ViaggioRunner implements CommandLineRunner {
    @Autowired
    private Faker faker;

    @Autowired
    private ViaggioRepository viaggioRepository;

    @Autowired
    private ViaggioService viaggioService;

    public static LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    @Override
    public void run(String... args) throws Exception {
        if(viaggioRepository.count() == 0) {
            for (int i = 0; i < 20; i++) {
                Viaggio viaggio = new Viaggio();
                viaggio.setDestinazione(faker.address().cityName());
                viaggio.setDataPartenza(toLocalDate(faker.date().between(
                        Date.from(LocalDate.now().minusDays(365).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                        Date.from(LocalDate.now().plusDays(365).atStartOfDay(ZoneId.systemDefault()).toInstant()))));
                viaggio.setStatoViaggio(viaggioService.statoViaggio(viaggio.getDataPartenza()));
                viaggioService.createViaggio(viaggio);
            }
        }
    }
}
