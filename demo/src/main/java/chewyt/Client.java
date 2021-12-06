import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Client {

    private static final String SERVER_IP =  "127.0.0.1";
    private static final int PORT =  9090;
    
    public static void main(String[] args) throws UnknownHostException, IOException {
        
        Socket socket  = new Socket(SERVER_IP,PORT);

        // To read from the socket terminal linking to the server e.g. Today's date
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String serverResponse = input.readLine(); // means Bufferedreader object.readline
        //JOptionPane.showMessageDialog(null, serverResponse);
        String username = JOptionPane.showInputDialog("Logged in at "+serverResponse+"\nEnter your username:");

            // Bufferedreader xxx.read() or .readline()
            //parse from Strings to Int ==> Integer.parseInt(xxxx.readline())
        

        System.out.println("Connected to server");
        //System.out.println("Enter request: ");
        // Getting out to the server
        PrintWriter out  = new PrintWriter(socket.getOutputStream(),true); //true for auto flush

        //Write to the client terminal: System.out.println(username+" has connected to the server.");
        //Write to the server
        out.println(username+" has connected to the server.");

        // To read from the keyboard terminal
        // BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        

        try {
            while(true){
                //System.out.print("> ");
                //String command = keyboard.readLine();
                //out.println(command); //Sending command to the server
                String window = JOptionPane.showInputDialog("Chatbox request :) \nEnter quit to close the loop");
                out.println(username+": "+window);
                //if(command.equals("quit")) break;
                if(window.equals("quit")) {
                    out.println(username+" has left the server.");
                    break;
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        finally{
            socket.close();
            System.exit(0);
        }

        
    }
}
