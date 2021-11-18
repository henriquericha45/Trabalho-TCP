import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.Instant;
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

            Instant momentoAgora = Instant.now();

            DataInputStream entrada = new DataInputStream( t.getInputStream());
            ObjectOutputStream gravador = new ObjectOutputStream(t.getOutputStream());

            //Recebe string de busca enviada pelo cliente
            String s = entrada.readUTF();

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

            t.close();
            System.out.println( "Servidor: conexao encerrada");
        }
        catch( Exception e )
        {
            System.out.println( e );
        }
    }
}
