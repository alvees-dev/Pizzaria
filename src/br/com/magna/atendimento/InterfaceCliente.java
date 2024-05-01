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

	Scanner scan = new Scanner(System.in);

	private void clear() {
		for (int i = 0; i <= 40; i++) {
			System.out.println();
		}
	}

	private void exibirBoasVindas() {

		System.out.println("""
				Seja Bem-Vindo a Pizzaria Alves
				-------------------------------
				""");

		int menu1 = 0;

		do {

			System.out.println("""
					------- Menu Principal --------

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

	private void escolheItens() {

		int menuPedido = 0;

		do {
			clear();
			pedido.exibirCarrinho();
			exibirOpcoesMenu();

			try {
				menuPedido = scan.nextInt();
			} catch (InputMismatchException e) {
				scan.nextLine();
			}

			switch (menuPedido) {
			case 1:
				clear();
				selecionarPizzaInteira();
				break;
			case 2:
				clear();
				selecionarPizzaMeia();
				break;
			case 3:
				clear();
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
					break;
				}
				break;
			case 6:
				System.exit(menuPedido);
			default:
				clear();
				System.out.println("Opção inválida");
				break;
			}
		} while (menuPedido != 6);
	}

	private void exibirOpcoesMenu() {
		System.out.println("\n\nO que gostaria de pedir?\n");
		System.out.println("1. Pizza Inteira");
		System.out.println("2. Pizza Meia");
		System.out.println("3. Bebida");
		System.out.println("4. Remover item");
		System.out.println("5. Concluir Pedido");
		System.out.println("6. Cancelar Compra");
	}

	private void selecionarPizzaInteira() {

		int selecionaInteira = 0;

		do {
			clear();
			pizza.exibirPizzas();
			System.out.println("\nSelecione a Pizza que Gostaria de pedir");

			try {
				selecionaInteira = scan.nextInt();
			} catch (InputMismatchException e) {
				clear();
				System.out.println("Opção inválida");
				scan.nextLine();
			}

			if (selecionaInteira <= 0 || selecionaInteira >= 11) {
				clear();
				System.out.println("Digito inválido");
			}
		} while (selecionaInteira <= 0 || selecionaInteira >= 11);
		pedido.adicionarItem(pizza.listaPizza().get(selecionaInteira - 1));

		System.out.println("Opção inválida, digite novamente!");

	}

	private void selecionarPizzaMeia() {

		int selecionaMeia1 = 0;
		int selecionaMeia2 = 0;

		do {
			pizza.exibirPizzas();
			try {

				System.out.println("Selecione a primeira pizza: ");
				selecionaMeia1 = scan.nextInt();

				if (selecionaMeia1 <= 0 || selecionaMeia1 >= 11) {
					clear();
					System.out.println("Opção Inválida");
					break;
				}
				System.out.println(pizza.listaPizza().get(selecionaMeia1 - 1));
				System.out.println("Selecione a segunda pizza: ");
				selecionaMeia2 = scan.nextInt();

				if (selecionaMeia2 <= 0 || selecionaMeia2 >= 11) {
					clear();
					System.out.println("Opção Inválida");
					continue;
				}

				pedido.adicionarMeiaPizza(pizza.listaPizza().get(selecionaMeia1 - 1),
						pizza.listaPizza().get(selecionaMeia2));

				if (selecionaMeia1 == selecionaMeia2) {
					clear();
					System.out.println("\nAs pizzas selecionadas são iguais. Por favor, selecione pizzas diferentes.");
				}

			} catch (InputMismatchException | IndexOutOfBoundsException e) {
				System.out.println("Opção inválida");
				scan.nextLine();
			}
		} while (selecionaMeia1 == selecionaMeia2);
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
					System.out.println("Opção inválida\n");
					selecionaBebida = 0;
				} else {
					break;
				}
			} catch (InputMismatchException e) {
				clear();
				System.out.println("Opção Inválida!");
				scan.nextLine(); // Limpa o buffer de entrada
				selecionaBebida = 0; // Reseta a seleção para continuar o loop
			}
		}
		// Certifica-se de que a seleção é válida antes de adicionar à lista
		pedido.adicionarItem(bebida.listaBebida().get(selecionaBebida - 1));

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

	private void pagamento() {
		
	}

	private void recibo() {
		clear();
		
		DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime horario = LocalDateTime.now();
		String dataHora = horario.format(dataFormat);

		System.out.println("********** Recibo **********");
		System.out.println("\nData e Hora da Compra: " + dataHora);
		System.out.println("\nItens: \n");
		pedido.listarCarrinho();
		System.out.println("\n*****************************\n");
		cadastro.exibirCadastro();
	}

	public void play() {
		exibirBoasVindas();
		escolheItens();
		pagamento();
		recibo();
	}

}
