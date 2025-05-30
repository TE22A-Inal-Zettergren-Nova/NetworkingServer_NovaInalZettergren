import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkingServer {
    public static void main(String[] args) {
        ServerSocket server = null;
        Socket client;

        int portnumber = 49153;
        if ( args.length >= 1){
            portnumber = Integer.parseInt(args[0]);
        }

        try{
            server = new ServerSocket(portnumber);
        }catch ( IOException ie) {
            System.out.println("Cannot open socket" + ie);
            System.exit(1);
        }
        System.out.println("ServerSocket is created " + server);

        while(true){
            try{
                System.out.println("Waiting for connect request...");
                client = server.accept();

                System.out.println("Connect request is accepted...");
                String clientHost = client.getInetAddress().getHostAddress();
                int clientPort = client.getPort();
                System.out.println("Client host = " + clientHost + "Client port = " + clientPort);

                InputStream clientIn = client.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(clientIn));

                String msgFromClient = br.readLine();
                System.out.println("Message recieved from client = " + msgFromClient);

    if(msgFromClient != null && ! msgFromClient.equalsIgnoreCase("bye")){
            OutputStream clientOut = client.getOutputStream();
            PrintWriter pw = new PrintWriter(clientOut, true);
            String ansMsg = "Hello, " + msgFromClient;
            pw.println(ansMsg);

        }

        if (msgFromClient != null && msgFromClient.equalsIgnoreCase("bye")){
            server.close();

            client.close();
            break;
        }
    } catch ( IOException ie) {


            }
        }
    }
}



