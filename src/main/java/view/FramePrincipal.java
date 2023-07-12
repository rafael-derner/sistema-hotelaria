package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.UsuarioController;
import model.exception.CampoInvalidoException;
import model.exception.UsuarioInativoException;
import model.vo.Usuario;
import view.paineis.PainelCadastroHospede;
import view.paineis.PainelCadastroQuarto;
import view.paineis.PainelCadastroReserva;
import view.paineis.PainelCadastroUsuario;
import view.paineis.PainelListagemHospede;
import view.paineis.PainelListagemQuarto;
import view.paineis.PainelListagemReserva;
import view.paineis.PainelListagemUsuario;
import view.paineis.PainelLogin;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class FramePrincipal extends JFrame {

	private PainelCadastroUsuario painelCadastroUsuario;
	private PainelListagemUsuario painelListagemUsuario;
	private PainelCadastroHospede painelCadastroHospede;
	private PainelListagemHospede painelListagemHospede;
	private PainelCadastroQuarto painelCadastroQuarto;
	private PainelCadastroReserva cadastroReserva;
	private PainelListagemReserva listaReserva;
	private PainelListagemQuarto painelListagemQuarto;
	private PainelLogin painelLogin;

	private UsuarioController usuarioController = new UsuarioController();
	private Usuario usuarioAutenticado;
	private JPanel contentPaneVazio;

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

	/*
	 * Renderiza tela de login
	 */
	public FramePrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		setTitle("Sistema de Hotelaria");
		setLocationRelativeTo(null);

		painelLogin = new PainelLogin();
		setContentPane(painelLogin);

		painelLogin.getTfCodigoAcesso().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					painelLogin.getBtnAcessar().doClick();
				}
			}
		});

		painelLogin.getBtnAcessar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String codAcesso = painelLogin.getTfCodigoAcesso().getText();
					if (codAcesso.matches("[0]{1}")) {
						System.out.println("ACESSO COM USUÁRIO ADMIN - ALTERAR PARA PRODUÇÃO");
						codAcesso = "00000000000";
					}
					usuarioAutenticado = usuarioController.login(codAcesso);
					initialize();
				} catch (CampoInvalidoException campoInvalidoException) {
					JOptionPane.showMessageDialog(null, campoInvalidoException.getMessage());
				} catch (UsuarioInativoException usuarioInativoException) {
					JOptionPane.showMessageDialog(null, usuarioInativoException.getMessage());
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		setTitle("Sistema de Hotelaria");
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		contentPaneVazio = new JPanel();
		setContentPane(contentPaneVazio);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnQuarto = new JMenu("Quarto");
		menuBar.add(mnQuarto);

		JMenuItem mnItemCadastrarQuarto = new JMenuItem("Cadastrar");
		mnItemCadastrarQuarto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelCadastroQuarto = new PainelCadastroQuarto(null);
				painelCadastroQuarto.setVisible(true);
				registrarCliqueBtnSalvarDoPainelCadastroQuarto();
				registrarCliqueBtnCancelarDoPainelCadastroQuarto();
				setContentPane(painelCadastroQuarto);
				revalidate();
			}
		});
		mnQuarto.add(mnItemCadastrarQuarto);

		JMenuItem mnItemListarQuartos = new JMenuItem("Listar");
		mnItemListarQuartos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelListagemQuarto = new PainelListagemQuarto();
				painelListagemQuarto.setVisible(true);
				registrarCliqueBtnCancelarDoPainelCadastroQuarto();
				registrarCliqueBtnEditarDoPainelListagemQuarto();
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

				// Atualiza a tela principal
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
				registrarCliqueBtnAdicionarNovoHospedePainelListagemHospede();

				// Atualiza a tela principal
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
				cadastroReserva = new PainelCadastroReserva(null, usuarioAutenticado);
				cadastroReserva.setVisible(true);
				registraCliqueBtnSalvarDoPainelCadastroReserva();
				setContentPane(cadastroReserva);
				revalidate();
			}
		});
		mnReserva.add(mnItemCadastrarReserva);

		JMenuItem mnItemListarReservas = new JMenuItem("Listar");
		mnItemListarReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaReserva = new PainelListagemReserva();
				listaReserva.setVisible(true);

				registraCliqueBtnEditarPainelListagemReserva();

				setContentPane(listaReserva);
				revalidate();
			}
		});
		mnReserva.add(mnItemListarReservas);

		JMenu mnUsuario = new JMenu("Funcionário");
		menuBar.add(mnUsuario);

		JMenuItem mnItemCadastrarUsuario = new JMenuItem("Cadastrar");
		mnItemCadastrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelCadastroUsuario = new PainelCadastroUsuario(null);
				painelCadastroUsuario.setVisible(true);
				// registra action do botao cancelar de dentro do painel
				registrarCliqueBtnCancelarPainelCadastroUsuario();
				registraCliqueBtnSalvarDoPainelCadastroUsuario();

				// Atualiza a tela principal
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
				registrarCliqueBtnAdicionarNovoUsuarioPainelListagemUsuario();

				// Atualiza a tela principal
				setContentPane(painelListagemUsuario);
				revalidate();
			}
		});
		mnUsuario.add(mnItemListarUsuarios);
	}

	private void registraCliqueBtnEditarPainelListagemReserva() {
		listaReserva.getBtnEditar().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				cadastroReserva = new PainelCadastroReserva(listaReserva.getReservaSelecionada(), usuarioAutenticado);
				cadastroReserva.setVisible(true);
				registraCliqueBtnSalvarDoPainelCadastroReserva();
				setContentPane(cadastroReserva);
				revalidate();
			}
		});
	}

	/*
	 * Clique no botï¿½o de CANCELAR do PainelCadastroUsuario
	 */
	protected void registrarCliqueBtnCancelarPainelCadastroUsuario() {
		if (painelCadastroUsuario == null) {
			painelCadastroUsuario = new PainelCadastroUsuario(null);
		}

		painelCadastroUsuario.getBtnCancelar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelListagemUsuario = new PainelListagemUsuario();
				painelListagemUsuario.setVisible(true);
				registrarCliqueBtnEditarDoPainelListagemUsuario();
				registrarCliqueBtnAdicionarNovoUsuarioPainelListagemUsuario();
				setContentPane(painelListagemUsuario);
				revalidate();
			}
		});
	}

	/*
	 * Clique no botÃ£o de EDITAR do PainelListagemUsuario
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
	 * Clique no botÃ£o de ADICIONAR NOVO USUARIO do PainelListagemUsuario
	 */
	protected void registrarCliqueBtnAdicionarNovoUsuarioPainelListagemUsuario() {
		painelListagemUsuario.getBtnAdicionarNovoUsuario().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				painelCadastroUsuario = new PainelCadastroUsuario(null);
				painelCadastroUsuario.setVisible(true);
				registrarCliqueBtnCancelarPainelCadastroUsuario();
				registraCliqueBtnSalvarDoPainelCadastroUsuario();

				setContentPane(painelCadastroUsuario);
				revalidate();
			}
		});
	}

	/*
	 * Clique no botÃ£o de ADICIONAR NOVO USUARIO do PainelListagemHospede
	 */
	protected void registrarCliqueBtnAdicionarNovoHospedePainelListagemHospede() {
		painelListagemHospede.getBtnAdicionarNovoHospede().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				painelCadastroHospede = new PainelCadastroHospede(null);
				painelCadastroHospede.setVisible(true);
				registrarCliqueBtnCancelarPainelCadastroHospede();
				registraCliqueBtnSalvarDoPainelCadastroHospede();

				setContentPane(painelCadastroHospede);
				revalidate();
			}
		});
	}

	/*
	 * Clique no botÃ£o de SALVAR do PainelCadastroUsuario
	 */
	protected void registraCliqueBtnSalvarDoPainelCadastroUsuario() {
		painelCadastroUsuario.getBtnSalvar().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (painelCadastroUsuario.salvarUsuario()) {
					painelListagemUsuario = new PainelListagemUsuario();
					painelListagemUsuario.setVisible(true);
					registrarCliqueBtnEditarDoPainelListagemUsuario();

					// Atualiza a tela principal
					setContentPane(painelListagemUsuario);
					revalidate();
				}
			}
		});
	}

	protected void registraCliqueBtnSalvarDoPainelCadastroReserva() {
		cadastroReserva.getBtnSalvar().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					if (cadastroReserva.inserirReserva()) {
						listaReserva = new PainelListagemReserva();
						listaReserva.setVisible(true);
						registraCliqueBtnEditarPainelListagemReserva();

						setContentPane(listaReserva);
						revalidate();
					}
				} catch (CampoInvalidoException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	/*
	 * Clique no botï¿½o de CANCELAR do PainelCadastroHospede
	 */
	protected void registrarCliqueBtnCancelarPainelCadastroHospede() {
		if (painelCadastroHospede == null) {
			painelCadastroHospede = new PainelCadastroHospede(null);
		}

		painelCadastroHospede.getBtnCancelar().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				painelListagemHospede = new PainelListagemHospede();
				painelListagemHospede.setVisible(true);
				registrarCliqueBtnEditarDoPainelListagemHospede();
				registrarCliqueBtnAdicionarNovoHospedePainelListagemHospede();
				setContentPane(painelListagemHospede);
				revalidate();
			}
		});
	}

	/*
	 * Clique no botÃ£o de EDITAR do PainelListagemUsuario
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
	 * Clique no botÃ£o de SALVAR do PainelCadastroUsuario
	 */
	protected void registraCliqueBtnSalvarDoPainelCadastroHospede() {
		painelCadastroHospede.getBtnSalvar().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (painelCadastroHospede.salvarHospede()) {
					painelListagemHospede = new PainelListagemHospede();
					painelListagemHospede.setVisible(true);
					registrarCliqueBtnEditarDoPainelListagemHospede();

					// Atualiza a tela principal
					setContentPane(painelListagemHospede);
					revalidate();
				}
			}
		});
	}

	protected void registrarCliqueBtnSalvarDoPainelCadastroQuarto() {
		painelCadastroQuarto.getBtnSalvar().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (painelCadastroQuarto.salvarQuarto()) {
					painelListagemQuarto = new PainelListagemQuarto();
					painelListagemQuarto.setVisible(true);
					registrarCliqueBtnEditarDoPainelListagemQuarto();
					setContentPane(painelListagemQuarto);
					revalidate();
				}

			}
		});
	}

	protected void registrarCliqueBtnCancelarDoPainelCadastroQuarto() {
		// TODO Auto-generated method stub
		if (painelCadastroQuarto == null) {
			painelCadastroQuarto = new PainelCadastroQuarto(null);
		}

		painelCadastroQuarto.getBtnCancelar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				painelListagemQuarto = new PainelListagemQuarto();
				painelListagemQuarto.setVisible(true);
				registrarCliqueBtnEditarDoPainelListagemQuarto();
				setContentPane(painelListagemQuarto);
				revalidate();
			}
		});

	}

	protected void registrarCliqueBtnEditarDoPainelListagemQuarto() {
		painelListagemQuarto.getBtnEditar().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				painelCadastroQuarto = new PainelCadastroQuarto(painelListagemQuarto.getQuartoSelecionado());
				painelCadastroQuarto.setVisible(true);
				registrarCliqueBtnCancelarDoPainelCadastroQuarto();
				registrarCliqueBtnSalvarDoPainelCadastroQuarto();

				setContentPane(painelCadastroQuarto);
				revalidate();

			}
		});
	}
}
