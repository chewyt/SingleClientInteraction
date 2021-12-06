package chewyt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Date;

public class Server {
    
    private static final int PORT = 9090;
    public static void main(String[] args) throws IOException{


        //Make the server connection
        ServerSocket listener = new ServerSocket(PORT);
        System.out.println("[SERVER] Server is waiting for client connection...");
        
        try {
            while(true){
                //Listening for client to enter
                Socket client = listener.accept();
                System.out.println("[SERVER] Connected to client!");
    
                // Printwriter to export the data to the client
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                //Initial print for Date display
                out.println((new Date()).toString());
                System.out.println("[SERVER] Sent date. Waiting for response...");
    
                // Server taking information from the socket terminal from the client ,not system.in
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
    
                //Initialising string variable for the data from the client 's "request"
                
                try {
                    while (true) {
                        String request = in.readLine();
                        if(request.equals("exit"))break;
                        System.out.println(request);
                    } 
                } catch (Exception e) {
                e.getStackTrace();
                }  
                finally{
                    client.close();
                    
                }
            }  

        } catch (Exception e) {
            e.getStackTrace();
        }
        finally{
            listener.close();
        }
        

        
    }
}
