import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Servidor {


    public static void main(String[] args) {

        Catalogo c = new Catalogo();

        c.addProduto("Geladeira Consul 500 litros", 2500.00, "Lojas Americanas");
        c.addProduto("Geladeira Brastemp 440 litros", 3800.00, "Casas Bahia");
        c.addProduto("Geladeira	Electrolux 380 litros", 2100.00, "Casas Bahia");
        c.addProduto("Geladeira	Electrolux 380 litros", 2300.00, "Magalu");
        c.addProduto("Geladeira	Brastemp 400 litros", 3700.00, "Magalu");

        


        try {
            ServerSocket ss = new ServerSocket(5000);

            while (true)
            {
                System.out.println( "Servidor aguardando um cliente ...");

                Socket t = ss.accept(); // bloqueia até receber pedido de conexão do cliente

                System.out.println( "Servidor: conexao feita");

                Trabalhador trab = new Trabalhador(t,c);
                trab.start();

            }

            //ss.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
