package view.paineis;

import javax.swing.JPanel;
import javax.swing.JPasswordField;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class PainelLogin extends JPanel {
	private JPasswordField tfCodigoAcesso;
	private JLabel lblBemVindo;
	private JLabel lblCodigoAcesso;
	private JButton btnAcessar;

	/**
	 * Create the panel.
	 */
	public PainelLogin() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(40dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(15dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(15dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(5dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		lblBemVindo = new JLabel("Bem Vindo");
		lblBemVindo.setHorizontalAlignment(SwingConstants.CENTER);
		lblBemVindo.setFont(new Font("Tahoma", Font.BOLD, 25));
		add(lblBemVindo, "4, 4, center, default");
		
		lblCodigoAcesso = new JLabel("Código de Acesso");
		add(lblCodigoAcesso, "4, 8");
		
		tfCodigoAcesso = new JPasswordField();
		add(tfCodigoAcesso, "4, 10, fill, default");
		tfCodigoAcesso.setColumns(10);
		
		btnAcessar = new JButton("Acessar");
		add(btnAcessar, "4, 14");

	}

	public JTextField getTfCodigoAcesso() {
		return tfCodigoAcesso;
	}

	public JButton getBtnAcessar() {
		return btnAcessar;
	}

}
