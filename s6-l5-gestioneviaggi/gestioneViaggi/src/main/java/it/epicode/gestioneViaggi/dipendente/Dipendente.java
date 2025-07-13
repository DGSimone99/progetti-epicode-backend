package it.epicode.gestioneViaggi.dipendente;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@Table(name = "dipendenti")

public class Dipendente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Il campo 'username' non può essere vuoto.")
    private String username;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Il campo 'nome' non può essere vuoto.")
    private String nome;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Il campo 'cognome' non può essere vuoto.")
    private String cognome;

    @Column(length = 50)
    @Email(message = "Inserire un indirizzo email valido.")
    private String email;

    @Column(length = 200)
    private String avatarUrl;

    @Override
    public String toString() {
        return id + " - " + nome + " " + cognome + " - " + email;
    }
}