package it.s2l5;

import it.s2l5.exceptions.DatoNonValidoException;
import it.s2l5.programma.AvviaProgramma;

import java.util.Scanner;

import static it.s2l5.programma.Archivio.archivio;
import static it.s2l5.pubblicazioni.Libri.libri;
import static it.s2l5.pubblicazioni.Riviste.riviste;

public class Main {
     public static void main(String[] args) throws DatoNonValidoException {
        archivio.addAll(libri);
        archivio.addAll(riviste);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            AvviaProgramma.avviaProgramma();
        }
    }
}
