import java.util.ArrayList;


class Catalogo{

    private ArrayList<Produto> produtos = new ArrayList<Produto>();

    /**
     * Adiciona um produto
     * @param nome
     * @param preco
     */
    public void addProduto(String nome, Double preco, String nomeLoja){

        produtos.add(new Produto(nome, preco, nomeLoja));

    }

    /**
     * Busca Produtos no Catalogo
     * @param busca
     * @return lista de produtos
     */
    public ArrayList<Produto> buscaProduto(String busca){

        ArrayList<Produto> resposta = new ArrayList<Produto>();

        for (int index = 0; index < produtos.size(); index++) {

            if(produtos.get(index).comparaNome(busca)){
                resposta.add(produtos.get(index));
            }
        }
        return resposta;

    }



}

