package br.com.southsystem.loja.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private BigDecimal preco;

    @Column(nullable = false)
    private Integer quantidade;


    public Produto() {
    }

    public Produto(Long id, String nome, String categoria, BigDecimal preco, Integer qntEstoque) {
        super();
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.quantidade = qntEstoque;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    @Override
    public String toString() {
        return id + "," + nome + "," + categoria.toUpperCase() + "," + preco.setScale(2, RoundingMode.HALF_EVEN) + "," + quantidade;
    }
}
