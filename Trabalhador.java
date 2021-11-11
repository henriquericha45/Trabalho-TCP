import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
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

            String s = entrada.readUTF();

            System.out.println( "Recebido: " + s);

            Resposta r = new Resposta(c.buscaProduto(s));

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
