import java.io.Serializable;
import java.util.ArrayList;



public class Resposta implements Serializable{

    public String a;

    private ArrayList<Produto> produtos_resposta;

    public Resposta(ArrayList<Produto> lista){
        this.produtos_resposta = lista;
    }

    public Resposta(){
        this.produtos_resposta = new ArrayList<Produto>();
        
    }

    /**
     * Concatena produtos a lista
     * @param Resposta r
     */
    public void concatenaProdutos(Resposta r){
        this.produtos_resposta.addAll(r.listaProdutos());
        this.imprimeProdutos();
    }

    
    /**
     * Retorna lista de produtos
     * @return Produtos
     */
    public ArrayList<Produto> listaProdutos(){
        return this.produtos_resposta;
    }


    /**
     * Imprime os Produtos contidos na Resposta
     */
    public void imprimeProdutos(){
        for (Produto produto : produtos_resposta) {
            System.out.println(produto.nome());
        }
    }


}