package br.com.southsystem.loja.controller;

import br.com.southsystem.loja.LojaApplication;
import br.com.southsystem.loja.exception.ProdutoException;
import br.com.southsystem.loja.model.Produto;
import br.com.southsystem.loja.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/produtos")
@Api(value = "API Produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;
    private static final Logger LOG = Logger.getLogger(LojaApplication.class);

    @PostMapping
    @ApiOperation(value="Adicionar produto ao banco de dados")
    public ResponseEntity<?> adicionarProdutos(@RequestBody Produto produto) {
        try {
            produtoService.adicionar(produto);
            LOG.info("O produto " + produto.toString() + " foi adicionado ao banco de dados!\n");
            return ResponseEntity.status(HttpStatus.CREATED).body(produto);
        } catch (Exception e) {
            LOG.error("Houve um erro ao adicionar o produto ao banco de dados: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    @ApiOperation(value="Retornar uma lista dos produtos")
    public ResponseEntity<?> listarProdutos() {
        LOG.info("Lista de produtos: ");
        List<Produto> produtos = produtoService.listar();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{codigo}")
    @ApiOperation(value="Buscar um produto no banco de dados")
    public ResponseEntity<?> pesquisarProduto(@PathVariable(name = "codigo", required = true) Long codigo) throws ProdutoException {
        LOG.info("Pesquisando produto");
        Produto produto = produtoService.pesquisarPorCodigo(codigo);
        return ResponseEntity.ok(produto);
    }

    @PutMapping(path = "/{codigo}")
    @ApiOperation(value="Atualizar os dados de um produto")
    public ResponseEntity<?> atualizarProduto(@PathVariable(name = "codigo", required = true) Long codigo, @RequestBody Produto produto) throws ProdutoException {
        try {
            produto = produtoService.atualizar(codigo, produto);
            LOG.info("Produto " +produto.toString() + " atualizado!\n");
            return ResponseEntity.ok(produto);
        } catch (Exception e) {
            LOG.error("Houve um erro ao atualizar o produto: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping(path = "/{codigo}")
    @ApiOperation(value="Deletar um produto")
    public ResponseEntity<?> excluirProduto(@PathVariable(name = "codigo", required = true) Long codigo) throws ProdutoException {
        try {
            produtoService.excluir(codigo);
            return ResponseEntity.ok("Produto excluído!");
        } catch (Exception e) {
            LOG.error("Houve um erro ao excluir o produto: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

