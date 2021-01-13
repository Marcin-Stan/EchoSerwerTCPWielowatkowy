package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server extends Thread {

    ServerSocket serverSocket = null;

    static int counter;

    Server( ) {
    }

    public synchronized void Start(int port ) {
        try{
            if (this.serverSocket != null && this.serverSocket.isBound()) {
                this.serverSocket.close();
            }

            this.serverSocket = new ServerSocket(port);
            this.start();

        }catch (IOException e){
            this.serverSocket = null;

        }

    }

    @Override
    public void run() {
        try {

            System.out.println("Server is working on port " + this.serverSocket.getLocalPort());
            System.out.println("Waiting for new client");

            while (true) {
                    Socket clientSocket = this.serverSocket.accept();
                    counter++;
                    if(counter<=3){
                        ClientThread clientThread = new ClientThread(clientSocket);
                        clientThread.start();
                        System.out.println("Liczba polaczonych klientow:"+counter);
                    }else{
                        System.out.println("Jest za duzo klientow polaczonych z serverem, klient zostal rozlaczony ");
                        counter--;
                        try {
                            clientSocket.close();
                        } catch (IOException exception) {
                            exception.printStackTrace();
                        }

                    }



            }
        } catch (IOException exception) {
            System.out.println("bład");
        }


    }
}
