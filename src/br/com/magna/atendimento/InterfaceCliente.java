package br.com.magna.atendimento;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

import br.com.magna.cardapio.Bebida;
import br.com.magna.cardapio.MeiaPizza;
import br.com.magna.cardapio.Pizza;

public class InterfaceCliente {

	private Pizza pizza = new Pizza("nome", 0);
	private Bebida bebida = new Bebida("nome", 0);
	private CadastroCliente cadastro = new CadastroCliente();
	private Carrinho pedido = new Carrinho();
	String opcaoPagamentoEscolhida = "";

	Scanner scan = new Scanner(System.in);

	private void clear() {
		for (int i = 0; i <= 40; i++) {
			System.out.println();
		}
	}

	private void exibirBoasVindas() {

		System.out.println("""
				Seja Bem-Vindo a Pizzaria Alves
				-------------------------------""");

		int menu1 = 0;

		do {

			System.out.println("""
					------- MENU PRINCIPAL --------

					1. Ver cardápio
					2. Pedido
					3. Sair
							""");

			System.out.println("Digite sua escolha: ");

			try {
				menu1 = scan.nextInt();

				if (menu1 == 1) {
					pizza.exibirPizzas();
					bebida.exibirBebidas();
					continue;
				} else if (menu1 == 2) {
					System.out.println("Para prosseguir com o pedido, é necessário se cadastrar!");
					cadastro.adicionaCadastro();
					break;
				} else if (menu1 == 3) {
					System.out.println("Obrigado pelo acesso!");
					System.exit(menu1);
					break;
				} else {
					clear();
					System.out.println("Opção inválida\n");
				}
			} catch (InputMismatchException e) {
				clear();
				System.out.println("Digito inválido. Digite uma opção válida.");
				scan.nextLine();
			}

		} while (menu1 != 2 || menu1 != 1);
	}

	private void exibirOpcoesMenu() {
		System.out.println(
			    "\n\nO que gostaria de pedir?\n\n" +
			    "1. Pizza Inteira\n" +
			    "2. Pizza Meia\n" +
			    "3. Bebida\n" +
			    "4. Remover item\n" +
			    "5. Concluir Pedido\n" +
			    "6. Cancelar Compra"
			);
	}

	private void escolheItens() {

		int menuPedido = 0;
		boolean itensEscolhidos = false;
		clear();

		while (!itensEscolhidos) {
			pedido.exibirCarrinho();
			exibirOpcoesMenu();

			try {
				menuPedido = scan.nextInt();
			} catch (InputMismatchException e) {
				scan.nextLine();
			}

			switch (menuPedido) {
			case 1:
				selecionarPizzaInteira();
				break;
			case 2:
				selecionarPizzaMeia();
				break;
			case 3:
				selecionarBebida();
				break;
			case 4:
				if (pedido.carrinhos.isEmpty()) {
					clear();
					System.out.println("Não há itens para serem removidos");
				} else {
					clear();
					removerItem();
				}
				break;
			case 5:
				if (pedido.carrinhos.isEmpty()) {
					clear();
					System.out.println("Não é possível concluir a compra. Carrinho vazio.");
				} else {
					clear();
					itensEscolhidos = true;
				}
				break;
			case 6:
				System.out.println("Até a próxima! :)");
				System.exit(menuPedido);
				break;
			default:
				clear();
				System.out.println("Opção inválida");
				System.out.println("Selecione novamente: ");
				break;
			}
		}
	}

	private void selecionarPizzaInteira() {

		int selecionaInteira = 0;
		clear();

		do {
			pizza.exibirPizzas();
			System.out.println("\nSelecione a Pizza que Gostaria de pedir");

			try {
				selecionaInteira = scan.nextInt();

				if (selecionaInteira <= 0 || selecionaInteira >= 11) {
					clear();
					System.out.println("\nOpção inválida.\n");
					System.out.println("Selecione Novamente: \n\n");
				}
			} catch (InputMismatchException e) {
				clear();
				System.out.printf("Digito inválido.%n");
				System.out.printf("Selecione Novamente: %n%n");
				scan.nextLine();
			}
		} while (selecionaInteira <= 0 || selecionaInteira >= 11);
		pedido.adicionarItem(pizza.listaPizza().get(selecionaInteira - 1));
		clear();
	}

	private void selecionarPizzaMeia() {

		int selecionaMeia1 = 0;
		int selecionaMeia2 = 0;
		clear();

		do {
			pizza.exibirPizzas();
			try {

				System.out.printf("Selecione a primeira pizza: %n%n");
				selecionaMeia1 = scan.nextInt();

				if (selecionaMeia1 <= 0 || selecionaMeia1 >= 11) {
					clear();
					System.out.println("\nOpção inválida.\n");
					System.out.println("Selecione Novamente: \n\n");
					continue;
				}
				System.out.println(pizza.listaPizza().get(selecionaMeia1 - 1));
				
				System.out.printf("%n%nSelecione a segunda pizza: %n%n");
				selecionaMeia2 = scan.nextInt();

				if (selecionaMeia2 <= 0 || selecionaMeia2 >= 11) {
					clear();
					System.out.println("\nOpção inválida.\n");
					System.out.println("Selecione Novamente: \n\n");
				}

				pedido.adicionarMeiaPizza(pizza.listaPizza().get(selecionaMeia1 - 1),
						pizza.listaPizza().get(selecionaMeia2 - 1));

				if (selecionaMeia1 == selecionaMeia2) {
					clear();
					System.out
							.printf("%nAs pizzas selecionadas são iguais. Por favor, selecione pizzas diferentes.%n%n");
				}

			} catch (InputMismatchException e) {
				clear();
				System.out.println("\nDigito inválido.\n");
				System.out.println("Selecione Novamente: \n\n");
				scan.nextLine();
			}
		} while (selecionaMeia1 == selecionaMeia2);
		clear();
	}

	private void selecionarBebida() {
		int selecionaBebida = 0;

		while (selecionaBebida == 0) {
			bebida.exibirBebidas();
			System.out.println("\nSelecione a bebida: ");
			try {
				selecionaBebida = scan.nextInt();
				if (selecionaBebida <= 0 || selecionaBebida >= 6) {
					bebida.exibirBebidas();
					clear();
					System.out.println("\nOpção inválida.\n");
					System.out.println("Selecione Novamente: \n\n");
					selecionaBebida = 0;
				} else {
					break;
				}
			} catch (InputMismatchException e) {
				clear();
				System.out.println("\nDigito inválido.\n");
				System.out.println("Selecione Novamente: \n\n");
				scan.nextLine();
				selecionaBebida = 0; // Reseta a seleção para continuar o loop
			}
		}
		// Certifica-se de que a seleção é válida antes de adicionar à lista
		pedido.adicionarItem(bebida.listaBebida().get(selecionaBebida - 1));
		clear();
	}

	private void removerItem() {
		int opcaoRemoverItem = 0;

		while (opcaoRemoverItem < pedido.carrinhos.size() || opcaoRemoverItem > pedido.carrinhos.size()) {
			pedido.exibirCarrinho();
			System.out.println("\n\nDigite o número do item que deseja remover: ");

			try {
				// Recebe opcao digitada
				opcaoRemoverItem = scan.nextInt();

				// verifica se a opção digitada esta entre as disponiveis
				if (opcaoRemoverItem > 0 && opcaoRemoverItem <= pedido.carrinhos.size()) {
					// remove o item desejado
					Object itemRemovido = pedido.carrinhos.remove(opcaoRemoverItem - 1);

					// Verifica o tipo do item removido e ajusta o preço total
					if (itemRemovido instanceof Pizza) {
						Pizza pizza = (Pizza) itemRemovido;
						pedido.precoTotal -= pizza.getPreco();
					} else if (itemRemovido instanceof Bebida) {
						Bebida bebida = (Bebida) itemRemovido;
						pedido.precoTotal -= bebida.getPreco();
					} else if (itemRemovido instanceof MeiaPizza) {
						MeiaPizza meiaPizza = (MeiaPizza) itemRemovido;
						pedido.precoTotal -= meiaPizza.getPreco();
					}

					System.out.println("\nItem removido: \n" + itemRemovido);
					break; // Sai do loop se a opção for válida
				} else {
					System.out.println("Opção inválido. Por favor, digite um número válido.");
				}
			} catch (InputMismatchException e) {
				clear();
				System.out.println("Opção inválida");
				scan.nextLine();
			}
		}
	}

	private void opcoesPagamento() {

		System.out.println(
			    "\n\n-------- PAGAMENTO --------\n\n" +
			    "Qual forma de pagamento?\n" +
			    "1. Dinheiro\n" +
			    "2. Pix\n" +
			    "3. Cartão Débito\n" +
			    "4. Cartão Crédito\n" +
			    "5. Vale Refeição\n" +
			    "6. Vale Alimentação\n" +
			    "---------------------------\n" +
			    "7. Voltar para o Carrinho\n" +
			    "8. Cancelar a compra"
			);

	}

	private void pagamento() {

		int opcaoPagamento = 0;
		opcaoPagamentoEscolhida = "";

		do {
			System.out.println("Selecione o opção");
			opcoesPagamento();
			try {
				opcaoPagamento = scan.nextInt();

				if (opcaoPagamento == 1) {
					opcaoPagamentoEscolhida = "Dinheiro";
					break;
				} else if (opcaoPagamento == 2) {
					opcaoPagamentoEscolhida = "Pix";
					break;
				} else if (opcaoPagamento == 3) {
					opcaoPagamentoEscolhida = "Cartão Débito";
					break;
				} else if (opcaoPagamento == 4) {
					opcaoPagamentoEscolhida = "Cartão Crédito";
					break;
				} else if (opcaoPagamento == 5) {
					opcaoPagamentoEscolhida = "Vale Refeição";
					break;
				} else if (opcaoPagamento == 6) {
					opcaoPagamentoEscolhida = "Vale Alimentações";
				} else if (opcaoPagamento == 7) {
					escolheItens();
				} else if (opcaoPagamento == 8) {
					System.exit(opcaoPagamento);
				} else {
					System.out.println("Opção inválida.");
					System.out.println("Selecione novamente: ");
				}

			} catch (InputMismatchException e) {
				clear();
				System.out.println("\nDigito inválido.\n");
				System.out.println("Selecione Novamente: \n\n");
				scan.nextLine();
			}

		} while (opcaoPagamento != 0);

	}
	
	public void recibo() {

		DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime horario = LocalDateTime.now();
		String dataHora = horario.format(dataFormat);
		StringBuilder cupomFiscal = new StringBuilder();
		clear();

		cupomFiscal.append("\n******************RECIBO******************\n\n");
		cupomFiscal.append(String.format("%29s", "PIZZARIA ALVES\n"));
		cupomFiscal.append(String.format("%31s", "RUA DOS TESTE, 123\n"));
		cupomFiscal.append(String.format("%27s", "SÃO PAULO\n\n"));
		cupomFiscal.append(String.format("%31s", "FONE (11)99999-9999\n"));
		cupomFiscal.append("CNPJ 99.999.999/0009-99 I.E. 999999999999\n");
		cupomFiscal.append(dataHora + "     CCF:000001   COO:000001\n");
		cupomFiscal.append("------------------------------------------\n");
		cupomFiscal.append(String.format("%28s", "CUPOM FISCAL\n"));
		cupomFiscal.append(String.format("%-4s %37s%n", "ITEM", "VALOR"));
		System.out.println(cupomFiscal.toString());
		pedido.listarCarrinho();
		System.out.println("\n" +opcaoPagamentoEscolhida);
		System.out.printf("%n============================================%n");
		cadastro.exibirCadastro();
		System.out.printf("%n============================================%n");
		System.out.printf("%30s", "VOLTE SEMPRE\n\n");
		

	}


	public void start() {
		exibirBoasVindas();
		escolheItens();
		pagamento();
		recibo();
	}

}
