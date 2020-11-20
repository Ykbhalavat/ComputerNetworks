import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class server {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

        ServerSocket testsocket = new ServerSocket(5000);

		 List<Info_Client> clients = new ArrayList<>();
	       
		 
		 while(true) {
			 System.out.println("server started");
				System.out.println("waiting for client");
		      
		            Socket socket = testsocket.accept();
		            ServerThread thread = new ServerThread(socket, clients);
		            thread.start();
		            
		 }
	
	       

	}
	
	
}
