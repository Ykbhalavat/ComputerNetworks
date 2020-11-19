import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class ServerThread extends Thread {
    ScriptEngineManager mgr = new ScriptEngineManager();
    ScriptEngine engine = mgr.getEngineByName("JavaScript");
    Socket s;
   
    ServerThread(Socket s)
    {
        this.s=s;
    }
    
 
    public void run(){
        
        try {
            helper();
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ScriptException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    private void helper() throws IOException, ScriptException{
        InputStream is = s.getInputStream();
        OutputStream os = s.getOutputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String exp = reader.readLine();
        String result = engine.eval(exp).toString();
        System.out.println(result);
    }
    
    
    
    
    
}
