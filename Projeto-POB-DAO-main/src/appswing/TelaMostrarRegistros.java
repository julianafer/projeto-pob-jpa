package appswing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Registro;
import modelo.Veiculo;
import regras_negocio.Fachada;
import javax.swing.JRadioButton;

public class TelaMostrarRegistros {
	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel label;
	private JLabel label_6;
	private Veiculo veiculo;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaRegistros tela = new TelaRegistros();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 * @wbp.parser.entryPoint
	 */
	public TelaMostrarRegistros(String placa) throws Exception {
		this.veiculo = Fachada.localizarVeiculo(placa);
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setModal(true);
		frame.setResizable(false);
		frame.setTitle("Registros");
		frame.setBounds(100, 100, 729, 284);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				Fachada.inicializar();
				listagem();
			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 43, 674, 148);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				label_6.setText("selecionado="+ (int) table.getValueAt( table.getSelectedRow(), 0));
			}
		});
		table.setGridColor(Color.BLACK);
		table.setRequestFocusEnabled(false);
		table.setFocusable(false);
		table.setBackground(new Color(232, 244, 244));
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		label = new JLabel("");		//label de mensagem
		label.setForeground(Color.BLUE);
		label.setBounds(12, 355, 688, 14);
		frame.getContentPane().add(label);

		label_6 = new JLabel("resultados:");
		label_6.setBounds(31, 202, 431, 14);
		frame.getContentPane().add(label_6);

		JTextPane textPane = new JTextPane();
		textPane.setBounds(47, 308, 1, 16);
		frame.getContentPane().add(textPane);
		
		lblNewLabel = new JLabel("Registros do veiculo " + veiculo.getPlaca());
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBounds(21, 18, 423, 14);
		frame.getContentPane().add(lblNewLabel);
		
		ButtonGroup group = new ButtonGroup();
	}

	public void listagem() {
		try{
			//ler os carros do banco
			List<Registro> lista = veiculo.getRegistros();

			// o model armazena todas as linhas e colunas do table
			DefaultTableModel model = new DefaultTableModel();

			//adicionar colunas no model
			model.addColumn("id");
			model.addColumn("placa");
			model.addColumn("data");
			model.addColumn("operação");

			//adicionar linhas no model
			for(Registro reg : lista) {
				model.addRow(new Object[]{reg.getId(), reg.getVeiculo().getPlaca(), reg.getDatahora(), reg.getOperacao()});
			}


			//atualizar model no table (visualizacao)
			table.setModel(model);

			label_6.setText("resultados: "+lista.size()+ " objetos");
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}
	}
}
