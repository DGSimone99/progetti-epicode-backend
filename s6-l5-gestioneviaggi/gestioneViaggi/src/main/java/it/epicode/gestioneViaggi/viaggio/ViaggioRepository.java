package it.epicode.gestioneViaggi.viaggio;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ViaggioRepository extends JpaRepository<Viaggio, Long> {
    public List<Viaggio> getAllByStatoViaggio(StatoViaggio statoViaggio);
}