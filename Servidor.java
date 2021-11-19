import java.io.IOException;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;



public class Servidor {



    public static void main(String[] args) {

    
        try {

            ServerSocket ss = new ServerSocket(5000);

            MulticastSocket ms = new MulticastSocket();

            int[] timeout = {2000}; 

            

            int x = 1;
            while (x==1)
            {
                System.out.println( "Servidor: aguardando cliente");

                //Aguarda receber pedido de conexão do cliente
                Socket t = ss.accept(); 

                System.out.println( "Servidor: conexao feita");

                Trabalhador trab = new Trabalhador(t, ms, timeout);
                trab.start();

            }

            ss.close();

        } catch (IOException e) {
            
            e.printStackTrace();
        }

    }

}
