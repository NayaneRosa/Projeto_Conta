package conta;

import java.util.Scanner;

import conta.util.Cores;

import conta.controller.ContaController;
import conta.model.ContaCorrente;
import conta.model.ContaPoupança;

public class Menu {

	public Scanner leia = new Scanner(System.in);
	
	public static void main(String[] args) {
	
		ContaController contas = new ContaController(); 
		
		Scanner leia = new Scanner(System.in);
		
		int opcao, numero, agencia, tipo, aniversario = 0;
		String titular;
		float saldo, limite;
		
		System.out.println("\nCriar Contas\n");
		
		ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(), 123, 1, "João da Silva", 1000f, 100.0f);
		contas.cadastrar (cc1);
		
		ContaCorrente cc2 = new ContaCorrente(contas.gerarNumero(), 123, 1, "Maria da Silva", 2000f, 100.0f);
		contas.cadastrar (cc2);
		
		ContaPoupança cp1 = new ContaPoupança(contas.gerarNumero(), 123, 2, "Mariana dos Santos", 4000f, 12);
		contas.cadastrar (cp1);
		
		ContaPoupança cp2 = new ContaPoupança(contas.gerarNumero(), 123, 2, "juliana Ramos", 8000f, 15);
		contas.cadastrar (cp2);
		
		contas.listarTodas();
		
		// Teste da Classe Conta Corrente
		ContaCorrente cc11 = new ContaCorrente(2, 123, 1, "Mariana", 15000.0f, 1000.0f);
		cc11.visualizar();
		cc11.visualizar();
		cc11.depositar(5000.0f);
		cc11.visualizar();
		
		// Teste da Classe Conta poupança
		ContaPoupança cp11 = new ContaPoupança(3, 123, 2, "Victor", 100000.0f, 15);
		cp11.visualizar();
		cp11.sacar(1000.0f);
		cp11.depositar(5000.0f);
		cp11.visualizar();
				
		
		int opcao1;

		while (true) {
			
			System.out.println(Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND + "*****************************************************");
			System.out.println("                                                     ");
			System.out.println("           BANCO DO BRAZIL COM z                     ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("                                                     ");
			System.out.println("           1 - Criar Conta                           ");
			System.out.println("           2 - Listar todas as Contas                ");
			System.out.println("           3 - Buscar Conta por Numero               ");
			System.out.println("           4 - Atualizar Dados da Conta              ");
			System.out.println("           5 - Apagar Conta                          ");
			System.out.println("           6 - Sacar                                 ");
			System.out.println("           7 - Depositar                             ");
			System.out.println("           8 - Transferir valores entre Contas       ");
			System.out.println("           9 - Sair                                  ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("Entre com a opção desejada:                          ");
			System.out.println("                                                     " + Cores.TEXT_RESET);
			
			opcao1 = leia.nextInt();
			
		if (opcao1 == 9) {
			System.out.println(Cores.TEXT_WHITE_BOLD + "\nBanco do Brazil com Z - O seu Futuro começa aqui!");
			sobre();
			leia.close();
			System.exit(0);
		}
		switch (opcao1) {
		case 1: 
			System.out.println(Cores.TEXT_WHITE_BOLD + "Criar conta \n\n");
			
			System.out.println("Digite o Numero da Agência: ");
			agencia = leia.nextInt();
			System.out.println("Digite o Nome do Titular: ");
			leia.skip("\\R?");
			titular = leia.nextLine();
			
			do {
				System.out.println("Digite o Tipo da Conta (1-CC ou 2-CP): ");
				tipo = leia.nextInt();
			}while(tipo < 1 && tipo > 2);
			
			System.out.println("Digite o saldo da Conta (R$): ");
			saldo = leia.nextFloat();
			
			switch(tipo) {
			case 1 -> {
				System.out.println("Digite o Limite de Crédito (R$): ");
				limite = leia.nextFloat();
				contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
			}
			case 2 -> {
			System.out.println("Digite o dia do Aniversário da Conta: ");
			aniversario = leia.nextInt();
			contas.cadastrar(new ContaPoupança(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
			}
		}
		
			keyPress();
			break;
		case 2: 
			System.out.println(Cores.TEXT_WHITE_BOLD + "Listar todas as Contas\n\n");
			contas.listarTodas();
			
			break;
		case 3: 
			System.out.println(Cores.TEXT_WHITE_BOLD + "Consultar dados da Conta - por número\n\n");
			
			System.out.println("Digite o número da conta: ");
			numero = leia.nextInt();
			
			contas.procurarPorNumero(numero);
			
			keyPress();
			break;
		case 4: 
			System.out.println(Cores.TEXT_WHITE_BOLD + "Atualizar dados da Conta\n\n");
			System.out.println("Atualizar dados da Conta\n\n");
			
			System.out.println("Digite o numero da conta: ");
			numero = leia.nextInt();
			
			var buscaConta = contas.buscarNaCollection(numero);
			 
			if(buscaConta != null) {
				
				tipo = buscaConta.getTipo();
				
				System.out.println("Digite o numero da Agência: ");
				agencia = leia.nextInt();
				System.out.println("Digite o nome do Titular: ");
				leia.skip("\\R?");
				titular = leia.nextLine();
				
				System.out.println("Digite o Saldo da Conta (R$): ");
				saldo = leia.nextFloat();
				
				switch (tipo) {
				case 1 -> {
					System.out.println("Digite o Limite de Crédito (R$): ");
					limite = leia.nextFloat();
					
					contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
					
				}
				case 2 -> {
					System.out.println("Digite o dia do Aniversário da Conta (R$): ");
					limite = leia.nextInt();
					
					contas.atualizar(new ContaPoupança(numero, agencia, tipo, titular, saldo, aniversario));
					
				}
				default -> {
					System.out.println("Tipo de conta inválida");	
				}
			}
			}else {
				System.out.println("A conta não foi encontrada!");
			}
			
			keyPress();
			break;
		case 5: 
			System.out.println(Cores.TEXT_WHITE_BOLD + "Apagar a Conta\n\n");
			
			System.out.println("Digite o numero da Conta: ");
			numero = leia.nextInt();
			
			contas.deletar(numero);
			
			keyPress();
			break;
		case 6: 
			System.out.println(Cores.TEXT_WHITE_BOLD + "Saque\n\n");
			
			break;
		case 7: 
			System.out.println(Cores.TEXT_WHITE_BOLD + "Depósito\n\n");
			
			break;
		case 8: 
			System.out.println(Cores.TEXT_WHITE_BOLD + "Transferência entre Contas\n\n");
			
			break;
		default: 
			System.out.println(Cores.TEXT_RED_BOLD + "\nOpção Inválida!\n");
			break;
		}
		
	}
		
	}
    private static void keyPress() {
		
	}
	public static void sobre() {
    	 System.out.println("*****************************************************");
    	 System.out.println("Projeto Desenvolvido por: ");
    	 System.out.println("Nayane Rosa - nayaneksr95@gmail.com");
    	 System.out.println("github.com/conteudoGeneration");
    	 System.out.println("*****************************************************");
     }
    
}
