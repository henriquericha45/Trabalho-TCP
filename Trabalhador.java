import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;



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
            DataInputStream entrada = new DataInputStream( t.getInputStream());
            ObjectOutputStream gravador = new ObjectOutputStream(t.getOutputStream());

            //Recebe string de busca enviada pelo cliente
            String s = entrada.readUTF();
            
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
