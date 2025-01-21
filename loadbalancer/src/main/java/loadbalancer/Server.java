package loadbalancer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private int PORT;

    public Server(int PORT) {
        this.PORT = PORT;
    }

    public Server() {

    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening at port " + PORT);
            while (true) {
                try (Socket plug = serverSocket.accept()) {
                    System.out.println("Client connected!");
                    handleClient(plug);
                }
            }
        } catch (IOException ioException) {
            System.out.println("Error starting the server: " + ioException.getMessage());
        }
    }

    private void handleClient(Socket plug) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(plug.getInputStream()));
        OutputStream out = plug.getOutputStream();

        String line;
        while ((line = in.readLine()) != null && !line.isEmpty()) {
            System.out.println(line); 
        }

        String response = "HTTP/1.1 200 OK\r\n" +
                          "Content-Type: text/plain\r\n" +
                          "Content-Length: 35\r\n" +
                          "\r\n" +
                          "Response from server: HTTP/1.1 200 OK" + plug.getPort();

        out.write(response.getBytes());
        out.flush();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
      
        List<Server> running = new ArrayList<>();
        running.add(new Server(8080));
        running.add(new Server(9090));
        running.add(new Server(7070));
        running.add(new Server(9000));
        running.add(new Server(9002));
        running.add(new Server(9003));

        for(Server server : running) {
            new Thread(() -> {
                try {
                    server.start();
                } catch(IOException e) {
                    e.getMessage();
                }
            }).start();
        }

        Thread.sleep(1000);

    }
}
