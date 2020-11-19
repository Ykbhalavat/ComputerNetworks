import java.io.BufferedReader;
import java.io.DataInputStream; 
import java.io.DataOutputStream; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress; 
import java.net.Socket; 
import java.net.UnknownHostException; 
import java.util.Scanner; 
  

public class Client {
  private Socket socket = null;
  private BufferedReader dataInput = null;
  private DataInputStream Serverreaddata = null;
  private DataOutputStream dataOutput = null; 


  public Client(String address, int portNum) throws IOException
  {
    //start the connection process
   
      socket = new Socket(address, portNum);
      dataInput = new BufferedReader(new InputStreamReader(System.in));
      Serverreaddata =   new DataInputStream((socket.getInputStream()));
      dataOutput = new DataOutputStream(socket.getOutputStream());
      System.out.println("Connection to Server Successful");
      
 while(true)
 {
	    System.out.println("Enter expression to be calculated, or type 'end' to end connection to server: ");
	    String response = dataInput.readLine();

        if (response.equals("bye")) 
            break; 

        dataOutput.writeUTF(response); 

        String ans = Serverreaddata.readUTF(); 
        
        System.out.println("Answer=" + ans); 
 }
    
  
  }  
  
  public static void main(String[] args) throws IOException
    {
      Client testClient = new Client("127.0.0.1", 5000);
      
    }
}

