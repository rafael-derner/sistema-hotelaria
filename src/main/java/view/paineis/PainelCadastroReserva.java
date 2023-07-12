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
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Color;

public class PainelCadastroReserva extends JPanel {
	private DatePickerSettings pickerInicial;
	private DatePickerSettings pickerFinal;
	private JButton btnSalvar;
	private JButton btnVoltar;
	private DatePicker dataInicio;
	private String[] nomesColunas = { "Número", "Categoria", "Valor" };
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
	private JLabel lblTitulo;
	private Usuario usuarioVO;
	private JButton btnBuscarQuartos;
	private ReservaController reservaController = new ReservaController();
	private Reserva reservaVO;
	private JSeparator separator;
	private JSeparator separator_1;

	private JComboBox cbxCategoriaQuarto;
	private String[] categoriasDeQuarto = { "", "Básico", "Intermediário", "Luxo" };
	private JButton btnCancelar;

	public PainelCadastroReserva(Reserva reserva, Usuario usuarioAutenticado) {
		if (reserva != null) {
			reservaVO = reserva;
			listaQuartos = new ArrayList<Quarto>();
			listaQuartos.add(reservaVO.getQuarto());
			listaHospedes = new ArrayList<Hospede>();
			listaHospedes.add(reservaVO.getHospede());
		} else {
			reservaVO = new Reserva();
		}
		usuarioVO = usuarioAutenticado;

		setLayout(new FormLayout(
				new ColumnSpec[] { 
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(100dlu;default)"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("150px:grow"), 
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("150px:grow"), 
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("150px:grow"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("150px:grow"), 
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("150px:grow"), 
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(100dlu;default)"), },
				new RowSpec[] { 
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, 
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, 
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, 
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, 
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(17dlu;default)"),
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, 
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, 
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(17dlu;default)"),
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, 
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, 
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"),
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, 
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, 
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, 
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, 
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, 
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		lblTitulo = new JLabel("Cadastro de Reservas");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 25));
		add(lblTitulo, "4, 4, 9, 1, center, default");
		
		tfNomeHospede = new JTextField();
		add(tfNomeHospede, "4, 6, 3, 1, fill, default");
		tfNomeHospede.setColumns(10);
		
		cbxNomeHospede = new JComboBox();
		add(cbxNomeHospede, "4, 8, 3, 1, fill, default");

		JLabel lblReservaHospede = new JLabel("Nome do Hóspede:");
		add(lblReservaHospede, "4, 8");

		JButton btnBuscarHospede = new JButton("Buscar Hóspede");

		btnBuscarHospede.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbxNomeHospede.removeAllItems();
				hospedeController = new HospedeController();
				HospedeSeletor seletor = new HospedeSeletor();
				seletor.setNome(tfNomeHospede.getText());
				listaHospedes = hospedeController.consultarComFiltro(seletor);
				for (Hospede l : listaHospedes) {
					cbxNomeHospede.addItem(l);
				}
				cbxNomeHospede.setSelectedItem(null);
			}
		});
		add(btnBuscarHospede, "12, 10, fill, default");

		separator = new JSeparator();
		add(separator, "4, 14, 9, 1");

		tfNomeHospede = new JTextField();
		add(tfNomeHospede, "4, 10, 7, 1, fill, default");
		tfNomeHospede.setColumns(10);

		cbxNomeHospede = new JComboBox();
		add(cbxNomeHospede, "4, 12, 9, 1, fill, default");

		primeiraData = 0;

		pickerInicial = new DatePickerSettings();
		dataInicio = new DatePicker(pickerInicial);
		dataInicio.addDateChangeListener(new DateChangeListener() {
			public void dateChanged(DateChangeEvent event) {
				LocalDate dataInicialSelecionada = dataInicio.getDate();
				pickerFinal.setDateRangeLimits(dataInicialSelecionada, null);
				if (primeiraData == 0) {
					dataFim.setDate(dataInicialSelecionada);
					primeiraData = 1;
				}
			}
		});

		JLabel lblModeloQuarto = new JLabel("Categoria de Quarto pretendida:");
		add(lblModeloQuarto, "4, 16");

		/*
		 * btngroup rdbtnBasico = new JRadioButton("Básico"); add(rdbtnBasico,
		 * "4, 18, center, default");
		 * 
		 * rdbtnIntermediario = new JRadioButton("Intermediário");
		 * add(rdbtnIntermediario, "6, 18, center, default");
		 * 
		 * rdbtnLuxo = new JRadioButton("Luxo"); add(rdbtnLuxo,
		 * "8, 18, center, default");
		 * 
		 * final ButtonGroup buttonGroup = new ButtonGroup();
		 * buttonGroup.add(rdbtnIntermediario); buttonGroup.add(rdbtnBasico);
		 * buttonGroup.add(rdbtnLuxo);
		 */

		cbxCategoriaQuarto = new JComboBox(categoriasDeQuarto);
		add(cbxCategoriaQuarto, "4, 18, 9, 1, fill, default");

		separator_1 = new JSeparator();
		add(separator_1, "4, 20, 9, 1");

		JLabel lblInicioPeridodo = new JLabel("Data pretendida para inicio de reserva:");
		add(lblInicioPeridodo, "4, 22, left, default");

		JLabel lblFimPeridodo = new JLabel("Data pretendida para fim de reserva:");
		add(lblFimPeridodo, "8, 22, 3, 1");
		add(dataInicio, "4, 24, 3, 1");

		pickerFinal = new DatePickerSettings();
		dataFim = new DatePicker(pickerFinal);
		dataFim.addDateChangeListener(new DateChangeListener() {
			public void dateChanged(DateChangeEvent event) {
				LocalDate dataFinalSelecionada = dataFim.getDate();
				pickerInicial.setDateRangeLimits(null, dataFinalSelecionada);
				if (primeiraData == 0) {
					dataInicio.setDate(dataFinalSelecionada);
					primeiraData = 1;
				}
			}
		});
		add(dataFim, "8, 24, 3, 1, fill, default");

		btnBuscarQuartos = new JButton("Buscar Quartos disponíveis");
		btnBuscarQuartos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dataInicio.getDate().lengthOfYear() > 0) {
					try {
						listaQuartos = new ArrayList<Quarto>();
//						String rdbSelecionado = consultaRadioBurronSelecionado(rdbtnBasico, rdbtnIntermediario, rdbtnLuxo);
						String rdbSelecionado = (String) cbxCategoriaQuarto.getSelectedItem();
						listaQuartos = reservaController.consultarQuartos(dataInicio.getDate(), dataFim.getDate(),
								rdbSelecionado);
					} catch (CampoInvalidoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					atualizarTabelaUsuarios();
				}
			}
		});
		add(btnBuscarQuartos, "12, 24, fill, default");

		tabelaQuartos = new JTable();
		add(tabelaQuartos, "4, 26, 9, 1, fill, fill");
		this.limparTabelaQuartos();

		tabelaQuartos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int elementoSelecionado = tabelaQuartos.getSelectedRow();
				if (elementoSelecionado > 0) {
					btnSalvar.setEnabled(true);
					quartoSelecionado = listaQuartos.get(elementoSelecionado - 1);
				}
			}
		});
	
		btnVoltar = new JButton("Voltar");
		add(btnVoltar, "4, 22, left, default");
		

		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		add(btnLimpar, "4, 28, fill, default");

		btnSalvar = new JButton("Salvar");
		btnSalvar.setBackground(new Color(128, 255, 128));
		btnSalvar.setEnabled(false);
		add(btnSalvar, "10, 28, fill, default");

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(255, 0, 0));
		add(btnCancelar, "12, 28, fill, default");

		if (reservaVO.getIdReserva() != null) {
			quartoSelecionado = reservaVO.getQuarto();
			btnSalvar.setEnabled(true);
			cbxNomeHospede.addItem(listaHospedes.get(0));
			prencherCampos();
			atualizarTabelaUsuarios();
		}
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

	public JButton getBtnSalvar() {
		return btnSalvar;
	}

	public void setBtnSalvar(JButton btnSalvar) {
		this.btnSalvar = btnSalvar;
	}

	private void prencherCampos() {
		tfNomeHospede.setText(reservaVO.getHospede().getNome());
		cbxNomeHospede.setSelectedItem(0);
		this.limparTabelaQuartos();
		this.dataInicio.setDate(reservaVO.getDtCheckIn());
		this.dataFim.setDate(reservaVO.getDtCheckOut());
		switch (reservaVO.getQuarto().getTipoQuarto()) {
		case "BÁSICO":
			rdbtnBasico.setSelected(true);
			break;
		case "INTERMEDIÁRIO":
			rdbtnIntermediario.setSelected(true);
			break;
		case "LUXO":
			rdbtnLuxo.setSelected(true);
			break;
		}
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

	public boolean inserirReserva() throws CampoInvalidoException {
		boolean retorno = false;
		reservaVO.setQuarto(quartoSelecionado);
		reservaVO.setHospede((Hospede) cbxNomeHospede.getSelectedItem());
		reservaVO.setUsuario(usuarioVO);
		reservaVO.setDtCheckIn(dataInicio.getDate());
		reservaVO.setDtCheckOut(dataFim.getDate());
		try {
			if (reservaVO.getIdReserva() != null) {
				if (reservaController.atualizar(reservaVO)) {
					JOptionPane.showMessageDialog(null, "Reserva atualizada com sucesso!", "Sucesso",
							JOptionPane.INFORMATION_MESSAGE);
					retorno = true;
				} else {
					JOptionPane.showMessageDialog(null,
							"Ocorreu um erro ao atualizar a reserva. Verifique os dados e tente novamente", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				reservaController.inserir(reservaVO);
				JOptionPane.showMessageDialog(null, "Reserva criada com sucesso!", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
				retorno = true;
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage());
		}
		return retorno;
	}

	private String consultaRadioBurronSelecionado(JRadioButton rdbtnBasico, JRadioButton rdbtnIntermediario,
			JRadioButton rdbtnLuxo) {
		String botao = "";
		if (rdbtnBasico.isSelected()) {
			botao += "BÁSICO";
		}
		if (rdbtnIntermediario.isSelected()) {
			botao += "INTERMEDIÁRIO";
		}
		if (rdbtnLuxo.isSelected()) {
			botao += "LUXO";
		}
		return botao;
	}
}
