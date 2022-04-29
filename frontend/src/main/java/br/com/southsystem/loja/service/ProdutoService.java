package br.com.southsystem.loja.service;

import br.com.southsystem.loja.exception.NegativoException;
import br.com.southsystem.loja.exception.ProdutoException;
import br.com.southsystem.loja.model.Produto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


public class ProdutoService {
    Scanner entrada = new Scanner(System.in);

    String url;
    HttpClient client;
    HttpRequest request;
    HttpResponse<String> response;
    ObjectMapper mapper = new ObjectMapper();

    public ProdutoService() {
        client = HttpClient.newHttpClient();
        url = "http://localhost:8080/produtos";
    }

    public void adicionarProduto() throws IOException, InterruptedException {
        System.out.println("\nADICIONAR PRODUTO");

        System.out.print("\nDigite o nome do produto: ");
        String nome = entrada.nextLine();

        System.out.print("\nDigite a categoria do produto: ");
        String categoria = entrada.nextLine();

        System.out.print("\nDigite o preço do produto: ");
        String stringPreco = entrada.nextLine();
        BigDecimal preco = new BigDecimal(stringPreco.replace(",", "."));

        if (preco.compareTo(BigDecimal.ZERO) < 0) {
            throw new NegativoException();
        }

        System.out.print("\nDigite a quantidade do produto em estoque: ");
        Integer qntEstoque = Integer.parseInt(entrada.nextLine());
        if (qntEstoque < 0) {
            throw new NegativoException();
        }

        Produto produto = new Produto(nome, categoria, preco, qntEstoque); //cria o objeto produto com os dados acima

        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writeValueAsString(produto);

        request = HttpRequest.newBuilder(
                        URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
    }

    public void listarProduto() throws InterruptedException {
        try {
            request = HttpRequest.newBuilder()
                    .uri(URI.create(url)).GET()
                    .build();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Produto[] produtos = mapper.readValue(response.body(), Produto[].class);

            List<Produto> lista = Arrays.asList(produtos);

            for (Produto produto : lista) {
                System.out.println(produto.toString());
            }
            if (lista.isEmpty()) {
                System.out.println("Ainda não há produtos cadastrados");
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private Produto buscarPorId(Long id) {
        try {
            Produto[] produtos = mapper.readValue(response.body(), Produto[].class);
            List<Produto> lista = Arrays.asList(produtos);
            if (lista.isEmpty()) {
                System.out.println("Ainda não há produtos cadastrados");
            }
            for (Produto produto : lista) {
                if (Objects.equals(id, produto.getId())) {
                    return produto;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void editarProduto() throws IOException, InterruptedException {
        listarProduto();
        System.out.println("EDITAR PRODUTO");
        System.out.print("Digite o código do produto que você deseja alterar: ");
        Long codigo = Long.valueOf(entrada.nextLine());

        Produto produtoEncontrado = buscarPorId(codigo);
        if(produtoEncontrado == null) {
            throw new ProdutoException("Produto com código não cadastrado");
        }

        System.out.println("\nATUALIZAR OS DADOS\n");
        System.out.print("Digite o novo nome do produto: ");
        String novoNome = entrada.nextLine();
        System.out.print("\nDigite a nova categoria do produto: ");
        String novaCategoria = entrada.nextLine();
        System.out.print("\nDigite o novo preço do produto: ");
        String stringNovoPreco = entrada.nextLine();
        BigDecimal novoPreco = new BigDecimal(stringNovoPreco.replace(",", "."));

        if (novoPreco.compareTo(BigDecimal.ZERO) < 0) {
            throw new NegativoException();
        }

        System.out.print("\nDigite a nova quantidade do produto em estoque: ");
        Integer novaQntEstoque = Integer.parseInt(entrada.nextLine());
        if (novaQntEstoque < 0) {
            throw new NegativoException();
        }

        produtoEncontrado.setNome(novoNome);
        produtoEncontrado.setCategoria(novaCategoria);
        produtoEncontrado.setPreco(novoPreco);
        produtoEncontrado.setQuantidade(novaQntEstoque);

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writeValueAsString(produtoEncontrado);

        request = HttpRequest.newBuilder(
                        URI.create(url + "/" + produtoEncontrado.getId()))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
    }

    public void deletarProduto() throws IOException, InterruptedException {
        listarProduto();
        System.out.println("REMOVER PRODUTO");
        System.out.print("Digite o código do produto que você deseja remover: ");
        Long codigo = Long.valueOf(entrada.nextLine());

        Produto produtoEncontrado = buscarPorId(codigo);
        if (produtoEncontrado == null) {
            throw new ProdutoException("\nNenhum produto com o código " + codigo + " foi encontrado!");
        }

        request = HttpRequest.newBuilder(
                        URI.create(url + "/" + produtoEncontrado.getId()))
                .header("Content-Type", "application/json")
                .DELETE()
                .build();

        response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
    }
}
