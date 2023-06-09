package home.dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import home.negocio.CustomPlayer;
import home.negocio.beans.Musica;

public class RepositorioMusicaArray implements IRepositorioMusica, Serializable {

	private static RepositorioMusicaArray instanceMusic;
	private ArrayList<Musica> musicas;

	private RepositorioMusicaArray() {
		this.musicas = new ArrayList<Musica>();
	}

	public static synchronized RepositorioMusicaArray getInstance() {
		if (instanceMusic == null) {
			if (ler() == null) {
				instanceMusic = new RepositorioMusicaArray();
			} else {
				instanceMusic = (RepositorioMusicaArray) ler();
			}
		}
		return instanceMusic;
	}

	public void salvar() {
		try {
			File f2 = new File("BancoDeMusicas\\RepositorioMusicaArray.db");
			FileOutputStream fos = new FileOutputStream(f2);
			ObjectOutputStream oms = new ObjectOutputStream(fos);
			oms.writeObject(instanceMusic);
			oms.close();
			System.out.println("Objeto serializado com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static IRepositorioMusica ler() {
		IRepositorioMusica repo = null;
		try {
			File f = new File("BancoDeMusicas\\RepositorioMusicaArray.db");

			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream mis = new ObjectInputStream(fis);
			Object mo = mis.readObject();
			if (mo != null) {
				repo = (RepositorioMusicaArray) mo;
				System.out.println("MUSICA agora ele foi des-serializado com sucesso");
			}
			mis.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return repo;
	}

	public void cadastrar(Musica mus) {
		for (Musica s : musicas) {
			if (mus.getTitulo().equals(s.getTitulo()) && mus.getArtista().equals(s.getArtista())
					|| mus.getEndereco().equals(s.getEndereco())) {
				mus = null;
				JOptionPane.showMessageDialog(null, "ERRO!\nMUSICA JA CADASTRADA!");
			}
		}
		if (mus != null) {
			musicas.add(mus);
			salvar();
		}
	}

	public void remover(Musica s) {
		try {
			musicas.remove(musicas.indexOf(s));
			JOptionPane.showMessageDialog(null, "MUSICA REMOVIDA");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO!\nMUSICA NÃO PÔDE SER REMOVIDA!");
			e.printStackTrace();
		}
	}

	public Musica procurar(String titulo, String artista) {
		Musica resultado = null;
		for (Musica s : musicas) {
			if (s.getTitulo().equals(titulo) && s.getArtista().equals(artista)) {
				resultado = s;
			}
		}
		return resultado;
	}

	public void printar(Musica m) {
		try {
			JOptionPane.showMessageDialog(null, m.toString());
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "NADA PARA MOSTRAR!");
		}
	}

	public String[] retornaMusicas() {
		int i = 0;
		String[] listaMusicas = new String[0];
		for (Musica m : musicas) {
			if (m != null) {
				String[] listaMusicas2 = new String[listaMusicas.length + 1];
				for (int h = 0; h < listaMusicas.length; h++) {
					listaMusicas2[h] = listaMusicas[h];
				}
				listaMusicas = listaMusicas2;
				listaMusicas[i] = m.getTitulo() + "-" + m.getArtista();
			}
			i++;
		}
		return listaMusicas;
	}

	public ArrayList<Musica> getMusicas() {
		return musicas;
	}

}
