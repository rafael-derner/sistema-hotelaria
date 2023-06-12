package view.paineis;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

import controller.ReservaController;
import model.exception.CampoInvalidoException;
import model.vo.Hospede;
import model.vo.Reserva;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;

public class PainelCadastroReserva extends JPanel {
	private DatePickerSettings pickerInicial;
	private DatePickerSettings pickerFinal;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private DatePicker dataInicio;
	private DatePicker dataFim;
	private JTable tabelaResultados;
	private JTextField tfNomeHospede;
	private JComboBox cbxNomeHospede;
	private String nomeHospede;
	private ReservaController reservaController;
	private ArrayList<String> listaNomesHospedes;
	private int primeiraData;
	private JRadioButton rdbtnBasico;
	private JRadioButton rdbtnIntermediario;
	private JRadioButton rdbtnLuxo;
	private JButton btnLimpar;
	private Reserva novaReserva;
	
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		
		JLabel lblReservaHospede = new JLabel("Buscar hospede:");
		add(lblReservaHospede, "4, 2, 3, 1");
		
		tfNomeHospede = new JTextField();
		add(tfNomeHospede, "4, 4, 3, 1, fill, default");
		tfNomeHospede.setColumns(10);
		
		reservaController = new ReservaController();
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nomeHospede = tfNomeHospede.getText();
				try {
					listaNomesHospedes = reservaController.buscarHospedePorNome(nomeHospede);
					
				} catch (CampoInvalidoException e1) {
					//JOptionPane.showMessageDialog(e1.getMessage(), null);
					e1.printStackTrace();
				}
			}
		});
		add(btnBuscar, "8, 4, left, default");
		
		cbxNomeHospede = new JComboBox(); //<Object>(listaNomesHospedes.toArray());
		add(cbxNomeHospede, "4, 6, 3, 1, fill, default");
		cbxNomeHospede.setSelectedItem(null);
		
		JLabel lblModeloQuarto = new JLabel("Selecione o modelo de quarto:");
		add(lblModeloQuarto, "4, 8, 3, 1");
		
		rdbtnBasico = new JRadioButton("Básico");
		add(rdbtnBasico, "4, 10");
		
		rdbtnIntermediario = new JRadioButton("Intermediário");
		add(rdbtnIntermediario, "6, 10");
		
		rdbtnLuxo = new JRadioButton("Luxo");
		add(rdbtnLuxo, "8, 10");
		
		ButtonGroup buttonGroup = new ButtonGroup();
	    buttonGroup.add(rdbtnBasico);
	    buttonGroup.add(rdbtnIntermediario);
	    buttonGroup.add(rdbtnLuxo);
		
		JLabel lblPeriodo = new JLabel("Insira um periodo para a reserva:");
		add(lblPeriodo, "4, 12, 3, 1");
		
		JLabel lblInicioPeridodo = new JLabel("Inicio:");
		add(lblInicioPeridodo, "4, 14, left, default");
		
		JLabel lblFimPeridodo = new JLabel("Fim:");
		add(lblFimPeridodo, "6, 14");
		
		primeiraData = 0;
		
		pickerInicial = new DatePickerSettings();
		dataInicio = new DatePicker(pickerInicial);
		dataInicio.addDateChangeListener(new DateChangeListener() {
			public void dateChanged(DateChangeEvent event) {
				if(primeiraData == 0) {
					LocalDate dataInicialSelecionada = dataInicio.getDate();
					pickerFinal.setDateRangeLimits(dataInicialSelecionada, null);
					dataFim.setDate(dataInicialSelecionada);
					primeiraData = 1;
				}
			}
		});
		add(dataInicio, "4, 16");
		
		
		pickerFinal = new DatePickerSettings();
		dataFim = new DatePicker(pickerFinal);
		dataFim.addDateChangeListener(new DateChangeListener() {
			public void dateChanged(DateChangeEvent event) {
				if(primeiraData == 0) {
					LocalDate dataFinalSelecionada = dataFim.getDate();
					pickerInicial.setDateRangeLimits(null, dataFinalSelecionada);
					dataInicio.setDate(dataFinalSelecionada);
					primeiraData = 1;
				}
			}
		});
		add(dataFim, "6, 16, fill, default");
		
		tabelaResultados = new JTable();
		add(tabelaResultados, "4, 18, 5, 1, fill, fill");
		
		
		btnCancelar = new JButton("Cancelar");
		add(btnCancelar, "4, 20, left, default");
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		add(btnLimpar, "6, 20, center, default");
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserirReserva();
			}
		});
		add(btnSalvar, "8, 20, right, default");
		
	}
	
	private void limparCampos() {
		tfNomeHospede.setText("");
		//cbxNomeHospede descobrir como limpa Jcheckbox
		//tabelaResultados descobrir como limpa Jtable
		primeiraData = 0;
		rdbtnBasico.setSelected(true);
	}

	private void inserirReserva() {
		novaReserva = new Reserva();
		//novaReserva.setQuarto(); criar uma forma de buscar o quarto selecionado a partir da 'tabelaResultados'
		//novaReserva.setHospede(); criar uma forma de pegar o Hospede a partir do nome selecionado na cbxNomeHospede
		//novaReserva.setUsuario(); criar uma forma de pegar o usuario que esta acessando o sistema
		//Criar um Enum para status da reserva;
		novaReserva.setDtCheckIn(dataInicio.getDate());
		novaReserva.setDtCheckOut(dataFim.getDate());
		reservaController.inserir(novaReserva);
	}
}
