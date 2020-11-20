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
   
	  String username;
	  
      socket = new Socket(address, portNum);
      dataInput = new BufferedReader(new InputStreamReader(System.in));
      Serverreaddata =   new DataInputStream((socket.getInputStream()));
      dataOutput = new DataOutputStream(socket.getOutputStream());
      System.out.println("Connection to Server Successful");
      
      System.out.print("Please enter the name of Client: ");
      username = dataInput.readLine();
      dataOutput.writeBytes(username + "\n");
      System.out.println(Serverreaddata.readLine());
      
 while(true)
 {
	 System.out.println(" Please enter 'end' to end connection to server: ");
	 System.out.println("Enter expression to be calculated in format of [10 + 14] // keep on space between each : ");
	 String response = dataInput.readLine();
   if (response.equals("end")) 
   {
		 System.out.println("the Connection has been ended by the user");
		 socket.close();
       break; 
   }else {
   	  dataOutput.writeUTF(response); 
         String answer = Serverreaddata.readUTF(); 
         System.out.println("Answer from server = " + answer); 
   }
}
	 
 }

  public static void main(String[] args) throws IOException
    {
      Client testClient = new Client("127.0.0.1", 5000);
      
    }
}

