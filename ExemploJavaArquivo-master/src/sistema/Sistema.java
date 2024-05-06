package sistema;
 
import java.util.Scanner;
 
import services.HandleMenu;
 
public class Sistema {
 
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		HandleMenu hm = new HandleMenu();
		int opcao = 0;
		HandleMenu menu = new HandleMenu();
		do {
			menu.menu();
			opcao = sc.nextInt();
			switch (opcao) {
			case 1: {
				menu.menuUsuario();
				int opcaoUsuario = sc.nextInt();
				switch (opcaoUsuario) {
				case 1: {
					hm.criar();
					continue;
				}
				case 2: {
					hm.editar();
					continue;
				}
				case 3: {
					hm.deletar();
					continue;
				}
				case 4: {
					hm.listar();
					continue;
				}
				case 5: {
					hm.listarPorId();
					continue;
				}
				case 6: {
					hm.login();
					continue;
				}
				case 7: {
					hm.editarSenha();
					continue;
				}
				case 8: {
					hm.sair();
				}
				default:{
					System.out.println("Opção inválida");
				} 
				while (opcaoUsuario != 9);
				sc.close();
				}
				continue;
			}case 2 : {
				menu.MenuProduto();
				int opcaoUsuario = sc.nextInt();
				switch (opcaoUsuario) {
				case 1: {
					hm.criarProd();
					continue;
				}
				case 2: {
					hm.editarProd();
					continue;
				}
				case 3: {
					hm.deletarProd();
					continue;
				}
				case 4: {
					hm.listarProd();
					continue;
				}
				case 5: {
					hm.listarPorIdProd();
					continue;
				}
				case 6: {
					hm.somarPrecos();
					continue;
				}
				case 7: {
					hm.quantidadeProd();
					continue;
				}
				case 8: {
					hm.sair();
				}
				default:{
					System.out.println("Opção inválida");
				} 
				while (opcaoUsuario != 9);
				sc.close();
				}
				continue;
			}
			default:
				System.out.println("Opção inválida");
			}
		} while (opcao != 9);
			sc.close();
	}
 
}