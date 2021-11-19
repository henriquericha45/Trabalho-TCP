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
        
    }

    /**
     * Adiciona Mensagem
     */
    public void add(String nome){
        produtos_resposta.add(new Produto(nome));
        
    }

    /**
     * Verifica se esta vazio
     */
    public boolean verifica(){
        return produtos_resposta.isEmpty();
        
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
        System.out.println("\n-----------------LISTA DE PRODUTOS-----------------\n");
        for (Produto produto : produtos_resposta) {
            produto.imprimirProduto();
        }
    }

    /**
     * Gera resposta
     */
    public String respostaProdutos(){
        String r = "";
        r = r + "\n-----------------LISTA DE PRODUTOS-----------------\n";
        for (Produto produto : produtos_resposta) {
            r = r + produto.imprimirProduto();
        }
        return r;
    }


}