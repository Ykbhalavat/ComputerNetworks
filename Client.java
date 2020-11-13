
package client_server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
      String exp; 
      int temp;
      Socket s = new Socket("localhost",1999);
      Scanner input = new Scanner(System.in);
      Scanner s1 = new Scanner(s.getInputStream());
      System.out.println("Enter your expression");
       exp = input.nextLine();
      PrintStream p = new PrintStream(s.getOutputStream());
      p.println(exp);
      temp = s1.nextInt();
      System.out.println(temp);
      
    }
    
}
