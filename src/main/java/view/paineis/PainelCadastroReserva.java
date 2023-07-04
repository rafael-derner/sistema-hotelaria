package view.paineis;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import controller.HospedeController;
import controller.ReservaController;
import model.exception.CampoInvalidoException;
import model.vo.Hospede;
import model.vo.Quarto;
import model.vo.Reserva;
import model.vo.Usuario;
import model.seletor.HospedeSeletor;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import java.awt.Font;

public class PainelCadastroReserva extends JPanel {
	private DatePickerSettings pickerInicial;
	private DatePickerSettings pickerFinal;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private DatePicker dataInicio;
	private String[] nomesColunas = { "Numero", "Categoria", "Valor" };
	private DatePicker dataFim;
	private JTable tabelaQuartos;
	private JTextField tfNomeHospede;
	private JComboBox<Hospede> cbxNomeHospede;
	private String nomeHospede;
	private HospedeController hospedeController;
	private List<Hospede> listaHospedes;
	private ArrayList<Quarto> listaQuartos;
	private Quarto quartoSelecionado;
	private int primeiraData;
	private JRadioButton rdbtnBasico;
	private JRadioButton rdbtnIntermediario;
	private JRadioButton rdbtnLuxo;
	private JButton btnLimpar;
	private Reserva novaReserva;
	private JLabel lblTitulo;
	private Usuario usuarioVO;
	private JButton btnBuscarQuartos;
	private ReservaController reservaController = new ReservaController();
	private Reserva reservaVO;;
	
	public PainelCadastroReserva(Reserva reserva) {
		if(reserva != null) {
			reservaVO = new Reserva();
			reservaVO = reserva;
			prencherCampos();
		} else {
			reservaVO = new Reserva();
		}
		
		
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
		
		lblTitulo = new JLabel("Cadastro de Reservas");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 25));
		add(lblTitulo, "2, 2, 7, 1, center, default");
		
		JLabel lblReservaHospede = new JLabel("Buscar hospede:");
		add(lblReservaHospede, "4, 4, 3, 1");
		
		tfNomeHospede = new JTextField();
		add(tfNomeHospede, "4, 6, 3, 1, fill, default");
		tfNomeHospede.setColumns(10);
		
		cbxNomeHospede = new JComboBox();
		add(cbxNomeHospede, "4, 8, 3, 1, fill, default");
		
		JButton btnBuscarHospede = new JButton("Buscar");
		btnBuscarHospede.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hospedeController = new HospedeController();
				HospedeSeletor seletor = new HospedeSeletor();
				seletor.setNome(tfNomeHospede.getText());
				listaHospedes = hospedeController.consultarComFiltro(seletor);
				for(Hospede l : listaHospedes) {
					cbxNomeHospede.addItem(l);
				}
				cbxNomeHospede.setSelectedItem(null);
			}
		});
		add(btnBuscarHospede, "8, 6, left, default");
		
		JLabel lblModeloQuarto = new JLabel("Selecione o modelo de quarto:");
		add(lblModeloQuarto, "4, 10, 3, 1");
		
		rdbtnBasico = new JRadioButton("Básico");
		add(rdbtnBasico, "4, 12");
		
		rdbtnIntermediario = new JRadioButton("Intermediário");
		add(rdbtnIntermediario, "6, 12");
		
		rdbtnLuxo = new JRadioButton("Luxo");
		add(rdbtnLuxo, "8, 12");
		
		final ButtonGroup buttonGroup = new ButtonGroup();
	    buttonGroup.add(rdbtnBasico);
	    buttonGroup.add(rdbtnIntermediario);
	    buttonGroup.add(rdbtnLuxo);
		
		JLabel lblPeriodo = new JLabel("Insira um periodo para a reserva:");
		add(lblPeriodo, "4, 14, 3, 1");
		
		JLabel lblInicioPeridodo = new JLabel("Inicio:");
		add(lblInicioPeridodo, "4, 16, left, default");
		
		JLabel lblFimPeridodo = new JLabel("Fim:");
		add(lblFimPeridodo, "6, 16");
		
		primeiraData = 0;
		
		pickerInicial = new DatePickerSettings();
		dataInicio = new DatePicker(pickerInicial);
		dataInicio.addDateChangeListener(new DateChangeListener() {
			public void dateChanged(DateChangeEvent event) {
				LocalDate dataInicialSelecionada = dataInicio.getDate();
				pickerFinal.setDateRangeLimits(dataInicialSelecionada, null);
				if(primeiraData == 0) {
					dataFim.setDate(dataInicialSelecionada);
					primeiraData = 1;
				}
			}
		});
		add(dataInicio, "4, 18");
		
		pickerFinal = new DatePickerSettings();
		dataFim = new DatePicker(pickerFinal);
		dataFim.addDateChangeListener(new DateChangeListener() {
			public void dateChanged(DateChangeEvent event) {
				LocalDate dataFinalSelecionada = dataFim.getDate();
				pickerInicial.setDateRangeLimits(null, dataFinalSelecionada);
				if(primeiraData == 0) {
					dataInicio.setDate(dataFinalSelecionada);
					primeiraData = 1;
				}
			}
		});
		add(dataFim, "6, 18, fill, default");
		
		btnBuscarQuartos = new JButton("Buscar");
		btnBuscarQuartos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dataInicio.getDate().lengthOfYear() > 0) {
					try {
						listaQuartos = new ArrayList<Quarto>();
						String rdbSelecionado = consultaRadioBurronSelecionado(rdbtnBasico, rdbtnIntermediario, rdbtnLuxo);
						listaQuartos = reservaController.consultarQuartos(dataInicio.getDate(), dataFim.getDate(), rdbSelecionado);
					} catch (CampoInvalidoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					atualizarTabelaUsuarios();
				}
			}
		});
		add(btnBuscarQuartos, "8, 18, left, default");
		
		tabelaQuartos = new JTable();
		add(tabelaQuartos, "4, 20, 5, 1, fill, fill");
		this.limparTabelaQuartos();
		
		tabelaQuartos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int elementoSelecionado = tabelaQuartos.getSelectedRow();
				if(elementoSelecionado > 0) {
					btnSalvar.setEnabled(true);
					quartoSelecionado = listaQuartos.get(elementoSelecionado - 1);
				}
			}
		});
		
		
		btnCancelar = new JButton("Cancelar");
		add(btnCancelar, "4, 22, left, default");
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		add(btnLimpar, "6, 22, center, default");
		

		btnSalvar = new JButton("Salvar");
		btnSalvar.setEnabled(false);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					inserirReserva();
					limparCampos();
				} catch (CampoInvalidoException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());;
				}
			}
		});
		add(btnSalvar, "8, 22, right, default");
		
	}
	
	private void prencherCampos() {
		tfNomeHospede.setText(reservaVO.getHospede().getNome());
		//cbxNomeHospede.add(reservaVO.getHospede().getNome());
		this.limparTabelaQuartos();
		//this.setDataInicio(reservaVO.getDtCheckIn());
		//this.setDataFim(reservaVO.getDtCheckOut());
		rdbtnBasico.setSelected(true);
	}

	public DatePicker getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(DatePicker dataInicio) {
		this.dataInicio = dataInicio;
	}

	public DatePicker getDataFim() {
		return dataFim;
	}

	public void setDataFim(DatePicker dataFim) {
		this.dataFim = dataFim;
	}

	private void editarReserva() {
				
	}

	private void atualizarTabelaUsuarios() {
		this.limparTabelaQuartos();

		DefaultTableModel model = (DefaultTableModel) tabelaQuartos.getModel();

		for (Quarto quarto : listaQuartos) {
			Object[] novaLinhaDaTabela = new Object[4];
			novaLinhaDaTabela[0] = quarto.getNumeroQuarto();
			novaLinhaDaTabela[1] = quarto.getTipoQuarto();
			novaLinhaDaTabela[2] = quarto.getValorQuarto();

			model.addRow(novaLinhaDaTabela);
		}
	}
	
	private void limparTabelaQuartos() {
		tabelaQuartos.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
	}
	
	private void limparCampos() {
		tfNomeHospede.setText("");
		cbxNomeHospede.removeAllItems();
		this.limparTabelaQuartos();
		primeiraData = 0;
		rdbtnBasico.setSelected(true);
	}

	private void inserirReserva() throws CampoInvalidoException {
		novaReserva = new Reserva();
		novaReserva.setQuarto(listaQuartos.get(tabelaQuartos.getSelectedRow() - 1));
		novaReserva.setHospede((Hospede) cbxNomeHospede.getSelectedItem());
		novaReserva.setUsuario(usuarioVO);
		novaReserva.setDtCheckIn(dataInicio.getDate());
		novaReserva.setDtCheckOut(dataFim.getDate());
		try {
				reservaController.inserir(novaReserva);
		}catch(Exception e2){
			JOptionPane.showMessageDialog(null, e2.getMessage());
		}
	}
	
	private String consultaRadioBurronSelecionado(JRadioButton rdbtnBasico, JRadioButton rdbtnIntermediario, JRadioButton rdbtnLuxo) {
		String botao = "";
		if(rdbtnBasico.isSelected()) {
			botao += "BÁSICO";
		}
		if(rdbtnIntermediario.isSelected()) {
			botao += "INTERMEDIÁRIO";
		}
		if(rdbtnLuxo.isSelected()) {
			botao += "LUXO";
		}
		return botao;
	}
}
