/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */
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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.db4o.ObjectContainer;

import modelo.Veiculo;
import modelo.Registro;
import regras_negocio.Fachada;

public class TelaConsulta {
	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton button;
	private JLabel label;
	private JLabel label_4;

	private ObjectContainer manager;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConsulta tela = new TelaConsulta();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaConsulta() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setModal(true);

		frame.setResizable(false);
		frame.setTitle("Consulta");
		frame.setBounds(100, 100, 729, 385);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				Fachada.inicializar();
			}
			@Override
			public void windowClosing(WindowEvent e) {
				Fachada.finalizar();
			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 43, 674, 148);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				label_4.setText("selecionado="+ (String) table.getValueAt( table.getSelectedRow(), 0));
			}
		});
		table.setGridColor(Color.BLACK);
		table.setRequestFocusEnabled(false);
		table.setFocusable(false);
		table.setBackground(Color.LIGHT_GRAY);
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
		label.setBounds(21, 321, 688, 14);
		frame.getContentPane().add(label);

		label_4 = new JLabel("resultados:");
		label_4.setBounds(21, 190, 431, 14);
		frame.getContentPane().add(label_4);

		button = new JButton("Consultar");
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			/**
			 *
			 */
			public void actionPerformed(ActionEvent e) {
				int index = comboBox.getSelectedIndex();
				if(index<0)
					label_4.setText("consulta nao selecionada");
				else
					switch(index) {
					case 0: 
						String data = JOptionPane.showInputDialog("digite a data (dd/mm/aaaa)");
						List<Registro> resultado = Fachada.registrosEmData(data);
						listagemRegistro(resultado);
						break;
					case 1: 
						String data2 = JOptionPane.showInputDialog("digite a data (dd/mm/aaaa)");
						List<Veiculo> resultado2 = Fachada.veiculosEmData(data2);
						listagemVeiculo(resultado2);
						break;
					case 2: 
						int n = Integer.parseInt(JOptionPane.showInputDialog("digite um número"));
						List<Veiculo> resultado3 = Fachada.veiculosNRegistros(n);
						listagemVeiculo(resultado3);
						break;

					}

			}
		});
		button.setBounds(606, 10, 89, 23);
		frame.getContentPane().add(button);

		comboBox = new JComboBox();
		comboBox.setToolTipText("selecione a consulta");
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"quais os registros da data X", "quais os veiculos contendo registro na data X", "quais os veiculos contendo com mais de N registros"}));
		comboBox.setBounds(21, 10, 513, 22);
		frame.getContentPane().add(comboBox);
	}

	public void listagemVeiculo(List<Veiculo> lista) {
		try{
			// model armazena todas as linhas e colunas do table
			DefaultTableModel model = new DefaultTableModel();

			//adicionar colunas no model
			model.addColumn("placa");
			model.addColumn("tipo");

			//adicionar linhas no model
			for(Veiculo veiculo : lista) {
				model.addRow(new Object[]{veiculo.getPlaca(), veiculo.getTipoveiculo()} );
			}
			//atualizar model no table (visualizacao)
			table.setModel(model);

			label_4.setText("resultados: " + lista.size() + " objetos");
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}
	}
	
	public void listagemRegistro(List<Registro> lista) {
		try{
			// model armazena todas as linhas e colunas do table
			DefaultTableModel model = new DefaultTableModel();

			//adicionar colunas no model
			model.addColumn("id");
			model.addColumn("data/hora");
			model.addColumn("veículo");
			model.addColumn("operação");

			//adicionar linhas no model
			for(Registro registro : lista) {
				model.addRow(new Object[]{registro.getId(), registro.getDatahora(), registro.getVeiculo().getPlaca(), registro.getOperacao()} );
			}
			//atualizar model no table (visualizacao)
			table.setModel(model);

			label_4.setText("resultados: " + lista.size() + " objetos");
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}
	}

}
