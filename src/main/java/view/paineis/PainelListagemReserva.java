package view.paineis;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

public class PainelListagemReserva extends JPanel {
	private JTextField textField;
	private JTextField tfQuarto;
	private DatePickerSettings dateSettings;
	private DatePicker dataInicio;
	private DatePicker dataFim;
	private JButton btnConsultar;
	private JButton btnLimpar;
	private JTable tabelaResultado;
	private JButton btnEditar;

	public PainelListagemReserva() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
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
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNome = new JLabel("Nome do hospede:");
		add(lblNome, "4, 4, 5, 1");
		
		textField = new JTextField();
		add(textField, "4, 6, 5, 1, fill, default");
		textField.setColumns(10);
		
		JLabel lblQuarto = new JLabel("Numero do quarto");
		add(lblQuarto, "4, 8, 5, 1");
		
		tfQuarto = new JTextField();
		tfQuarto.setColumns(10);
		add(tfQuarto, "4, 10, fill, default");
		
		dateSettings = new DatePickerSettings();
		
		JLabel lblInicioPeridodo = new JLabel("Inicio:");
		add(lblInicioPeridodo, "4, 12, 2, 1, left, default");
		
		JLabel lblFimPeridodo = new JLabel("Fim:");
		add(lblFimPeridodo, "6, 12, 3, 1");
		
		dataInicio = new DatePicker(dateSettings);
		add(dataInicio, "4, 14");
		
		dataFim = new DatePicker(dateSettings);
		add(dataFim, "6, 14, fill, default");
		
		tabelaResultado = new JTable();
		add(tabelaResultado, "4, 16, 5, 1, fill, fill");
		
		btnLimpar = new JButton("Limpar");
		add(btnLimpar, "4, 18, left, default");
		
		btnEditar = new JButton("Editar");
		add(btnEditar, "6, 18, center, default");
		
		btnConsultar = new JButton("Consultar");
		add(btnConsultar, "8, 18, right, default");

	}

}
