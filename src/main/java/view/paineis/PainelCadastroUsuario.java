package view.paineis;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JFormattedTextField;

import model.exception.CampoInvalidoException;
import model.exception.CpfDuplicadoException;
import model.vo.Usuario;
import controller.UsuarioController;

public class PainelCadastroUsuario extends JPanel {
	private JTextField tfNome;
	private JFormattedTextField tfCpf;
	private JFormattedTextField tfTelefone;
	private JLabel lblNome;
	private JLabel lblCpf;
	private JLabel lblTelefone;
	private JLabel lblPerfil;
	private JComboBox comboBox;
	private String[] tiposDePerfil = {"Recepcionista", "Gerente"};
	private JLabel lblNewLabel;
	private JButton btnSalvar;
	private JButton btnCancelar;

	private MaskFormatter mascaraCpf;
	private MaskFormatter mascaraTelefone;
	
	private Usuario usuario = new Usuario();
	private UsuarioController usuarioController = new UsuarioController();

	/**
	 * Create the panel.
	 */
	public PainelCadastroUsuario() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(100dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(40dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(40dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(100dlu;default)"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));

		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraCpf.setValueContainsLiteralCharacters(false);
			mascaraTelefone = new MaskFormatter("(##) #####-####");
			mascaraTelefone.setValueContainsLiteralCharacters(false);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		lblNewLabel = new JLabel("Cadastro de Usu\u00E1rio");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		add(lblNewLabel, "4, 4, 3, 1, center, default");
		
		lblNome = new JLabel("Nome");
		add(lblNome, "4, 8, 3, 1");
		
		tfNome = new JTextField();
		add(tfNome, "4, 10, 3, 1, fill, default");
		tfNome.setColumns(10);
		
		lblCpf = new JLabel("CPF");
		add(lblCpf, "4, 12, 3, 1");
		
		tfCpf = new JFormattedTextField(mascaraCpf);
		add(tfCpf, "4, 14, 3, 1, fill, default");
		tfCpf.setColumns(10);
		
		lblTelefone = new JLabel("Telefone");
		add(lblTelefone, "4, 16, 3, 1");
		
		tfTelefone = new JFormattedTextField(mascaraTelefone);
		add(tfTelefone, "4, 18, 3, 1, fill, default");
		tfTelefone.setColumns(10);
		
		lblPerfil = new JLabel("Perfil");
		add(lblPerfil, "4, 20, 3, 1");
		
		comboBox = new JComboBox(tiposDePerfil);
		add(comboBox, "4, 22, 3, 1, fill, default");
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setBackground(new Color(128, 255, 128));
		add(btnSalvar, "4, 28");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usuario.setNome(tfNome.getText());
				usuario.setPerfil((String) comboBox.getSelectedItem());
				try {
					String cpfSemMascara = (String) mascaraCpf.stringToValue(tfCpf.getText());
					usuario.setCpf(cpfSemMascara);

					String telefoneSemMascara = (String) mascaraTelefone.stringToValue(tfTelefone.getText());
					usuario.setTelefone(telefoneSemMascara);
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "Erro ao converter campos.", "Erro", JOptionPane.ERROR_MESSAGE);
				}
				
				try {
					usuarioController.inserir(usuario);
					JOptionPane.showMessageDialog(null, "Usuário criado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				} catch (CampoInvalidoException exceptionCampoInvalido) {
					JOptionPane.showMessageDialog(null, exceptionCampoInvalido.getMessage(), 
							"Erro", JOptionPane.ERROR_MESSAGE); 
				} catch (CpfDuplicadoException exceptionCpfDuplicado) {
					JOptionPane.showMessageDialog(null, exceptionCpfDuplicado.getMessage(), 
							"Erro", JOptionPane.ERROR_MESSAGE); 
				}
				
			}
		});
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(255, 0, 0));
		add(btnCancelar, "6, 28");

	}

	//Usado para tornar o btnCancelar acessível externamente 
	public JButton getBtnCancelar() {
		return btnCancelar;
	}
}
