import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;



public class Cliente {

    public static void main(String[] args) {
        

        // Abre conexão com o servidor
        try {

            Socket s = new Socket("127.0.0.1", 5000);
            System.out.println( "Cliente: conexao feita com o servidor" );

            ObjectOutputStream gravador = new ObjectOutputStream(s.getOutputStream());
            
            //Solicita ao usuário o texto para busca
            System.out.println("Insira o produto que deseja procurar: ");
            Scanner input = new Scanner(System.in); 
            String busca = input.nextLine();
            input.close();
            
            //Envia o texto para o servidor
            gravador.writeObject(busca);

            ObjectInputStream leitor = new ObjectInputStream(s.getInputStream());

            System.out.println("Aguardando um objeto ...");
            
            //Recebe o objeto do servidor
            Resposta r = (Resposta) leitor.readObject();
            
            //Imprime os produtos para o cliente
            r.imprimeProdutos();

            //Encerra a conexao
            s.close();

            System.out.println( "Cliente: conexao encerrada");
        } catch (Exception e) {
            
            e.printStackTrace();
        } 


    }

}
