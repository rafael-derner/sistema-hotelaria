package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.paineis.PainelCadastroHospede;
import view.paineis.PainelCadastroQuarto;
import view.paineis.PainelCadastroReserva;
import view.paineis.PainelCadastroUsuario;
import view.paineis.PainelListagemHospede;
import view.paineis.PainelListagemQuarto;
import view.paineis.PainelListagemUsuario;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class FramePrincipal extends JFrame {

	private PainelCadastroUsuario painelCadastroUsuario;
	private PainelListagemUsuario painelListagemUsuario;
	private PainelCadastroHospede painelCadastroHospede;
	private PainelListagemHospede painelListagemHospede;
	private PainelCadastroReserva cadastroReserva;
	private PainelListagemQuarto painelListagemQuarto;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FramePrincipal frame = new FramePrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FramePrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		setTitle("Sistema de Hotelaria");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnQuarto = new JMenu("Quarto");
		menuBar.add(mnQuarto);
		
		JMenuItem mnItemCadastrarQuarto = new JMenuItem("Cadastrar");
		mnItemCadastrarQuarto.addActionListener(new ActionListener() {
			private PainelCadastroQuarto painelCadastroQuarto;

			public void actionPerformed(ActionEvent e) {
				painelCadastroQuarto = new PainelCadastroQuarto();
				painelCadastroQuarto.setVisible(true);
				setContentPane(painelCadastroQuarto);
				revalidate();
			}
		});
		mnQuarto.add(mnItemCadastrarQuarto);
		
		JMenuItem mnItemListarQuartos = new JMenuItem("Listar");
		mnItemListarQuartos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelListagemQuarto = new PainelListagemQuarto(null);
				painelListagemQuarto.setVisible(true);
				setContentPane(painelListagemQuarto);
				revalidate();
			}
		});
		mnQuarto.add(mnItemListarQuartos);
		
		JMenu mnHospede = new JMenu("Hóspede");
		menuBar.add(mnHospede);
		
		JMenuItem mnItemCadastrarHospede = new JMenuItem("Cadastrar");
		mnItemCadastrarHospede.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelCadastroHospede = new PainelCadastroHospede(null);
				painelCadastroHospede.setVisible(true);
				// registra action do botao cancelar de dentro do painel
				registrarCliqueBtnCancelarPainelCadastroHospede();
				registraCliqueBtnSalvarDoPainelCadastroHospede();
				
				//Atualiza a tela principal
				setContentPane(painelCadastroHospede);
				revalidate();
			}
		});
		mnHospede.add(mnItemCadastrarHospede);
		
		JMenuItem mnItemListarHospedes = new JMenuItem("Listar");
		mnItemListarHospedes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelListagemHospede = new PainelListagemHospede();
				painelListagemHospede.setVisible(true);
				registrarCliqueBtnEditarDoPainelListagemHospede();
				
				//Atualiza a tela principal
				setContentPane(painelListagemHospede);
				revalidate();
			}
		});
		mnHospede.add(mnItemListarHospedes);
		
		JMenu mnReserva = new JMenu("Reserva");
		menuBar.add(mnReserva);
		
		JMenuItem mnItemCadastrarReserva = new JMenuItem("Cadastrar");
		mnItemCadastrarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastroReserva = new PainelCadastroReserva(null);
				cadastroReserva.setVisible(true);
				setContentPane(cadastroReserva);
				revalidate();
			}
		});
		mnReserva.add(mnItemCadastrarReserva);
		
		JMenuItem mnItemListarReservas = new JMenuItem("Listar");
		mnReserva.add(mnItemListarReservas);
		
		JMenu mnUsuario = new JMenu("Usuário");
		menuBar.add(mnUsuario);
		
		JMenuItem mnItemCadastrarUsuario = new JMenuItem("Cadastrar");		
		mnItemCadastrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelCadastroUsuario = new PainelCadastroUsuario(null);
				painelCadastroUsuario.setVisible(true);
				// registra action do botao cancelar de dentro do painel
				registrarCliqueBtnCancelarPainelCadastroUsuario();
				registraCliqueBtnSalvarDoPainelCadastroUsuario();
				
				//Atualiza a tela principal
				setContentPane(painelCadastroUsuario);
				revalidate();
			}
		});
		mnUsuario.add(mnItemCadastrarUsuario);
		
		JMenuItem mnItemListarUsuarios = new JMenuItem("Listar");
		mnItemListarUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelListagemUsuario = new PainelListagemUsuario();
				painelListagemUsuario.setVisible(true);
				registrarCliqueBtnEditarDoPainelListagemUsuario();
				
				//Atualiza a tela principal
				setContentPane(painelListagemUsuario);
				revalidate();
			}
		});
		mnUsuario.add(mnItemListarUsuarios);

	}
	
	/*
	 * Clique no bot�o de CANCELAR do PainelCadastroUsuario
	 */
	protected void registrarCliqueBtnCancelarPainelCadastroUsuario() {
		if(painelCadastroUsuario == null) {
			painelCadastroUsuario = new PainelCadastroUsuario(null);
		}
		
		painelCadastroUsuario.getBtnCancelar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelListagemUsuario = new PainelListagemUsuario();
				painelListagemUsuario.setVisible(true);
				registrarCliqueBtnEditarDoPainelListagemUsuario();
				setContentPane(painelListagemUsuario);
				revalidate();
			}
		});
	}

	/*
	 * Clique no botão de EDITAR do PainelListagemUsuario
	 */
	protected void registrarCliqueBtnEditarDoPainelListagemUsuario() {
		painelListagemUsuario.getBtnEditar().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				painelCadastroUsuario = new PainelCadastroUsuario(painelListagemUsuario.getUsuarioSelecionado());
				painelCadastroUsuario.setVisible(true);
				registrarCliqueBtnCancelarPainelCadastroUsuario();
				registraCliqueBtnSalvarDoPainelCadastroUsuario();
				
				setContentPane(painelCadastroUsuario);
				revalidate();
			}
		});
	}
	
	/*
	 * Clique no botão de SALVAR do PainelCadastroUsuario
	 */
	protected void registraCliqueBtnSalvarDoPainelCadastroUsuario() {
		painelCadastroUsuario.getBtnSalvar().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(painelCadastroUsuario.salvarUsuario()) {
					painelListagemUsuario = new PainelListagemUsuario();
					painelListagemUsuario.setVisible(true);
					registrarCliqueBtnEditarDoPainelListagemUsuario();
					
					//Atualiza a tela principal
					setContentPane(painelListagemUsuario);
					revalidate();
				}
			}
		});
	}
	
	/*
	 * Clique no bot�o de CANCELAR do PainelCadastroHospede
	 */
	protected void registrarCliqueBtnCancelarPainelCadastroHospede() {
		if(painelCadastroHospede == null) {
			painelCadastroHospede = new PainelCadastroHospede(null);
		}
		
		painelCadastroHospede.getBtnCancelar().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				painelListagemHospede = new PainelListagemHospede();
				painelListagemHospede.setVisible(true);
				registrarCliqueBtnEditarDoPainelListagemHospede();
				setContentPane(painelListagemHospede);
				revalidate();
			}
		});
	}

	/*
	 * Clique no botão de EDITAR do PainelListagemUsuario
	 */
	protected void registrarCliqueBtnEditarDoPainelListagemHospede() {
		painelListagemHospede.getBtnEditar().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				painelCadastroHospede = new PainelCadastroHospede(painelListagemHospede.getHospedeSelecionado());
				painelCadastroHospede.setVisible(true);
				registrarCliqueBtnCancelarPainelCadastroHospede();
				registraCliqueBtnSalvarDoPainelCadastroHospede();
				
				setContentPane(painelCadastroHospede);
				revalidate();
			}
		});
	}
	
	/*
	 * Clique no botão de SALVAR do PainelCadastroUsuario
	 */
	protected void registraCliqueBtnSalvarDoPainelCadastroHospede() {
		painelCadastroHospede.getBtnSalvar().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(painelCadastroHospede.salvarHospede()) {
					painelListagemHospede = new PainelListagemHospede();
					painelListagemHospede.setVisible(true);
					registrarCliqueBtnEditarDoPainelListagemHospede();
					
					//Atualiza a tela principal
					setContentPane(painelListagemHospede);
					revalidate();
				}
			}
		});
	}
}
