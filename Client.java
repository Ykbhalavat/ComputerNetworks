
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

//import jdk.internal.module.Resources;

public class Client {
  private Socket socket = null;
  private BufferedReader dataInput = null;
  private DataOutputStream dataOutput = null;

  public Client(String address, int portNum) {
    // start the connection process
    try {
      socket = new Socket(address, portNum);
      dataInput = new BufferedReader(new InputStreamReader(System.in));
      dataOutput = new DataOutputStream(socket.getOutputStream());
      System.out.println("Connection to Server Successful");
    } catch (UnknownHostException hostExecpt) {
      System.out.println(hostExecpt);
    } catch (IOException IO) {
      System.out.println(IO);
    }

    String userInput = "";
    System.out.println("Enter expression to be calculated, or type 'end' to end connection to server: ");

    // The while loop that will keep asking for user input
    while (!userInput.equals("end")) {
      try {
        userInput = dataInput.readLine();
        dataOutput.writeUTF(userInput);
      } catch (IOException IO) {
        System.out.println(IO);
      }
    }

    // close all open streams
    try {
      dataInput.close();
      dataOutput.close();
      socket.close();
    } catch (IOException IO) {
      System.out.println(IO);
    }
  }

  public static void main(String[] args) {
    Client testClient = new Client("127.0.0.1", 5000);
  }
}