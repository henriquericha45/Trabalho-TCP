import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;



public class Trabalhador extends Thread {

    private Socket t;
    private MulticastSocket ms;

    public Trabalhador( Socket t, MulticastSocket ms)
    {
        this.t = t;
        this.ms = ms;
    }

    public void run()
    {
        try
        {   
            ObjectOutputStream gravador = new ObjectOutputStream(t.getOutputStream());
            ObjectInputStream leitor = new ObjectInputStream(t.getInputStream());

            Instant momentoAgora = Instant.now();

            //Recebe string de busca enviada pelo cliente
            String s = (String) leitor.readObject();
            System.out.println(s);

            if(s.contains("admin")){

                System.out.println("Servidor: mensagem admin enviada");
                String log = new String(Files.readAllBytes(Paths.get("log.txt")));
                
                gravador.writeObject(log);
                
                gravador.close();

            } else {

                //Abre o arquivo e salva o log
                File file = new File("log.txt");
                FileWriter fr = new FileWriter(file, true);
                BufferedWriter br = new BufferedWriter(fr);
                br.newLine();
                br.write(s + " - " + momentoAgora);
                br.close();
                fr.close();

                //UDP multicast
                InetAddress grupo = InetAddress.getByName( "228.5.6.7" );
                DatagramPacket busca = new DatagramPacket( s.getBytes(), s.length(), grupo, 3000 );
                
                //Envia a busca as lojas
                System.out.println( "Servidor: Enviando para o grupo" + s.length() );
                ms.send(busca);
                System.out.println( "Ok." );
                

                Resposta r = new Resposta();

                //temporizador
                Boolean continuar = true;

                DatagramPacket pacote = new DatagramPacket(new byte[500],500);
                DatagramSocket socket = new DatagramSocket(7000);
                
                socket.setSoTimeout(5000);
                

                //por 20 segundos recebe respostas
                while(continuar){

                    //Gera a lista de resposta

                    System.out.println("Servidor: Aguardando pacote resposta");
                    try {

                        socket.receive(pacote);
                        
                        System.out.println("Servidor: Pacote recebido");

                        byte[] objeto_binario = pacote.getData();
                        ByteArrayInputStream buf =  new ByteArrayInputStream(objeto_binario);
                        ObjectInputStream l = new ObjectInputStream(buf);

                        Resposta rr = (Resposta) l.readObject();
        
                        r.concatenaProdutos(rr);


                    } catch (Exception e) {

                        continuar = false;

                    }
                    
                    
                    
                }
                
                socket.close();

                
                //Envia resposta
                gravador.writeObject(r);

            }
          

            t.close();
            System.out.println( "Servidor: Conexao encerrada");
        }
        catch( Exception e )
        {
            System.out.println( e );
        }
    }
}
