package home.GUI;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import home.negocio.Fachada;
import home.negocio.IFachada;
import home.negocio.beans.Musica;
import home.negocio.beans.Usuario;
import java.awt.Font;
import java.awt.SystemColor;

public class TelaCMusica extends JFrame {

	private JPanel contentPane;
	private JTextField textTitulo;
	private JTextField textArtista;
	private JTextField textGenero;
	private JTextField textAlbum;
	private JTextField textEndereco;
	private String end;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public TelaCMusica(Usuario u) {
		IFachada f = Fachada.getInstance();
		end = null;

		setTitle("JSPlayer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("Imagens\\f39bfcb5.png"));
		setBounds(100, 100, 850, 528);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNomeDaMsica = new JLabel("T\u00EDtulo:");
		lblNomeDaMsica.setForeground(SystemColor.controlHighlight);
		lblNomeDaMsica.setFont(new Font("OCR A Extended", Font.PLAIN, 14));
		lblNomeDaMsica.setBounds(12, 13, 103, 26);
		contentPane.add(lblNomeDaMsica);

		JLabel lblArtista = new JLabel("Artista:");
		lblArtista.setForeground(SystemColor.controlHighlight);
		lblArtista.setFont(new Font("OCR A Extended", Font.PLAIN, 14));
		lblArtista.setBounds(12, 52, 81, 16);
		contentPane.add(lblArtista);

		JLabel lbllbum = new JLabel("\u00C1lbum:");
		lbllbum.setForeground(SystemColor.controlHighlight);
		lbllbum.setFont(new Font("OCR A Extended", Font.PLAIN, 14));
		lbllbum.setBounds(12, 91, 56, 16);
		contentPane.add(lbllbum);

		JLabel lblAno = new JLabel("Ano:");
		lblAno.setForeground(SystemColor.controlHighlight);
		lblAno.setFont(new Font("OCR A Extended", Font.PLAIN, 14));
		lblAno.setBounds(12, 126, 56, 16);
		contentPane.add(lblAno);

		JLabel lblGnero = new JLabel("G\u00EAnero:");
		lblGnero.setForeground(SystemColor.controlHighlight);
		lblGnero.setFont(new Font("OCR A Extended", Font.PLAIN, 14));
		lblGnero.setBounds(12, 155, 56, 16);
		contentPane.add(lblGnero);

		textTitulo = new JTextField();
		textTitulo.setBounds(142, 15, 158, 22);
		contentPane.add(textTitulo);
		textTitulo.setColumns(10);

		textArtista = new JTextField();
		textArtista.setBounds(142, 49, 158, 22);
		contentPane.add(textArtista);
		textArtista.setColumns(10);

		textAlbum = new JTextField();
		textAlbum.setBounds(142, 88, 158, 22);
		contentPane.add(textAlbum);
		textAlbum.setColumns(10);

		JSpinner spinAno = new JSpinner();
		spinAno.setModel(new SpinnerNumberModel(new Integer(1900), new Integer(1500), null, new Integer(1)));
		spinAno.setBounds(142, 123, 97, 22);
		contentPane.add(spinAno);

		textGenero = new JTextField();
		textGenero.setBounds(142, 158, 158, 22);
		contentPane.add(textGenero);
		textGenero.setColumns(10);
		
		textEndereco = new JTextField();
		textEndereco.setBounds(115, 266, 258, 22);
		contentPane.add(textEndereco);
		textEndereco.setColumns(10);

		JButton btnCadastrar = new JButton("Confirmar");
		btnCadastrar.setFont(new Font("OCR A Extended", Font.PLAIN, 14));
		btnCadastrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (textTitulo.getText().length() > 0) {
					if (textArtista.getText().length() > 0) {
						if (textAlbum.getText().length() > 0) {
							if (textGenero.getText().length() > 0) {
								f.cadastrarMusica(new Musica(textTitulo.getText(), textArtista.getText(),
										textAlbum.getText(), spinAno.getValue(), textGenero.getText(), end));
								dispose();
								TelaUsuario telaUsuario = new TelaUsuario(u);
								telaUsuario.setVisible(true);
								telaUsuario.setResizable(false);
								telaUsuario.setLocationRelativeTo(null);
							} else {
								JOptionPane.showMessageDialog(null, "ERRO, GENERO INVALIDO");
							}
						} else {
							JOptionPane.showMessageDialog(null, "ERRO, ALBUM INVALIDO");
						}
					} else {
						JOptionPane.showMessageDialog(null, "ERRO, ARTISTA INVALIDO");
					}
				} else {
					JOptionPane.showMessageDialog(null, "ERRO, TITULO INVALIDO");
				}
			}
		});
		btnCadastrar.setBounds(305, 322, 105, 23);
		contentPane.add(btnCadastrar);

		JButton btnRetornar = new JButton("Retornar");
		btnRetornar.setFont(new Font("OCR A Extended", Font.PLAIN, 14));
		btnRetornar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				TelaUsuario telaUsuario = new TelaUsuario(u);
				telaUsuario.setVisible(true);
				telaUsuario.setResizable(false);
				telaUsuario.setLocationRelativeTo(null);
			}
		});
		btnRetornar.setBounds(80, 443, 97, 25);
		contentPane.add(btnRetornar);

		JButton btnEndereco = new JButton("Endere\u00E7o");
		btnEndereco.setFont(new Font("OCR A Extended", Font.PLAIN, 14));
		  btnEndereco.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
		    final JFileChooser jfc = new JFileChooser(new File("JSPMusicas\\"));
		    
		    FileFilter filter = new FileNameExtensionFilter("MP3 File","mp3");
		    jfc.setFileFilter(filter);
		    
		    int retorno = jfc.showOpenDialog(null);
		    if (retorno == JFileChooser.APPROVE_OPTION) {
		     end = jfc.getSelectedFile().getAbsolutePath();
		     end = end.substring(end.indexOf("JSPMusicas\\"));
		     textEndereco.setText(end);
		    }
		   }
		  });
		  btnEndereco.setBounds(115, 226, 97, 25);
		  contentPane.add(btnEndereco);
		  
		  JLabel label = new JLabel(" ");
			label.setIcon(new ImageIcon("Imagens\\JSPlayer 4.jpg"));
			label.setBounds(0, 0, 844, 500);
			contentPane.add(label);

	}
}
