package loadbalancer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class LoadBalancer {

    private final int PORT;
    private final AtomicInteger currentIndex = new AtomicInteger(0);
    private final List<AvailableServers> availableServers;

    public LoadBalancer(int PORT, List<AvailableServers> availableServers) {
        this.PORT = PORT;
        this.availableServers = new CopyOnWriteArrayList<>(availableServers);
    }

    public void start() throws IOException {
       try(ServerSocket lSocket = new ServerSocket(PORT)) {
          System.out.println("Load balancer listening on port " + PORT);
          while(true) {
            try(Socket plug = lSocket.accept()) {
                forwardRequest(plug);
            }
          }
       }
    }

    private void forwardRequest(Socket incomingFromClient) throws IOException {
         if(availableServers.size() == 0) {
            System.out.println("NO available servers");
            OutputStream out = incomingFromClient.getOutputStream();
            String response = "HTTP/1.1 503 Service Unavailable\r\nContent-Length: 0\r\n\r\n";
            out.write(response.getBytes());
            incomingFromClient.close();
         }

         AvailableServers availableServer = getNextServer();

         try(Socket serverSocket = new Socket("localhost", availableServer.PORT)) {
            System.out.println("Request assigned to server with PORT " + availableServer.PORT);
         } catch(IOException e) {
            System.out.println(e.getMessage());
         }
    }

    private AvailableServers getNextServer() {
        int index = currentIndex.getAndUpdate(i -> (i+1)%availableServers.size());
        return availableServers.get(index);
    }

    public static void main(String[] args) throws IOException {

        List<AvailableServers> availableServers = new ArrayList<>();
        availableServers.add(new AvailableServers(7070));
        availableServers.add(new AvailableServers(8080));
        availableServers.add(new AvailableServers(9090));
        availableServers.add(new AvailableServers(9000));
        availableServers.add(new AvailableServers(9001));
        availableServers.add(new AvailableServers(9002));
        LoadBalancer loadBalancer = new LoadBalancer(1010, availableServers);
        System.out.println("STARTING LOAD BALANCER");
        loadBalancer.start();
    }
}
