
import java.net.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;
import javax.script.ScriptException;


public class Server {

	private Socket socket = null;
	private ServerSocket server = null;
	private DataInputStream in = null;
	private DataOutputStream out = null;
	
	
	public Server(int port)
	{
		try {
			
			server = new ServerSocket(port);
			System.out.println("server started");
			
			System.out.println("waiting for client");
			
			socket = server.accept();
			System.out.println("client accepted");
			
			
			in = new DataInputStream(socket.getInputStream()); 
			out = new DataOutputStream(socket.getOutputStream()); 
			
		while(true)
		{
			 // wait for input 
            String input = in.readUTF(); 
  
            if(input.equals("exit")) 
                break; 
  
            System.out.println("Equation received :- " + input); 
            int result; 
  
            StringTokenizer st = new StringTokenizer(input); 
  
            int oprnd1 = Integer.parseInt(st.nextToken()); 
            String operation = st.nextToken(); 
            int oprnd2 = Integer.parseInt(st.nextToken()); 
  
            if (operation.equals("+")) 
            { 
                result = oprnd1 + oprnd2; 
            } 
  
            else if (operation.equals("-")) 
            { 
                result = oprnd1 - oprnd2; 
            } 
            else if (operation.equals("*")) 
            { 
                result = oprnd1 * oprnd2; 
            } 
            else
            { 
                result = oprnd1 / oprnd2; 
            } 
            System.out.println("Sending the result..."); 
  
            out.writeUTF(Integer.toString(result)); 
        } 
    }catch(IOException IO)
		{
    	
		}

	}
			
	
	
	class ServerThread extends Thread{
		
		String line = null;
		DataInputStream is = null;
		PrintWriter os = null;
		Socket s = null;
		
		public ServerThread(Socket s2)
		{
			s = s2;
		}
		
		public void run()
		{
			try {
				is = new DataInputStream(s.getInputStream());
				os = new PrintWriter(s.getOutputStream());
				 line = is.readLine();
				
				
				while(!line.equals("end"))
			    {
			     
			    	  os.println(line);
			    	  os.flush();
						System.out.println(line);
			        line = is.readLine();

			    
			    }
				is.close();
				os.close();
				s.close();
			    	  
			      }catch(Exception e)
			{
			    	  
			    }
			}
		}
	
	
public static void main(String[] args) throws IOException, ScriptException {
////    String exp;
////    int num=0;
////    ScriptEngineManager mgr = new ScriptEngineManager();
////    ScriptEngine engine = mgr.getEngineByName("JavaScript");
////    ServerSocket ss = new ServerSocket(1999);
////    Socket s = ss.accept();
////    Scanner s1 = new Scanner(s.getInputStream());
////    exp = s1.nextLine();
////    num = (int)engine.eval(exp);
////    PrintStream p =new PrintStream(s.getOutputStream());
////    p.println(num);
//    
//    
	Server server = new Server(5000);
//    
}

}



