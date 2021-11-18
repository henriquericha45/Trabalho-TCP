import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;



public class Trabalhador extends Thread {

    private Socket t;
    private Catalogo c;

    public Trabalhador( Socket t, Catalogo c)
    {
        this.t = t;
        this.c = c;
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

                System.out.println("Servidor: enviou");
                String log = new String(Files.readAllBytes(Paths.get("log.txt")));
                
                gravador.writeObject(log);
                
                gravador.close();

            } else{

                //Abre o arquivo e salva o log
                File file = new File("log.txt");
                FileWriter fr = new FileWriter(file, true);
                BufferedWriter br = new BufferedWriter(fr);
                br.newLine();
                br.write(s + " - " + momentoAgora);
                br.close();
                fr.close();
                
                //Gera a lista de resposta
                Resposta r = new Resposta(c.buscaProduto(s));

                //Envia resposta
                gravador.writeObject(r);

            }
          

            t.close();
            System.out.println( "Servidor: conexao encerrada");
        }
        catch( Exception e )
        {
            System.out.println( e );
        }
    }
}
