package view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class PainelCadastroUsuario extends JPanel {
	private JTextField tfNome;
	private JTextField tfCpf;
	private JTextField tfTelefone;

	/**
	 * Create the panel.
	 */
	public PainelCadastroUsuario() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(100dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
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
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNome = new JLabel("Nome");
		add(lblNome, "4, 6");
		
		tfNome = new JTextField();
		add(tfNome, "4, 8, fill, default");
		tfNome.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF");
		add(lblCpf, "4, 10");
		
		tfCpf = new JTextField();
		add(tfCpf, "4, 12, fill, default");
		tfCpf.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone");
		add(lblTelefone, "4, 14");
		
		tfTelefone = new JTextField();
		add(tfTelefone, "4, 16, fill, default");
		tfTelefone.setColumns(10);
		
		JLabel lblPerfil = new JLabel("Perfil");
		add(lblPerfil, "4, 18");
		
		JComboBox comboBox = new JComboBox();
		add(comboBox, "4, 20, fill, default");

	}

}
