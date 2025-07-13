package catalogo;

import catalogo.prestiti.PrestitoDAO;
import catalogo.prestiti.UtenteDAO;
import catalogo.pubblicazioni.Libro;
import catalogo.pubblicazioni.Pubblicazione;
import catalogo.pubblicazioni.PubblicazioneDAO;
import catalogo.pubblicazioni.Rivista;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.ArrayList;

import static catalogo.prestiti.Prestito.prestiti;
import static catalogo.prestiti.Prestito.prestito1;
import static catalogo.prestiti.Utente.utenti;
import static catalogo.pubblicazioni.Libro.libri;
import static catalogo.pubblicazioni.Libro.libro1;
import static catalogo.pubblicazioni.Rivista.riviste;

public class Main {
     public static void main(String[] args) {
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistUnit");
         EntityManager em = emf.createEntityManager();

         PubblicazioneDAO pubblicazioneDAO = new PubblicazioneDAO(em);
         PrestitoDAO prestitoDAO = new PrestitoDAO(em);
         UtenteDAO utenteDAO = new UtenteDAO(em);

         em.getTransaction().begin();
            /*pubblicazioneDAO.insert(new ArrayList<>(libri));
            pubblicazioneDAO.insert(new ArrayList<>(riviste));
            utenteDAO.insert(utenti);
            prestitoDAO.insert(prestiti);*/

            /*System.out.println("Lista Pubblicazioni:");
            pubblicazioneDAO.findAll().forEach(System.out::println);*/

            System.out.println("Lista Pubblicazioni:");
            pubblicazioneDAO.findAll().forEach(System.out::println);

            System.out.println("-----------------------------");

            System.out.println("Ricerca per anno 2023");
            pubblicazioneDAO.findByAnno(2023).forEach(System.out::println);

            System.out.println("-----------------------------");

            System.out.println("Ricerca per autore Tolkien");
            pubblicazioneDAO.findByAutore("Tolkien").forEach(System.out::println);

            System.out.println("-----------------------------");

            System.out.println("Ricerca per Titolo");
            pubblicazioneDAO.findByTitolo("Signo").forEach(System.out::println);

            System.out.println("-----------------------------");

            System.out.println("Prestiti da Utente");
            prestitoDAO.findPrestitiByUtente(1L).forEach(System.out::println);

            System.out.println("-----------------------------");

            System.out.println("Prestiti non resituiti:");
            prestitoDAO.findPrestitiNonRestituiti().forEach(System.out::println);

            /*pubblicazioneDAO.delete(1L);

            System.out.println("Lista Pubblicazioni senza isbn 1:");
            pubblicazioneDAO.findAll().forEach(System.out::println);

            pubblicazioneDAO.insert(libro1);

            System.out.println("Lista Pubblicazioni con riaggiunta Signore degli Anelli:");
            pubblicazioneDAO.findAll().forEach(System.out::println);
            */


            em.getTransaction().commit();
            
         em.close();
         emf.close();
    }
}
