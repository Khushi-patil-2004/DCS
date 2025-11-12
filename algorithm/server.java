package pr;
import java.io.*;
import java.net.*;
import java.util.*;

public class server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Server started. Waiting for clients...");

        int numClients = 3; // number of clients expected
        List<Socket> clients = new ArrayList<>();
        List<Long> clientTimes = new ArrayList<>();

        // Accept all clients
        for (int i = 0; i < numClients; i++) {
            Socket socket = serverSocket.accept();
            clients.add(socket);
            System.out.println("Client " + (i + 1) + " connected.");
        }

        long serverTime = System.currentTimeMillis();
        System.out.println("\nServer time (ms): " + serverTime);

        // Step 1: Send server time and receive client times
        for (Socket socket : clients) {
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            dos.writeLong(serverTime); // send server time
            dos.flush();

            long clientTime = dis.readLong(); // receive client time
            clientTimes.add(clientTime);

            System.out.println("Received client time: " + clientTime);
        }

        // Step 2: Calculate average time difference
        long totalDiff = 0;
        for (long time : clientTimes) {
            totalDiff += (time - serverTime);
        }

        long averageDiff = totalDiff / (numClients + 1); // include server in average
        long adjustedServerTime = serverTime + averageDiff;

        System.out.println("\nAverage time difference: " + averageDiff + " ms");
        System.out.println("Adjusted (synchronized) time: " + adjustedServerTime + " ms");

        // Step 3: Send offsets to each client
        for (int i = 0; i < clients.size(); i++) {
            Socket socket = clients.get(i);
            long clientTime = clientTimes.get(i);

            long offset = adjustedServerTime - clientTime; // main formula
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeLong(offset);
            dos.flush();

            System.out.println("Sent offset to Client " + (i + 1) + ": " + offset + " ms");
        }

        // Step 4: Print adjusted server time
        System.out.println("\nServer adjusted its own time by " + averageDiff + " ms");
        System.out.println("Final synchronized server time: " + (serverTime + averageDiff));

        // Close all connections
        for (Socket socket : clients) socket.close();
        serverSocket.close();
    }
}

