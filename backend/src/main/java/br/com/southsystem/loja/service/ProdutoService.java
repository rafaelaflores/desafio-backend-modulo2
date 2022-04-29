package br.com.southsystem.loja.service;

import br.com.southsystem.loja.exception.ProdutoException;
import br.com.southsystem.loja.model.Produto;
import br.com.southsystem.loja.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public void adicionar(Produto produto) {
        produtoRepository.saveAndFlush(produto);
    }

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    public Produto pesquisarPorCodigo(Long codigo) throws ProdutoException {
        Optional<Produto> optionalProduto = getOptional(codigo);
        Produto resultado = null;
        if(optionalProduto.isPresent()) {
            resultado = optionalProduto.get();
        } else {
            throw new ProdutoException("Produto não encontrado");
        }
        return resultado;
    }

    private Optional<Produto> getOptional(Long codigo) {
        return produtoRepository.findById(codigo);
    }

    public void excluir(Long codigo) throws ProdutoException {
       Optional<Produto> optionalProduto = getOptional(codigo);
       if (optionalProduto.isPresent()) {
           produtoRepository.delete(optionalProduto.get());
       } else {
           throw new ProdutoException("Produto não encontrado");
       }
   }
    public Produto atualizar(Long codigo, Produto produto) throws ProdutoException {
        Assert.notNull(codigo, "Não foi possível atualizar o registro");
        Optional<Produto> optionalProduto = getOptional(codigo);
        if (optionalProduto.isPresent()) {
            Produto db = optionalProduto.get();
            db.setNome(produto.getNome());
            db.setPreco(produto.getPreco());
            db.setCategoria(produto.getCategoria());
            db.setQuantidade(produto.getQuantidade());
            produtoRepository.save(db);
            return db;
        } else {
            throw new ProdutoException("Produto não encontrado!");
        }
    }

}
