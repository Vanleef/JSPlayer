package home.dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JOptionPane;

import home.negocio.beans.Usuario;

public class RepositorioUsuarioArray implements IRepositorioUsuarios, Serializable {

	private static RepositorioUsuarioArray instanceUser;
	private Usuario[] usuarios;
	private int next;

	private RepositorioUsuarioArray(int tamanho) {
		this.usuarios = new Usuario[tamanho];
		this.next = 0;
	}

	public static synchronized RepositorioUsuarioArray getInstance() {
		if (instanceUser == null) {
			if (ler() == null) {
				instanceUser = new RepositorioUsuarioArray(100);
			} else {
				instanceUser = (RepositorioUsuarioArray) ler();
			}
		}
		return instanceUser;
	}

	public void salvar() {
		try {
			File f = new File("Usuarios\\RepositorioUsuarioArray.db");
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(instanceUser);
			oos.close();
			System.out.println("Objeto serializado com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static IRepositorioUsuarios ler() {
		IRepositorioUsuarios repo = null;
		try {
			File f = new File("Usuarios\\RepositorioUsuarioArray.db");

			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object o = ois.readObject();
			if (o != null) {
				repo = (RepositorioUsuarioArray) o;
				System.out.println("agora ele foi des-serializado com sucesso");
			}
			ois.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return repo;
	}

	public void cadastrar(Usuario u) {
		this.usuarios[this.next] = u;
		if (u.getEmail().equals(this.usuarios[this.next].getEmail())) {

			this.next = this.next + 1;
			if (this.next == this.usuarios.length) {
				this.duplicaArrayUsuario();
			}
			JOptionPane.showMessageDialog(null, "O USUARIO FOI CADASTRADO!");
		} else {
			JOptionPane.showMessageDialog(null, "O USUARIO JA EXISTE!");

		}
		salvar();
	}

	private void duplicaArrayUsuario() {
		if (this.usuarios != null && this.usuarios.length > 0) {
			Usuario[] arrayDuplicado = new Usuario[this.usuarios.length * 2];
			for (int i = 0; i < this.usuarios.length; i++) {
				arrayDuplicado[i] = this.usuarios[i];
			}
			this.usuarios = arrayDuplicado;
		}
	}

	public int procurarIndice(String email) {
		int i = 0;
		boolean achou = false;
		while ((!achou) && (i < this.next)) {
			if (email.equals(this.usuarios[i].getEmail())) {
				achou = true;
			} else {
				i++;
			}
		}
		return i;
	}

	public int procurarIndice(String nome, String email) {
		int i = 0;
		boolean achou = false;
		while ((!achou) && (i < this.next)) {
			if (nome.equals(this.usuarios[i].getNome()) && email.equals(this.usuarios[i].getEmail())) {
				achou = true;
			} else {
				i++;
			}
		}
		return i;
	}

	public void remover(String nome, String email) {
		int i = this.procurarIndice(nome, email);
		if (i != this.next) {
			this.usuarios[i] = this.usuarios[this.next - 1];
			this.usuarios[this.next - 1] = null;
			this.next = this.next - 1;
			JOptionPane.showMessageDialog(null, "O USUARIO FOI REMOVIDO!");
		} else {
			JOptionPane.showMessageDialog(null, "ERRO, O USUARIO NAO PODE SER REMOVIDO");
		}
	}

	public boolean existe(String email) {
		boolean existe = false;
		int indice = this.procurarIndice(email);
		if (indice != next) {
			existe = true;
			System.out.println("O USUARIO EXISTE!");
		}
		return existe;
	}

	public boolean existe(String nome, String email) {
		boolean existe = false;
		int indice = this.procurarIndice(nome, email);
		if (indice != next) {
			existe = true;
			System.out.println("O USUARIO EXISTE!");
		}
		return existe;
	}

	public Usuario procurar(String nome, String email) {
		int usearch = this.procurarIndice(nome, email);
		Usuario resultado = null;
		if (usearch != this.next) {
			resultado = this.usuarios[usearch];
		} else {
			JOptionPane.showMessageDialog(null, "O USUARIO NAO FOI ENCONTRADO.");
		}
		return resultado;
	}

	public void printar(Usuario u) {
		try {
			JOptionPane.showMessageDialog(null, u.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
