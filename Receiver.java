// A Java program for a Server
import java.net.*;
import java.io.*;
 
public class Receiver
{
    //initialize socket and input stream
    private Socket          socket   = null;
    private ServerSocket    server   = null;
    private DataInputStream in       =  null;
 
    // constructor with port
    public Receiver(int port)
    {
        // starts server and waits for a connection
        try
        {
            server = new ServerSocket(port);
            System.out.println("Server started");
 
            System.out.println("Waiting for a client ...");
 
            socket = server.accept();
            System.out.println("Client accepted");
 
            // takes input from the client socket
            in = new DataInputStream(
                new BufferedInputStream(socket.getInputStream()));
 
            String line = "";
 
            // reads message from client until "Over" is sent
            try
            {
                line = in.readUTF();
                int indexOfSplit = line.indexOf(":");
                String text = line.substring(0,indexOfSplit);
                String keyString = line.substring(indexOfSplit+1,line.length());
                int key = Integer.parseInt(keyString);
                StringBuffer textToBeDecrypted = new StringBuffer(text);
                CaesarCipher csObj = new CaesarCipher();
                StringBuffer decryptedText = csObj.decrypt(textToBeDecrypted, key);
                
                System.out.println(decryptedText);

            }
            catch(IOException i)
            {
                System.out.println(i);
            }
            
            
            System.out.println("Closing connection");
 
            // close connection
            socket.close();
            in.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }
 
    public static void main(String args[])
    {
        Receiver server = new Receiver(5000);
    }
}