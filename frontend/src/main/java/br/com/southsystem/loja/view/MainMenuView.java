package br.com.southsystem.loja.view;

import java.util.Map;

public class MainMenuView {
    public String cabecalho() {
        StringBuilder cabecalho = new StringBuilder();
        cabecalho.append("+------------------------------------------+\n")
                .append("|          Sistema de Loja +PraTI          |\n")
                .append("+------------------------------------------+");

        return cabecalho.toString();
    }

    public void menu(Map<Integer, String> menus) {
        menus.forEach((chave, valor) -> {
            System.out.println("[" + chave + "] " + valor);
        });

        System.out.println(rodape());
        System.out.print("Escolha uma opção: ");
    }
    public String rodape() {
        return "+------------------------------------------+" ;
    }
}
