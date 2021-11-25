import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class LojaB {
    

    public static void main(String[] args) {

        String nome = "Loja B";
        Catalogo c = new Catalogo();

        c.addProduto("Geladeira Consul 500 litros", 2200.00,nome);
        c.addProduto("Geladeira Brastemp 440 litros", 4000.00,nome);
        c.addProduto("Fogao Electrolux 4 bocas", 1500.00,nome);
        c.addProduto("Frigobar Electrolux 50 litros", 900.00,nome);




        try {
            
            InetAddress grupo = InetAddress.getByName("228.5.6.7");
            MulticastSocket s = new MulticastSocket(3000);
            System.out.println(nome+": Entrando no grupo");
            s.joinGroup(grupo);


            int x = 1;
            while (x==1)
            {
                byte[] mensagem_bytes = new byte[1000];
                DatagramPacket mensagem_pacote =
                    new DatagramPacket(mensagem_bytes, mensagem_bytes.length);

                System.out.println(nome+": Aguardando busca");
                s.receive(mensagem_pacote);
                

                String dados_texto = new String(mensagem_pacote.getData(),
                        mensagem_pacote.getOffset(),mensagem_pacote.getLength());

                System.out.println(nome+": Mensagen recebida > "+ dados_texto);

                //Cria resposta com catalogo
                Resposta r = new Resposta(c.buscaProduto(dados_texto));

                ByteArrayOutputStream buf =  new ByteArrayOutputStream();
                ObjectOutputStream gravador = new ObjectOutputStream(buf);
                gravador.writeObject(r);
                byte[] objeto_binario = buf.toByteArray();
                int tam = objeto_binario.length;
                

                InetAddress endereco_servidor = InetAddress.getByName("127.0.0.1");
                int porta_servidor = 7000;
                DatagramPacket pacote = new DatagramPacket(objeto_binario, tam,
                        endereco_servidor, porta_servidor);
                DatagramSocket socket = new DatagramSocket();
                socket.send(pacote);
                System.out.println(nome+": Resposta enviada");
                socket.close();

            }

            System.out.println(nome+": Saindo do grupo");
            s.leaveGroup(grupo);
            s.close();
            

        } catch (Exception e) { e.printStackTrace(); }

    }

}
