
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Server {

    public static void main(String[] args) throws IOException, ScriptException {

        ServerSocket ss = new ServerSocket(5000);

        while (true) {
            System.out.println("Server Listening......");
            Socket s = ss.accept();
            ServerThread St = new ServerThread(s);
            St.start();

        }

        // Scanner s1 = new Scanner(s.getInputStream());
        // exp = s1.nextLine();
        // num = (int)engine.eval(exp);
        // PrintStream p =new PrintStream(s.getOutputStream());
        // p.println(num);

    }

}
