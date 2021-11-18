import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;



public class Administrador {

    public static void main(String[] args) {
        

        // Abre conexão com o servidor
        try {

            Socket s = new Socket("127.0.0.1", 5000);
            System.out.println( "Administrador: conexao feita com o servidor" );


            ObjectOutputStream gravador = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream leitor = new ObjectInputStream(s.getInputStream());
            
            //Envia o texto para o servidor
            gravador.writeObject("admin");
            

            System.out.println("Aguardando ...");
            
            //Recebe o log do servidor            
            String log = (String) leitor.readObject();
            
            //Imprime oo log
            System.out.println(log);

            //Encerra a conexao
            s.close();

            System.out.println( "Administrador: conexao encerrada");
        } catch (Exception e) {
            
            e.printStackTrace();
        } 


    }

}
