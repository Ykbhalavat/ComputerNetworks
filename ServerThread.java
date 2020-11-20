
import java.net.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import javax.script.ScriptException;

public class ServerThread extends Thread {

    private Socket socket = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;

    List<Info_Client> client;
    int index;

    public ServerThread(Socket s, List<Info_Client> client) {
        this.socket = s;
        this.client = client;

    }

    String nameofclient = "";

    public void run() {

        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            nameofclient = in.readLine();
            log("Connection with the client " + nameofclient + " was successful.");
            int timeOfConnection;
            String timeAndDate;
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss yyyy/MM/dd");
            LocalDateTime now = LocalDateTime.now();
            timeAndDate = dtf.format(now);
            System.out.println("Connection with the client " + nameofclient + " was successful at " + timeAndDate);
            out.writeBytes("Connection with server was successful!" + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }

        client.add(index, new Info_Client(nameofclient, new Date()));

        try {

            while (true) {

                String input = in.readUTF();

                if (input.contains("end")) {
                    int timeOfConnection = findTimeOfConnection(client.get(index));
                    log(nameofclient + " asked to terminate the request from the client.");
                    log(nameofclient + " was connected for " + timeOfConnection + " seconds");
                    System.out.println(nameofclient + " asked to terminate the request from the client.");
                    System.out.println(nameofclient + " was connected for " + timeOfConnection + " seconds");
                    socket.close();
                    break;
                }

                log("Equation received :  " + input);
                // System.out.println("Equation received : " + input);
                int result = 0;

                StringTokenizer st = new StringTokenizer(input);

                int oprnd1 = Integer.parseInt(st.nextToken());
                String operation = st.nextToken();
                int oprnd2 = Integer.parseInt(st.nextToken());

                if (operation.equals("+")) {
                    result = oprnd1 + oprnd2;
                }

                else if (operation.equals("-")) {
                    result = oprnd1 - oprnd2;
                } else if (operation.equals("*")) {
                    result = oprnd1 * oprnd2;
                } else if (operation.equals("/")) {
                    if (oprnd2 == 0) {
                        log("Error denominator cannot be 0");
                        // System.out.println("Error denominator cannot be 0");
                    } else {
                        result = oprnd1 / oprnd2;
                    }

                } else if (operation.equals("%")) {
                    if (oprnd2 == 0) {
                        log("Error denominator cannot be 0");
                        // System.out.println("Error denominator cannot be 0");
                    } else {
                        result = oprnd1 % oprnd2;
                    }

                } else {
                    log("operation not defined");
                    // System.out.println("operation not defined");

                }

                log("Sending the result to client as :  " + result);

                // System.out.println("Sending the result to client as : " + result);

                out.writeUTF(Integer.toString(result));
            }
        } catch (IOException IO) {
            IO.printStackTrace();
        }
    }

    public static void log(String message) {
        String timeAndDate;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        timeAndDate = dtf.format(now);
        try {
            FileWriter log = new FileWriter("server_logs.txt", true);
            BufferedWriter bWriter = new BufferedWriter(log);
            PrintWriter out = new PrintWriter(bWriter);

            String logMessage = timeAndDate + " " + message;
            out.println(logMessage);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int findTimeOfConnection(Info_Client client) {
        Date clientDate = client.getLogindate();
        Date currentDate = new Date();
        long timeDiff = currentDate.getTime() - clientDate.getTime(); // difference in the two in milliseconds
        timeDiff = (timeDiff / 1000) % 60; // time in seconds
        int timeDiffInt = (int) timeDiff;
        return timeDiffInt;
    }

    // public static void main(String[] args) throws IOException {
    // // TODO Auto-generated method stub
    //
    // ServerSocket testsocket = new ServerSocket(5000);
    //
    // List<Info_Client> clients = new ArrayList<>();
    //
    //
    // while(true) {
    // System.out.println("server started");
    // System.out.println("waiting for client");
    //
    // Socket socket = testsocket.accept();
    // ServerThread thread = new ServerThread(socket, clients);
    // thread.start();
    //
    // }

}
