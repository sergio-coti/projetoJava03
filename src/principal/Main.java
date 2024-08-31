package principal;

import java.util.Scanner;

import controllers.ProdutoController;

public class Main {

	public static void main(String[] args) {
	
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("\nSISTEMA DE CONTROLE DE PRODUTOS:\n");
		
		System.out.println("(1) CADASTRAR PRODUTOS");
		System.out.println("(2) CONSULTAR PRODUTOS");
		System.out.println("(3) ATUALIZAR PRODUTOS");
		System.out.println("(4) EXCLUIR PRODUTOS");
		
		System.out.print("\nINFORME A OPÇÃO DESEJADA: ");
		var opcao = scanner.nextLine();
		
		var produtoController = new ProdutoController();		
		
		switch(opcao) {
		case "1": produtoController.cadastrarProduto(); break;
		case "2": produtoController.consultarProdutos(); break;
		case "3": produtoController.atualizarProduto(); break;
		case "4": produtoController.excluirProduto(); break;
		}
		
		System.out.print("\nDESEJA CONTINUAR? (S,N): ");
		var continuar = scanner.nextLine();
		
		if(continuar.equalsIgnoreCase("S")) {
			//recursividade (voltar para o início do método main)
			main(args);
		}
		else {
			System.out.println("\nFIM DO PROGRAMA!");
		}
	}
}
