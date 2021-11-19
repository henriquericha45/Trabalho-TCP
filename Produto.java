import java.io.Serializable;


public class Produto implements Serializable{

    private String nome;
    private double preco;
    private String nomeLoja;


    public Produto(String nome, double preco, String nomeLoja){

        this.nome = nome;
        this.preco = preco;
        this.nomeLoja = nomeLoja;
    
    }

    /**
     * Compara uma string com o nome do Produto
     * @param busca
     * @return boolean
     */
    public boolean comparaNome(String busca){
        return nome.toLowerCase().contains(busca.toLowerCase());
    }


    /**
     * Imprime os dados do produto
     */
    public void imprimirProduto(){
        System.out.println(this.nome + " -  R$" + this.preco + " - " + this.nomeLoja);
        
    }


}