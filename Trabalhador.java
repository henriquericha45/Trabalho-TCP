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

    public void run() {

        int timeout = 2000;
        String nome = Thread.currentThread().getName();

        try
        {   
            ObjectOutputStream gravador = new ObjectOutputStream(t.getOutputStream());
            ObjectInputStream leitor = new ObjectInputStream(t.getInputStream());

            Instant momentoAgora = Instant.now();

            //Recebe string de busca enviada pelo cliente
            String s = (String) leitor.readObject();
            System.out.println(s);

            if(s.contains("admin")) {

                System.out.println(nome+": mensagem admin enviada");
                String log = new String(Files.readAllBytes(Paths.get("log.txt")));

                String texto_resposta = "\nTime out: "+timeout
                +"ms \n" + log;
                
                gravador.writeObject(texto_resposta);
                
                gravador.close();

            } else if(s.contains("timeout")) {

                s.replace("timeout ", "");
                timeout = Integer.parseInt(s);

                System.out.println(nome+": Timeout alterado >" + s);


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
                System.out.println( nome+": Enviando para o grupo");
                ms.send(busca);
                
                

                Resposta r = new Resposta();

                //temporizador
                Boolean continuar = true;

                DatagramPacket pacote = new DatagramPacket(new byte[500],500);
                DatagramSocket socket = new DatagramSocket(7000);
                
                socket.setSoTimeout(5000);
                

                //por 20 segundos recebe respostas
                while(continuar){

                    //Gera a lista de resposta

                    System.out.println(nome+": Aguardando pacote resposta");
                    try {

                        socket.receive(pacote);
                        
                        System.out.println(nome+": Pacote recebido");

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
            System.out.println( nome+": Conexao encerrada");
        }
        catch( Exception e )
        {
            System.out.println( e );
        }
    }
}
