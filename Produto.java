import java.io.Serializable;


public class Produto implements Serializable{

    private String nome;
    private double preco;


    public Produto(String nome, double preco){
        this.nome = nome;
        this.preco = preco;
        
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
     * Retorna o nome do produto
     */
    public String nome(){
        return this.nome;
        
    }

    /**
     * Retorna o preço do produto
     */
    public String preco(){
        return String.valueOf(this.preco);
        
    }

}