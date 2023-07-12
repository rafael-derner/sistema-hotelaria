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
import model.exception.CpfAlteradoException;
import model.exception.CpfDuplicadoException;
import model.vo.Hospede;
import controller.HospedeController;

public class PainelCadastroHospede extends JPanel {
	private JTextField tfNome;
	private JFormattedTextField tfCpf;
	private JFormattedTextField tfTelefone;
	private JLabel lblNome;
	private JLabel lblCpf;
	private JLabel lblTelefone;
	private JLabel lblCadastroHospede;
	private JButton btnSalvar;
	private JButton btnCancelar;

	private MaskFormatter mascaraCpf;
	private MaskFormatter mascaraTelefone;

	private Hospede hospedeVO;
	private HospedeController hospedeController = new HospedeController();

	/**
	 * Create the panel.
	 */
	public PainelCadastroHospede(Hospede hospede) {
		if (hospede != null) {
			hospedeVO = hospede;
		} else {
			hospedeVO = new Hospede();
		}

		setLayout(new FormLayout(
				new ColumnSpec[] { 
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(100dlu;default)"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(40dlu;default):grow"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(40dlu;default):grow"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(100dlu;default)"), },
				new RowSpec[] { 
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraCpf.setValueContainsLiteralCharacters(false);
			mascaraTelefone = new MaskFormatter("(##) #####-####");
			mascaraTelefone.setValueContainsLiteralCharacters(false);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		lblCadastroHospede = new JLabel("Cadastro de Hóspede");
		lblCadastroHospede.setFont(new Font("Tahoma", Font.BOLD, 25));
		add(lblCadastroHospede, "4, 4, 3, 1, center, default");

		lblNome = new JLabel("Nome do Hóspede:");
		add(lblNome, "4, 8, 3, 1");

		tfNome = new JTextField();
		add(tfNome, "4, 10, 3, 1, fill, default");
		tfNome.setColumns(10);

		lblCpf = new JLabel("CPF:");
		add(lblCpf, "4, 12, 3, 1");

		tfCpf = new JFormattedTextField(mascaraCpf);
		add(tfCpf, "4, 14, 3, 1, fill, default");
		tfCpf.setColumns(10);

		lblTelefone = new JLabel("Telefone:");
		add(lblTelefone, "4, 16, 3, 1");

		tfTelefone = new JFormattedTextField(mascaraTelefone);
		add(tfTelefone, "4, 18, 3, 1, fill, default");
		tfTelefone.setColumns(10);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setBackground(new Color(128, 255, 128));
		add(btnSalvar, "4, 28");

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(255, 0, 0));
		add(btnCancelar, "6, 28");

		if (this.hospedeVO.getIdHospede() != null) {
			preencherCamposDoFormulario();
		}

	}

	private void preencherCamposDoFormulario() {
		this.tfNome.setText(this.hospedeVO.getNome());
		this.tfCpf.setText(this.hospedeVO.getCpf());
		this.tfTelefone.setText(this.hospedeVO.getTelefone());
	}

	// Usado para tornar o btnCancelar acess�vel externamente
	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	// Usado para tornar o btnCancelar acess�vel externamente
	public JButton getBtnSalvar() {
		return btnSalvar;
	}

	public boolean salvarHospede() {
		boolean retorno = false;
		hospedeVO.setNome(tfNome.getText());
		try {
			String cpfSemMascara = (String) mascaraCpf.stringToValue(tfCpf.getText());
			hospedeVO.setCpf(cpfSemMascara);

			String telefoneSemMascara = (String) mascaraTelefone.stringToValue(tfTelefone.getText());
			hospedeVO.setTelefone(telefoneSemMascara);
		} catch (ParseException e1) {
			JOptionPane.showMessageDialog(null, "Erro ao converter campos.", "Erro", JOptionPane.ERROR_MESSAGE);
		}

		try {
			if (hospedeVO.getIdHospede() != null) {
				if (hospedeController.atualizar(hospedeVO)) {
					JOptionPane.showMessageDialog(null, "Hóspede atualizado com sucesso!", "Sucesso",
							JOptionPane.INFORMATION_MESSAGE);
					retorno = true;
				} else {
					JOptionPane.showMessageDialog(null,
							"Ocorreu um erro ao atualizar hóspede. Verifique os dados e tente novamente", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				hospedeController.inserir(hospedeVO);
				JOptionPane.showMessageDialog(null, "Hóspede criado com sucesso!", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
				retorno = true;
			}
		} catch (CampoInvalidoException exceptionCampoInvalido) {
			JOptionPane.showMessageDialog(null, exceptionCampoInvalido.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} catch (CpfDuplicadoException exceptionCpfDuplicado) {
			JOptionPane.showMessageDialog(null, exceptionCpfDuplicado.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} catch (CpfAlteradoException exceptionCpfAlterado) {
			JOptionPane.showMessageDialog(null, exceptionCpfAlterado.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
		return retorno;
	}
}
