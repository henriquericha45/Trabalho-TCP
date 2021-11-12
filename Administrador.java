import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;



public class Administrador {

    public static void main(String[] args) {
        

        // Abre conexão com o servidor
        try {

            Socket s = new Socket("127.0.0.1", 5000);
            System.out.println( "Administrador: conexao feita com o servidor" );

            DataOutputStream saida = new DataOutputStream(s.getOutputStream());
            
            //Envia o texto para o servidor
            saida.writeUTF("admin");
            saida.flush();

            DataInputStream entrada = new DataInputStream( s.getInputStream());
            //InputStreamReader osw = new InputStreamReader(s.getInputStream(), "UTF-8");

            System.out.println("Aguardando ...");
            
            //Recebe o log do servidor
            //System.out.println(osw.read());
            
            String r = entrada.readUTF();
            
            //Imprime oo log
            System.out.println(r);

            //Encerra a conexao
            s.close();

            System.out.println( "Cliente: conexao encerrada");
        } catch (Exception e) {
            
            e.printStackTrace();
        } 


    }

}
