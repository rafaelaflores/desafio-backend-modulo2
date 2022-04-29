package br.com.southsystem.loja.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
    private Long id;
    private String nome;
    private String categoria;
    private BigDecimal preco;
    private Integer quantidade;

    public Produto(String nome, String categoria, BigDecimal preco, Integer quantidade) {
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria.toUpperCase();
    }

    public String toString() {
        return "\n===== Produto " + nome + " =====\n"
                + "- Código do produto: " + id + "\n" +
                "- Categoria do produto: " + categoria.toUpperCase() + "\n" +
                "- Preço do produto: R$ " + preco.setScale(2, RoundingMode.HALF_EVEN) + "\n"
                + "- Quantidade em estoque: " + quantidade + "\n";
    }
}
