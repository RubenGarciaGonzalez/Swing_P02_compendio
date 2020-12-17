/**
 * VentanaPrincipal.java
   25 nov. 2020 8:24:44
 */
package clases;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


/**
 * La clase VentanaPrincipal.
 *
 * @author Rubén García González
 */
public class VentanaPrincipal extends JFrame implements ActionListener {

	/** Variables para las imagenes de los botones, y para el icono de la propia ventana. */
	private ImageIcon icon, botonNuevo, botonBasura;
	
	/** Los botones principales de la ventana. */
	private JButton nuevo, basura;
	
	/** La barra de herramientas. */
	private JMenuBar miBarra;
	
	/** Los diferentes menus de la barra de herramientas. */
	private JMenu archivo, registro, ayuda;
	
	/** Los elementos que componen cada uno de los menús de la barra de herramientas. */
	private JMenuItem salir, altasReservas, bajasReservas, acerca;
	
	/** Un objeto de nuestra propia ventana. */
	private VentanaPrincipal miVentana;
	
	/** Un objeto de la ventana dialogo. */
	private VentanaDialogo miDialogo;

	/**
	 * Constructor de la clase, donde se establece el título y el icono de la ventana, y se llama a los diferentes métodos encargados de la distribución de esta.
	 */
	public VentanaPrincipal() {
		super("Gestión Hotel Ruben's Resorts");
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		icon = new ImageIcon(getClass().getResource("../recursos/icon.png"));
		this.setIconImage(icon.getImage());

		configuracionPantalla();
		setUpBotones();
		setUpBarra();

		this.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * Método <b>configuracionPantalla()</b>.
	 * Encargado de establecer el tamaño de la ventana y la localización de esta en el centro de la pantalla del ordenador.
	 */
	private void configuracionPantalla() {
		// Establecemos el tamaño y la posición de la ventana
		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		Dimension tamanioPantalla = miPantalla.getScreenSize();
		// Obtenemos el ancho y alto de la pantalla y lo guardamos en dos variables
		int altoPantalla = tamanioPantalla.height;
		int anchoPantalla = tamanioPantalla.width;
		// Vamos a crear un marco que tenga la mitad de mi pantalla
		this.setSize(anchoPantalla / 2, altoPantalla / 2);
		this.setLocation(anchoPantalla / 4, altoPantalla / 4);

	}

	/**
	 * Método <b>setUpBarra()</b>
	 * Configuramos la barra de herramientas con todos sus componentes y la establecemos como MenuBar de la propia ventana.
	 * También se configura el nemotécnico Alt + R en el menú Registro
	 */
	private void setUpBarra() {
		miBarra = new JMenuBar();

		archivo = new JMenu("Archivo");
		registro = new JMenu("Registro");
		ayuda = new JMenu("Ayuda");

		salir = new JMenuItem("Salir");
		altasReservas = new JMenuItem("Altas Reservas");
		bajasReservas = new JMenuItem("Bajas Reservas");
		acerca = new JMenuItem("Acerca de...");

		bajasReservas.addActionListener(this);
		altasReservas.addActionListener(this);
		acerca.addActionListener(this);

		salir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int confirmar = JOptionPane.showConfirmDialog(null, "¿Deseas cerrar la aplicación?",
						"Saliendo del programa", JOptionPane.YES_NO_OPTION);

				if (confirmar == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});

		archivo.add(salir);
		registro.add(altasReservas);
		registro.add(bajasReservas);
		ayuda.add(acerca);
		
		registro.setMnemonic(KeyEvent.VK_R);

		miBarra.add(archivo);
		miBarra.add(registro);
		miBarra.add(ayuda);

		setJMenuBar(miBarra);
	}

	/**
	 * Método <b>setUpBotones()</b>.
	 * Establece los botones en su posicion con las imagenes elegidas, y les añade funcionalidad con eventos de tipo Action,
	 * También se les agrega el nemotécnico a cada uno de ellos.
	 */
	private void setUpBotones() {
		nuevo = new JButton();
		basura = new JButton();

		botonNuevo = new ImageIcon(getClass().getResource("../recursos/botonNew2.png"));
		nuevo.setIcon(botonNuevo);
		botonBasura = new ImageIcon(getClass().getResource("../recursos/botonBasura.png"));
		basura.setIcon(botonBasura);

		nuevo.setBounds(150, 70, 300, 300);
		basura.setBounds(490, 70, 300, 300);
		add(nuevo);
		add(basura);

		basura.setMnemonic(KeyEvent.VK_B);
		nuevo.setMnemonic(KeyEvent.VK_A);

		basura.addActionListener(this);
		nuevo.addActionListener(this);
	}

	/**
	 * Método <b>setVentana(VentanaPrincipal miV)</b>.
	 * Establece la ventana principal como ventana, esto no servirá a la hora de crear la ventana diálogo.
	 *
	 * @param miV: la nueva ventana
	 */
	public void setVentana(VentanaPrincipal miV) {
		miVentana = miV;
	}

	/**
	 * Se configura las diferentes acciones dependiendo del evento que reciba.
	 *
	 * @param e: evento de acción.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == basura || e.getSource() == bajasReservas) {
			JOptionPane.showMessageDialog(null, "Esta opción todavía no ha sido desarrollada", "Opción no desarrollada",
					JOptionPane.WARNING_MESSAGE);
		}

		if (e.getSource() == acerca) {
			String mensaje = "<html>"
					+ "Aplicación para la gestión de reservas del Hotel<span style=' font-style: italic;'> Ruben's Resorts</span></html>\n"
					+ "Desarrollada por Rubén García González  ";
			JOptionPane.showMessageDialog(null, mensaje, "Información de la aplicación",
					JOptionPane.INFORMATION_MESSAGE);
		}

		if (e.getSource() == nuevo || e.getSource() == altasReservas) {
			miDialogo = new VentanaDialogo(miVentana, true);
			miDialogo.setVisible(true);
		}

	}

}
