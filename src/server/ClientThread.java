package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientThread extends Thread{

    Socket clientSocket;
    ClientThread(Socket socket){this.clientSocket = socket;}

    @Override
    public void run() {

            try {
                System.out.println("Accepted connection request from :" + clientSocket.getInetAddress().getHostName() + ":" + clientSocket.getPort());
                DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
                DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                byte[] messageByte = new byte[1000];
                while (true) {
                    int bytesRead = in.read(messageByte);
                    String messageString = new String(messageByte, 0, bytesRead);
                    System.out.println("Client " + clientSocket.getInetAddress() + ";" + clientSocket.getPort() + " SAYS: " + messageString);
                    out.write(messageByte, 0, bytesRead);
                    out.flush();
                }
            } catch (Exception e) {
                System.out.println("Client " + clientSocket.getInetAddress() + ";" + clientSocket.getPort() + " DISCONNECTED. " + e.getMessage());
                Server.counter--;
                try {
                    clientSocket.close();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }

            }

    }
}
