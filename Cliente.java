import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Cliente {

    public static void main(String[] args) {
        

        // abre conexão com o servidor
        try {
            Socket s = new Socket("127.0.0.1", 5000); //IP SERVIDOR
            System.out.println( "Cliente: conexao feita com o servidor" );

            DataOutputStream saida = new DataOutputStream(s.getOutputStream()); 
            
            System.out.println("Insira o produto que deseja procurar: ");


            saida.writeUTF("Geladeira");

            ObjectInputStream entrada = new ObjectInputStream(s.getInputStream());

            System.out.println("Aguardando um objeto ...");
            
            Resposta r = (Resposta) entrada.readObject(); // lê o objeto
            r.imprimeProdutos();

            s.close(); // encerra a conexao com o servidor

            System.out.println( "Cliente: conexao encerrada");
        } catch (Exception e) {
            
            e.printStackTrace();
        } 


    }

}
