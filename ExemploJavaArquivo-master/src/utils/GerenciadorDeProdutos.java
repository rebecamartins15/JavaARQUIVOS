package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.Produto;

public class GerenciadorDeProdutos {
		private static final String NOME_ARQUIVO = "produtos.txt";
		
		public void verificaECriaProd(String nomeArquivo) {
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
	
	public void adicionarProduto(Produto produto) {
		//BuffereWriter, FileWriter => Writer - escrever
		//BufferedWriter, proporciona uma eficiente escrita
		//FileWriter, escreve dentro do arquivo
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOME_ARQUIVO,true))){
			bw.write(produto.toString()); //1;arthur;12345
			bw.newLine(); //nova linha no arquivo txt
			System.out.println("Produto adicionado com sucesso!");
		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao escrever o produto! " + e.getMessage());
		}
	}
	public List<Produto> lerProdutos(){
		List<Produto> produtos = new ArrayList<Produto>();
		//Buffed, FIle, Reader
		try (BufferedReader br = new BufferedReader(new FileReader(NOME_ARQUIVO))){
			String linha; //Cada linha é um usuario nosso
			//percorrer todas as linhas enquanto for diferente de vazio
			while((linha = br.readLine()) != null) {
				String[] partes = linha.split(";"); //Dividir em três partes
				//Adicionar usuarios a lista
				produtos.add(new Produto(Long.parseLong(partes[0]),partes[1],Double.parseDouble(partes[2]), Integer.parseInt(partes[3])));
			}
		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao ler o arquivo! " + e.getMessage());
		}
		return produtos;
	}
	public void deletarProduto(long id) {
		List<Produto> produtos = lerProdutos();
		//array function => o usuario vai passar de linha em linha
		if(produtos.removeIf(produto -> produto.getId()==id)) {
			reescreverArquivoProd(produtos);
			System.out.println("Produto deletado com sucesso");
		}else {
			System.out.println("Produto não encontrado");
		}
	}
	public void reescreverArquivoProd(List<Produto> produtos) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOME_ARQUIVO))){
			for(Produto produto : produtos) {
				bw.write(produto.toString());
				bw.newLine();
			}
		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao reescrever o arquivo! " + e.getMessage());
		}
	}
	public void listarProduto() {
		List<Produto> produtos = lerProdutos();
		if(produtos.isEmpty()) {
			System.out.println("Nenhum produto cadastrado");
		}else {
			System.out.println("Lista de produto :");
			for(Produto produto : produtos) {
				System.out.println("ID: " + produto.getId() + ", Nome: " + "" + produto.getNome()+ ", Preço: " + produto.getPreco() + ", Quantidade: " + produto.getQuantidade());
			}
		}
	}
	public void editarProduto(long id, String novoNome, Double novoPreco, int novaQuantidade) {
		List<Produto> produtos = lerProdutos();
		boolean encontrado = false;
		for(Produto produto : produtos) {
			if(produto.getId() == id) {
				produto.setNome(novoNome);
				produto.setPreco(novoPreco);
				produto.setQuantidade(novaQuantidade);
				encontrado = true;
				break;
			}
		}
		if(encontrado) {
			reescreverArquivoProd(produtos);
			System.out.println("Produto editado com sucesso!");
		} else {
			System.out.println("Produto não encontrado!");
		}
	}
	public void listarPorIdProd(long id) {
		List<Produto> produtos = lerProdutos();
		boolean encontrado = false;
		for(Produto produto : produtos) {
			if(produto.getId() == id) {
				System.out.println("ID: " + produto.getId() + ", Nome: " + "" + produto.getNome()+ ", Preco: " + produto.getPreco()+ ", Quantidade: " + produto.getQuantidade());
				encontrado = true;
				break;
			}
		}
		if(! encontrado) {
			System.out.println("Produto não encontrado.");
		}
	}
	public void somarPrecos () {
		List<Produto> produtos = lerProdutos();
		if(produtos.isEmpty()) {
			System.out.println("Nenhum produto cadastrado");
		}else {
	        double somaPrecos = 0.0;
	        for (Produto produto : produtos) {
	            somaPrecos += produto.getPreco();
	        }
	        System.out.printf("A soma dos preços de todos os produtos cadastrados é: ", somaPrecos);
	    }
	}
	public void somarProdutos () {
		List<Produto> produtos = lerProdutos();
		if(produtos.isEmpty()) {
			System.out.println("Nenhum produto cadastrado");
		}else {
	        int quantidadeProd = produtos.size();
	        int quantidadeTotal = 0;
	        for (Produto produto: produtos) {
	        	quantidadeTotal += produto.getQuantidade();
	        	System.out.println("Temos cadastrados: " + quantidadeProd + " produtos e a quantidade total em estoque de: " + quantidadeTotal);
	        }
	    }
		
	}

}