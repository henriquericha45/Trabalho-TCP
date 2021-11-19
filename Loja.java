import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Loja {
    

    public static void main(String[] args) {

        String nome = "Loja A";
        Catalogo c = new Catalogo();

        c.addProduto("Geladeira Consul 500 litros", 2500.00);
        c.addProduto("Geladeira Brastemp 440 litros", 3800.00);
        c.addProduto("Geladeira Electrolux 380 litros", 2100.00);
        c.addProduto("Geladeira Electrolux 380 litros", 2300.00);
        c.addProduto("Geladeira Brastemp 400 litros", 3700.00);



        try {
            
            InetAddress grupo = InetAddress.getByName("228.5.6.7");
            MulticastSocket s = new MulticastSocket(3000);
            System.out.println("Entrando no grupo ...");
            s.joinGroup(grupo);
            System.out.println("Ok");


            int x = 1;
            while (x==1)
            {
                byte[] mensagem_bytes = new byte[1000];
                DatagramPacket mensagem_pacote =
                    new DatagramPacket(mensagem_bytes, mensagem_bytes.length);

                System.out.print("Aguardando mensagem ...");
                s.receive(mensagem_pacote);
                System.out.println("Ok.");

                String dados_texto = new String(mensagem_pacote.getData(),
                        mensagem_pacote.getOffset(),mensagem_pacote.getLength());

                System.out.println("Mensagen recebida: "+ dados_texto);

                //
                Resposta r = new Resposta(c.buscaProduto(dados_texto));

                ByteArrayOutputStream buf =  new ByteArrayOutputStream();
                ObjectOutputStream gravador = new ObjectOutputStream(buf);
                gravador.writeObject(r);
                byte[] objeto_binario = buf.toByteArray();
                int tam = objeto_binario.length;
                System.out.println(tam);

                InetAddress endereco_servidor = InetAddress.getByName("127.0.0.1");
                int porta_servidor = 7000;
                DatagramPacket pacote = new DatagramPacket(objeto_binario, tam,
                        endereco_servidor, porta_servidor);
                DatagramSocket socket = new DatagramSocket();
                socket.send(pacote);
                System.out.println("Enviado!");
                socket.close();

            }

            System.out.println("Saindo do grupo ...");
            s.leaveGroup(grupo);
            s.close();
            System.out.println("Ok.");

        } catch (Exception e) { e.printStackTrace(); }

    }

}
