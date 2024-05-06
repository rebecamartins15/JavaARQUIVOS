package utils;
 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.Usuario;
 
public class GerenciadorDeUsuarios {
	Scanner sc = new Scanner(System.in);
	private static final String NOME_ARQUIVO = "usuarios.txt";
	//Verificar a existência do nosso banco de dados e criar caso não exista.
	public void verificaECria(String nomeArquivo) {
		File arquivo = new File(nomeArquivo);
		//Verificar se o arquivo existe
		if(arquivo.exists()) {
			System.out.println("Banco funcionando");
		}else {
			//Tente criar, caso de erro, exiba o erro
			try {
				//Criar um novo arquivo
				arquivo.createNewFile();
			} catch (IOException e) {
				System.out.println("Ocorreu um erro ao criar o arquivo: " +e.getMessage());
			}
		}
	}
	public void adicionarUsuario(Usuario usuario) {
		//BuffereWriter, FileWriter => Writer - escrever
		//BufferedWriter, proporciona uma eficiente escrita
		//FileWriter, escreve dentro do arquivo
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOME_ARQUIVO,true))){
			bw.write(usuario.toString()); //1;arthur;12345
			bw.newLine(); //nova linha no arquivo txt
			System.out.println("Usuário adicionado com sucesso!");
		} catch (IOException e) {
			System.out.println("ocorreu um erro ao escrever o usuário! " + e.getMessage());
		}
	}
	public List<Usuario> lerUsuarios(){
		List<Usuario> usuarios = new ArrayList<Usuario>();
		//Buffed, FIle, Reader
		try (BufferedReader br = new BufferedReader(new FileReader(NOME_ARQUIVO))){
			String linha; //Cada linha é um usuario nosso
			//percorrer todas as linhas enquanto for diferente de vazio
			while((linha = br.readLine()) != null) {
				String[] partes = linha.split(";"); //Dividir em três partes
				//Adicionar usuarios a lista
				usuarios.add(new Usuario(Integer.parseInt(partes[0]),partes[1],partes[2]));
			}
		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao ler o arquivo! " + e.getMessage());
		}
		return usuarios;
	}
	public void deletarUsuario(int id) {
		List<Usuario> usuarios = lerUsuarios();
		//array function => o usuario vai passar de linha em linha
		if(usuarios.removeIf(usuario -> usuario.getId()==id)) {
			reescreverArquivo(usuarios);
			System.out.println("Usuário deletado com sucesso");
		}else {
			System.out.println("Usuário não encontrado");
		}
	}
	public void reescreverArquivo(List<Usuario> usuarios) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOME_ARQUIVO))){
			for(Usuario usuario : usuarios) {
				bw.write(usuario.toString());
				bw.newLine();
			}
		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao reescrever o arquivo! " + e.getMessage());
		}
	}
	public void listarUsuario() {
		List<Usuario> usuarios = lerUsuarios();
		if(usuarios.isEmpty()) {
			System.out.println("Nenhum usuário cadastrado");
		}else {
			System.out.println("Lista de usuários :");
			for(Usuario usuario: usuarios) {
				System.out.println("ID: " + usuario.getId() + ", Nome: " + "" + usuario.getNome()+ ", Senha: " + usuario.getSenha());
			}
		}
	}
	public void editarUsuario(int id, String novoNome, String novaSenha) {
		List<Usuario> usuarios = lerUsuarios();
		boolean encontrado = false;
		for(Usuario usuario : usuarios) {
			if(usuario.getId() == id) {
				usuario.setNome(novoNome);
				usuario.setSenha(novaSenha);
				encontrado = true;
				break;
			}
		}
		if(encontrado) {
			reescreverArquivo(usuarios);
			System.out.println("Usuário editado com sucesso!");
		} else {
			System.out.println("Usuário não encontrado!");
		}
	}
	public void listarPorId(int id) {
		List<Usuario> usuarios = lerUsuarios();
		boolean encontrado = false;
		for(Usuario usuario : usuarios) {
			if(usuario.getId() == id) {
				System.out.println("ID: " + usuario.getId() + ", Nome: " + "" + usuario.getNome()+ ", Senha: " + usuario.getSenha());
				encontrado = true;
				break;
			}
		}
		if(! encontrado) {
			System.out.println("Usuário não encontrado.");
		}
	}
	public boolean login(String nome, String senha) {
		List<Usuario> usuarios = lerUsuarios();
		for(Usuario usuario : usuarios) {
			if (usuario.getNome().equals(nome) && usuario.getSenha().equals(senha)) {
				return true;
			}
		}
		return false;

	}
	public void editarSenha() {
		System.out.println("Faça seu login: ");
		System.out.println("Digite o nome: ");
		String nome = sc.next();
		System.out.println("Digite a senha: ");
		String senha = sc.next();
		if (login(nome,senha)) {
			System.out.println("Login realizado com sucesso!");
			System.out.println("Digite a nova senha:");
			String novaSenha = sc.next();
			List<Usuario> usuarios = lerUsuarios();
			for(Usuario usuario : usuarios) {
				usuario.setSenha(novaSenha);
			}
		} else {
			System.err.println("Credenciais inválidas! Tente novamente");
		};
	}








}