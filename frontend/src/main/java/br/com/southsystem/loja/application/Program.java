package br.com.southsystem.loja.application;

import br.com.southsystem.loja.service.ProdutoService;
import br.com.southsystem.loja.view.MainMenuView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner entrada = new Scanner(System.in);
        MainMenuView mainMenuView = new MainMenuView();
        ProdutoService produtoService = new ProdutoService();

        while(true) {
            System.out.println(mainMenuView.cabecalho());

            Map<Integer, String> menus = new HashMap<>();
            menus.put(1, "Adicionar produto");
            menus.put(2, "Editar produto");
            menus.put(3, "Remover produto");
            menus.put(0, "Sair do programa");

            mainMenuView.menu(menus);
            int opcao = Integer.parseInt(entrada.nextLine());

            switch (opcao) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    produtoService.adicionarProduto();
                    break;
                case 2:
                    produtoService.editarProduto();
                    break;
                case 3:
                    produtoService.deletarProduto();
                    break;
                default:
                    System.out.println("\nOpção Inválida\n");
            }
        }
    }
}
