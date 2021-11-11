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

    public boolean comparaNome(String busca){
        return nome.toLowerCase().contains(busca.toLowerCase());
    }

    public void imprimeNome(){
        System.out.println(this.nome);
        
    }
}