package view.paineis;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class PainelCadastroReserva extends JPanel {
	private JTextField tfNomeHospede;
	private DatePickerSettings dateSettings;
	private DatePicker dataPicker;
	private JTable table;
	private JButton btnSalvar;
	private JButton btnCancelar;

	public PainelCadastroReserva() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(70dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(70dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(70dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(17dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(15dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		
		JLabel lblReservaHospede = new JLabel("Reserva iniciada para o Hospede:");
		add(lblReservaHospede, "4, 2, 3, 1");
		
		tfNomeHospede = new JTextField();
		add(tfNomeHospede, "4, 4, 5, 1, fill, default");
		tfNomeHospede.setColumns(10);
		
		JLabel lblModeloQuarto = new JLabel("Selecione o modelo de quarto:");
		add(lblModeloQuarto, "4, 6, 3, 1");
		
		JRadioButton rdbtnBasico = new JRadioButton("Básico");
		add(rdbtnBasico, "4, 8");
		
		JRadioButton rdbtnIntermediario = new JRadioButton("Intermediário");
		add(rdbtnIntermediario, "6, 8");
		
		JRadioButton rdbtnLuxo = new JRadioButton("Luxo");
		add(rdbtnLuxo, "8, 8");
		
		 ButtonGroup buttonGroup = new ButtonGroup();
	     buttonGroup.add(rdbtnBasico);
	     buttonGroup.add(rdbtnIntermediario);
	     buttonGroup.add(rdbtnLuxo);
		
		JLabel lblPeriodo = new JLabel("Insira um periodo para a reserva:");
		add(lblPeriodo, "4, 10, 3, 1");
		
		dateSettings = new DatePickerSettings();
		dataPicker = new DatePicker(dateSettings);
		add(dataPicker, "4, 12, 3, 1");
		
		table = new JTable();
		add(table, "4, 14, 5, 1, fill, fill");
		
		btnCancelar = new JButton("Cancelar");
		add(btnCancelar, "4, 16, left, default");
		
		btnSalvar = new JButton("Salvar");
		add(btnSalvar, "8, 16, right, default");
		
		
		
	}
}
