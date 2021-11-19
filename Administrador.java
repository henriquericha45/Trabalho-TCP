import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;



public class Administrador {

    public static void main(String[] args) {
        

        // Abre conexão com o servidor
        try {

            Socket s = new Socket("127.0.0.1", 5000);
            System.out.println( "Administrador: conexao com servidor" );


            ObjectOutputStream gravador = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream leitor = new ObjectInputStream(s.getInputStream());

            //Solicita ao administrador a opcao
            System.out.println("\n1 - Informacoes\n2 - Alterar timeout");
            Scanner input = new Scanner(System.in); 
            String op = input.nextLine();
            

            if(op.contains("1")){

                //Envia o texto para o servidor
                gravador.writeObject("admin");
                

                System.out.println("Administrador: Aguardando");
                
                //Recebe o log do servidor            
                String log = (String) leitor.readObject();
                
                //Imprime oo log
                System.out.println(log);

            } else if (op.contains("2")) {

                System.out.println("\nNovo timeout:");
                String to = input.nextLine();
                
                //Envia o texto para o servidor
                gravador.writeObject("timeout "+to);
            }
            

            //Encerra a conexao
            s.close();

            input.close();

            System.out.println( "Administrador: Conexao encerrada");
        } catch (Exception e) {
            
            e.printStackTrace();
        } 


    }

}
