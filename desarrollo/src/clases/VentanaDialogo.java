/**
 * VentanaDialogo.java
   26 nov. 2020 10:15:14
 */
package clases;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import javafx.geometry.Bounds;

/**
 * La clase VentanaDialogo .
 *
 * @author Rubén García González
 */
public class VentanaDialogo extends JDialog {

	/** Icono de la ventana. */
	private ImageIcon icon;

	/** Panel 1. */
	private Panel1 panel1;

	/** Panel 2. */
	private Panel2 panel2;

	/** Panel 3. */
	private Panel3 panel3;

	/** Panel 4. */
	private Panel4 panel4;

	/** PanelNiño. */
	private PanelNiño pNiño;

	/** Botones para imprimir, guardar y crear nuevos datos de la reserva. */
	private JButton imprimir, nuevo, guardar;

	/** Imagenes para cada uno de los botones. */
	private ImageIcon imagen1, imagen2, imagen3;

	/**
	 * Método <VentanaDialogo(VentanaPrincipal miVentana, boolean modal)>.
	 * Constructor de la ventana diálogo. Recibe por parámetros la ventana "padre" y
	 * el booleano que lo convierte en ventana diálogo. Se establece la medida de
	 * esta junto al título y al icono y se llama al método <i>setUp()</i> para el
	 * resto de configuración.
	 *
	 * @param miVentana ventana padre
	 * @param modal     booleano para decir si es modal o no
	 */
	public VentanaDialogo(VentanaPrincipal miVentana, boolean modal) {
		super(miVentana, modal);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// Establecemos el tamaño de la ventana dialogo a la resolución de la pantalla
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setTitle("Altas Reservas");
		// Establecemos un ícono para esta ventana diálogo de igual forma que lo hacemos
		// con la ventana principal
		icon = new ImageIcon(getClass().getResource("../recursos/icon.png"));
		this.setIconImage(icon.getImage());
		this.setLayout(null);
		this.setResizable(false);

		setUp();

	}

	/**
	 * Método <b>setUp()</b>. Instancia y coloca cada uno de los paneles en sus
	 * correspondientes posiciones y agrega las funcionalidades a los botones.
	 */
	private void setUp() {

		panel1 = new Panel1();
		panel1.setBounds(15, 15, 800, 100);

		panel2 = new Panel2();
		panel2.setBounds(15, 130, 800, 150);

		panel3 = new Panel3();
		panel3.setBounds(15, 295, 800, 500);

		panel4 = new Panel4();
		panel4.setBounds(880, 9, 834, 890);

		pNiño = new PanelNiño();

		imagen1 = new ImageIcon(getClass().getResource("../recursos/print.png"));
		imprimir = new JButton(imagen1);

		imagen2 = new ImageIcon(getClass().getResource("../recursos/save.png"));
		guardar = new JButton(imagen2);

		imagen3 = new ImageIcon(getClass().getResource("../recursos/new.png"));
		nuevo = new JButton(imagen3);

		imprimir.setBounds(250, 810, 80, 70);
		nuevo.setBounds(350, 810, 80, 70);
		guardar.setBounds(450, 810, 80, 70);

		/**
		 * Cuando se pulsa en el botón <b>Nuevo</b>, se llama a los métodos de limpiado
		 * de cada uno de los paneles.
		 */
		nuevo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == nuevo) {
					panel2.limpiadoCampos();
					panel3.limpiadoCampos();
					panel4.limpiadoCampos();
				}

			}
		});

		/**
		 * Cuando se pulsa en el botón <b>Guardar</b>, se comprueba que no haya campos
		 * vacíos. Si los hay, se mostrará una ventana avisándonos de ello; de lo
		 * contrario, saldrá una ventana con el mensaje "Registro guardado
		 * correctamente"
		 */
		guardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == guardar) {
					if (!panel2.comprobacionVacio() || !panel3.comprobacionVacio()) {
						JOptionPane.showMessageDialog(null, "Faltan campos por rellenar todavía", "No se puede guardar",
								JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Registro guardado correctamente", "Guardado",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}

			}
		});

		/**
		 * Cuando se pulsa en el botón <b>Imprimir</b>, se comprueba que no haya campos
		 * vacíos. Si los hay, se mostrará una ventana avisándonos de ello; de lo
		 * contrario, se llamará a los métodos <i>cargarDatosCliente()</i> y
		 * <i>cargarDatosReserva()</i>"
		 */
		imprimir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == imprimir) {
					if (!panel2.comprobacionVacio() || !panel3.comprobacionVacio()) {
						JOptionPane.showMessageDialog(null, "Faltan campos por rellenar todavía",
								"No se puede imprimir", JOptionPane.ERROR_MESSAGE);
					} else {
						cargarDatosCliente();
						cargarDatosReserva();
					}
				}

			}
		});

		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.add(panel4);
		this.add(imprimir);
		this.add(nuevo);
		this.add(guardar);

	}

	/**
	 * Método <b>cargarDatosCliente()</b>. Recoge los valores de los campos del
	 * cliente, y los agrega al área de texto correspondiente en el Panel 4.
	 */
	public void cargarDatosCliente() {
		String mensaje = panel2.getTfNombre() + "\n" + panel2.getTfApellidos() + "\n" + panel2.getTfDni() + "\n"
				+ panel2.getTfTelefono() + "\n" + panel2.getTfFechaEntrada() + "\n" + panel2.getTfFechaSalida() + "\n"
				+ panel2.getTfNumEntancias();
		panel4.setTa1(mensaje);
	}

	/**
	 * Método <b>cargarDatosReserva()</b>. Recoge los valores de los campos de la
	 * reserva, y los agrega al área de texto correspondiente en el Panel 4.
	 */
	public void cargarDatosReserva() {
		String mensaje1 = panel3.getTipos() + "\n" + panel3.getSpNumHab() + "\n" + panel3.getCheck();
		if (panel3.getCheck().equalsIgnoreCase("¿Niños?: Sí")) {
			mensaje1 += "\n" + panel3.getPNiñosEdad() + "\n" + panel3.getPNiñosExtras();
			panel4.setTa2(mensaje1);
		} else {
			panel4.setTa2(mensaje1);
		}
	}

}
