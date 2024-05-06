package services;
 
import java.util.List;
import java.util.Scanner;

import models.Produto;
import models.Usuario;
import utils.GerenciadorDeProdutos;
import utils.GerenciadorDeUsuarios;
 
public class HandleMenu {
	Scanner sc = new Scanner(System.in);
	//Gerenciador
	GerenciadorDeUsuarios gs = new GerenciadorDeUsuarios();
	GerenciadorDeProdutos gp = new GerenciadorDeProdutos();
	public void menu() {
		System.out.println("Bem vindo! Qual menu deseja utilizar?");
		System.out.println("1- Menu de Usuarios");
		System.err.println("2- Menu de Produtos");
	}
	public void menuUsuario() {
		System.out.println("Menu de Usuarios:");
		System.out.println("1- Criar Usuario");
		System.out.println("2- Editar Usuario");
		System.out.println("3- Deletar Usuario");
		System.out.println("4- Listar Usuarios");
		System.out.println("5- Listar Usuario por Id");
		System.out.println("6- Login");
		System.out.println("7- Alterar senha");
		System.out.println("8- Sair");
	}
	public void MenuProduto() {
		System.out.println("Menu de Produtos:");
		System.out.println("1- Criar Produto");
		System.out.println("2- Editar Produto");
		System.out.println("3- Deletar Produto");
		System.out.println("4- Listar Produtos");
		System.out.println("5- Listar Produto por Id");
		System.out.println("6- Somar Preços");
		System.out.println("7- Quantidades totais de Produtos");
		System.out.println("8- Sair");
	}
	//Construtor vazio
	public HandleMenu() {
		gs.verificaECria("usuarios.txt");
		gp.verificaECriaProd("produto.txt");
	}
	public void criar() {
		System.out.println("Digite o nome: ");
		String nome = sc.next();
		System.out.println("Digite a senha: ");
		String senha = sc.next();
		int id = getNextId();
		Usuario user1 = new Usuario(id,nome,senha);
		gs.adicionarUsuario(user1);
	}
	public void editar() {
		System.out.println("Digite o ID do usuário: ");
		int id = sc.nextInt();
		System.out.println("Digite o novo nome: ");
		String nome = sc.next();
		System.out.println("Digite a nova senha: ");
		String senha = sc.next();
		gs.editarUsuario(id, nome, senha);
	}
	public void deletar() {
		System.out.println("Digite o Id do usuário a ser deletado: ");
		int id = sc.nextInt();
		gs.deletarUsuario(id);
	}
	public void login() {
		System.out.println("Digite o nome: ");
		String nome = sc.next();
		System.out.println("Digite a senha: ");
		String senha = sc.next();
		if (gs.login(nome,senha)) {
			System.out.println("Login realizado com sucesso!");
		} else {
			System.err.println("Credenciais inválidas! Tente novamente");
		};
	}
	public void sair() {
		System.out.println("Volte sempre!");
		System.exit(0);
	}
	public void listar() {
		gs.listarUsuario();
	}
	public void listarPorId() {
		System.out.println("Digite o ID a ser listado: ");
		int id = sc.nextInt();
		gs.listarPorId(id);
	}
	public void criarProd () {
		System.out.println("Digite o nome do produto: ");
		String nome = sc.next();
		System.out.println("Digite o preço: ");
		double preco = sc.nextDouble();
		System.out.println("Digite a quantidade: ");
		int quantidade = sc.nextInt();
		Produto produto1 = new Produto(quantidade, nome, preco, quantidade);	
		gp.adicionarProduto(produto1);
	}
	public void editarProd() {
		System.out.println("Digite o ID do produto: ");
		long id = sc.nextLong();
		System.out.println("Digite o novo nome: ");
		String nome = sc.next();
		System.out.println("Digite o novo preço: ");
		Double preco = sc.nextDouble();
		System.out.println("Digite a nova quantidade; ");
		int quantidade = sc.nextInt();
		gp.editarProduto(id, nome, preco, quantidade);
	}
	public void deletarProd() {
		System.out.println("Digite o Id do produto a ser deletado: ");
		long id = sc.nextLong();
		gp.deletarProduto(id);
	}
	public void listarProd() {
		gp.listarProduto();
	}
	public void listarPorIdProd() {
		System.out.println("Digite o ID a ser listado: ");
		long id = sc.nextLong();
		gp.listarPorIdProd(id);
	}
	public void somarPrecos () {
		System.out.println("Somando todos os preços");
		gp.somarPrecos();
	}
	public void quantidadeProd () {
		System.out.println("A seguir listaremos as quantidades de produtos:");
		gp.somarProdutos();
	}
	public void editarSenha() {
		gs.editarSenha();
	}
	
	private int getNextId() {
		List<Usuario> usuarios = gs.lerUsuarios();
		int maxId = 0;
		for(Usuario usuario : usuarios) {
			int id = usuario.getId();
			if (id > maxId) {
				//lógica para descobrir ultimo id
				maxId = id;
			}
		}
		return maxId + 1;
	}
	private long getNextIdProd() {
		List<Produto> produtos = gp.lerProdutos();
		long maxId = 0;
		for(Produto produto : produtos) {
			long id = produto.getId();
			if (id > maxId) {
				//lógica para descobrir ultimo id
				maxId = id;
			}
		}
		return maxId + 1;
	}
			
}