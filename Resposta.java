import java.io.Serializable;
import java.util.ArrayList;

public class Resposta implements Serializable{

    private ArrayList<Produto> produtos_resposta;

    public Resposta(ArrayList<Produto> lista){
        this.produtos_resposta = lista;
    }

    public void imprimeProdutos(){
        for (int index = 0; index < produtos_resposta.size(); index++) {

           produtos_resposta.get(index).imprimeNome();
        }
    }


}