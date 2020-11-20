import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class server {

	public static void main(String[] args) throws IOException {
		createLogFile(); // create new log file, or check if one already exists
		ServerSocket testsocket = new ServerSocket(5000);

		List<Info_Client> clients = new ArrayList<>();

		while (true) {
			ServerThread.log("server started");
			ServerThread.log("Waiting for client...");
			// System.out.println("server started");
			// System.out.println("waiting for client");

			Socket socket = testsocket.accept();
			ServerThread thread = new ServerThread(socket, clients);
			thread.start();

		}

	}

	public static Boolean createLogFile() {
		try {
			File fileObj = new File("server_logs.txt");
			if (fileObj.createNewFile()) {
				System.out.println("Log file successfully created.");
			} else {
				System.out.println("Log File already exists");
				try {
					FileWriter log = new FileWriter("server_logs.txt", true);
					BufferedWriter bWriter = new BufferedWriter(log);
					PrintWriter out = new PrintWriter(bWriter);

					out.println("\nSTARTING A NEW SERVER\n");
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}