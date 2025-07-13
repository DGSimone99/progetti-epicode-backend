package it.epicode.gestioneViaggi.dipendente;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class DipendenteRunner implements CommandLineRunner {
    @Autowired
    private Faker faker;

    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Override
    public void run(String... args) throws Exception {
        if(dipendenteRepository.count() == 0) {
            for (int i = 0; i < 20; i++) {
                Dipendente dipendente = new Dipendente();
                dipendente.setNome(faker.name().firstName());
                dipendente.setCognome(faker.name().lastName());
                dipendente.setUsername(faker.name().username());
                dipendente.setEmail(faker.internet().emailAddress());
                dipendenteService.createDipendente(dipendente);
            }
        }
    }
}
