// A Java program for a Client
import java.net.*;
import java.util.Scanner;
import java.io.*;
 
public class Sender
{
    // initialize socket and input output streams
    private Socket socket            = null;
    private DataInputStream  input   = null;
    private DataOutputStream out     = null;
 
    // constructor to put ip address and port
    public Sender(String address, int port)
    {
        
    	 // takes input from terminal
    	 Scanner sc = new Scanner(System.in);
    	
    	 // establish a connection
    	 try
        {
            socket = new Socket(address, port);
            System.out.println("Connected");
 
           
            // sends output to the socket
            out    = new DataOutputStream(socket.getOutputStream());
        }
        catch(UnknownHostException u)
        {
            System.out.println(u);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
 
        // string to read message from input
        String line = "";
 
        // apply encryption
        
            try
            {
                line = sc.nextLine();
                CaesarCipher csObj = new CaesarCipher();
                int key = csObj.takeKeyInput();
                StringBuffer textToBeEncrypted = new StringBuffer(line);
                StringBuffer encryptedText = csObj.encrypt(textToBeEncrypted, key);
                System.out.println(encryptedText.toString());
                out.writeUTF(encryptedText.toString()+ ":"+ Integer.toString(key));
            }
            catch(IOException i)
            {
                System.out.println(i);
            }
        
 
        // close the connection
        try
        {
            sc.close();
            out.close();
            socket.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }
 
    public static void main(String args[])
    {
        Sender client = new Sender("127.0.0.1", 5000);
    }
}