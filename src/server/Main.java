package server;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Integer port =0;
        boolean isCorrect = false;

        while(!isCorrect) {

            try {
                System.out.println("Podaj numer portu(liczba):");
                Scanner sc = new Scanner(System.in);
                port = sc.nextInt();
                isCorrect=true;
            } catch (InputMismatchException e) {
                System.out.println("Portem moze byc tylko liczba, sprobuj jeszcze raz");
            }
        }


        Server server = new Server();
        server.Start(port);

    }
}
