/**
 * Panel3.java
   1 dic. 2020 17:17:50
 */
package clases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * La Clase Panel3.
 *
 * @author Rub�n Garc�a Gonz�lez
 */
public class Panel3 extends JPanel {

	/** Etiquetas del panel. */
	private JLabel tipoHab, numHab, ni�os, importe;

	/** Campo de texto para el importe total. */
	private JTextField tfImporte;

	/** JSpinner del n�mero de habitaciones. */
	private JSpinner spNumHab;

	/** JCheckBox para afirmar si hay ni�os o no. */
	private JCheckBox check;

	/** Selector de tipo de habitaciones. */
	private JComboBox<String> tipos;

	/** Colores para el texto y el <i>background</i> del panel. */
	private Color colorFondo, colorTexto;

	/** Fuente. */
	private Font fuente;

	/** PanelNi�o. */
	private PanelNi�o pNi�os;

	/** PanelImagenes. */
	private PanelImagenes pImagenes;

	/** Panel2. */
	private Panel2 panel2;

	/**
	 * En el constructor por defecto de la clase simplemente llamamos al m�todo
	 * <i>setUp()</i> .
	 */
	public Panel3() {
		setUp();
	}

	/**
	 * M�todo <b>setUp()</b>. Este m�todo se encarga simplemente de instanciar los
	 * diferentes campos y etiquetas con un valor definido, distribuirlos en el
	 * panel y agregarlos a �ste. A�adimos un evento del tipo <i>ItemListener</i> al
	 * JCheckBox en d�nde, si est� seleccionado, se motrar� el PanelNi�o; de lo
	 * contrario, estar� oculto. Tambi�n a�adimos un evento del tipo
	 * <i>FocusListener</i> al campo tfImporte en donde, dependiendo de si hemos
	 * seleccionado al menos una habitaci�n o no, se mostrar� o no el precio total
	 * de la reserva.
	 */
	private void setUp() {
		this.setLayout(null);
		colorFondo = new Color(0, 149, 163);
		colorTexto = new Color(254, 231, 29);
		fuente = new Font("", Font.BOLD, 17);
		panel2 = new Panel2();

		// Inicializamos las variables
		tipoHab = new JLabel("Tipo de habitaci�n: ");
		numHab = new JLabel("N� de hab: ");
		ni�os = new JLabel("�Ni�os? ");
		importe = new JLabel("Importe ");

		tipoHab.setBounds(10, 10, 180, 30);
		tipoHab.setFont(fuente);
		tipoHab.setForeground(colorTexto);
		numHab.setBounds(10, 60, 100, 30);
		numHab.setFont(fuente);
		numHab.setForeground(colorTexto);
		ni�os.setBounds(10, 110, 100, 30);
		ni�os.setFont(fuente);
		ni�os.setForeground(colorTexto);

		String[] tipHab = { "Simple", "Doble", "Suite" };
		tipos = new JComboBox<String>(tipHab);

		tipos.setBounds(180, 10, 150, 30);

		spNumHab = new JSpinner(new SpinnerNumberModel(0, 0, 50, 1));
		spNumHab.setBounds(100, 60, 100, 30);

		pNi�os = new PanelNi�o();
		pNi�os.setBounds(10, 150, 350, 150);

		pImagenes = new PanelImagenes();
		pImagenes.setBounds(400, 10, 570, 630);

		check = new JCheckBox();
		check.setSelected(false);
		check.setBounds(98, 117, 20, 20);
		check.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getSource() == check) {
					if (check.isSelected()) {
						pNi�os.show();
					} else {
						pNi�os.hide();
					}
				}

			}
		});

		importe.setBounds(300, 320, 220, 30);
		importe.setFont(new Font("Eras Bold ITC", Font.BOLD, 35));
		importe.setForeground(colorTexto);
		tfImporte = new JTextField();
		tfImporte.setBounds(280, 370, 200, 30);

		tfImporte.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
			}

			@Override
			public void focusGained(FocusEvent e) {
				tfImporte.setHorizontalAlignment(JTextField.CENTER);
				if (!comprobacionVacio()) {
					tfImporte.setText("Min: 1 habitaci�n a reservar");
					tfImporte.setForeground(Color.RED);
				} else {
					tfImporte.setForeground(Color.BLACK);
					tfImporte.setText(importeReserva().toString() + "�");
				}

			}
		});

		this.add(tipoHab);
		this.add(tipos);
		this.add(numHab);
		this.add(spNumHab);
		this.add(ni�os);
		this.add(check);
		this.add(pNi�os);
		this.add(importe);
		this.add(tfImporte);
		this.add(pImagenes);

		// Establecemos un borde a nuestro panel
		setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		this.setBackground(colorFondo);
	}

	/**
	 * M�todo <b>limpiadoCampos()</b>. Establece los valores a su valor por defecto.
	 */
	public void limpiadoCampos() {
		spNumHab.setValue(Integer.valueOf(0));
		check.setSelected(false);
		tipos.setSelectedIndex(0);
		tfImporte.setText("");
		pNi�os.limpiadoCampos();
	}

	/**
	 * M�todo <b>comprobacionVacio()</b>. Comprueba si el valor del JSpinner
	 * <i>spNumHab</i> es distinto a 0.
	 *
	 * @return true, si es distinto a 0.
	 */
	public boolean comprobacionVacio() {
		if ((Integer) spNumHab.getValue() == 0) {
			return false;
		}
		return true;
	}

	/**
	 * M�todo <b>importeReserva()</b>. Recoge el valor de todos los campos
	 * necesarios para hacer el c�lculo total de la reserva.
	 *
	 * @return El importe total
	 */
	public String importeReserva() {
		int dias = Panel2.getNumDias();
		int numHabitaciones = (Integer) spNumHab.getValue();
		int valorCunaOCama = 0;
		int valorHab = 0;
		int importeReserva = 0;
		if (tipos.getSelectedItem() == "Simple") {
			valorHab = 50;
		} else if (tipos.getSelectedItem() == "Doble") {
			valorHab = 75;
		} else {
			valorHab = 125;
		}

		if (check.isSelected()) {
			valorCunaOCama = 20;
		}

		if (check.isSelected()) {
			importeReserva = dias * ((numHabitaciones * valorHab) + valorCunaOCama);
		} else {
			importeReserva = dias * (numHabitaciones * valorHab);
		}

		return Integer.toString(importeReserva);
	}

	/**
	 * M�todo <b>getSpNumHab</b>. Devuelve el valor del JSpinner <i>spNumHab</i>.
	 *
	 * @return valor del <i>spNumHab</i>
	 */
	public String getSpNumHab() {
		int valor = (Integer) spNumHab.getValue();
		return "N� de habitaciones: " + valor;
	}

	/**
	 * M�todo <b>getCheck</b>. Devuelve una cadena con la opci�n escogida,
	 * dependiendo si est� o no seleccionado el campo <i>check</i>.
	 * 
	 * @return la opci�n.
	 */
	public String getCheck() {
		String opcion = null;
		if (check.isSelected()) {
			opcion = "S�";
		} else {
			opcion = "No";
		}
		return "�Ni�os?: " + opcion;
	}

	/**
	 * M�todo <b>getTipos</b>. Devuelve el tipo de habitaci�n seleccionada por el
	 * usuario.
	 * 
	 * @return el tipo de habitaci�n.
	 */
	public String getTipos() {
		return "Tipo de habitaci�n: " + tipos.getSelectedItem();
	}

	/**
	 * M�todo <b>getPNi�osEdad</b>. Devuelve la edad de los ni�os.
	 * 
	 * @return la edad de los ni�os.
	 */
	public String getPNi�osEdad() {
		String valor = pNi�os.getSpEdadNi�o();
		return valor;
	}

	/**
	 * M�todo <b>getPNi�osExtras</b>. Devuelve los extras asociados a la edad de los
	 * ni�os.
	 * 
	 * @return los extras.
	 */
	public String getPNi�osExtras() {
		String valor = pNi�os.getTfExtras();
		return valor;
	}

}

class PanelNi�o extends JPanel {

	/** Etiquetas para el panel PanelNi�o. */
	private JLabel edadNi�os, extras;
	/** Campo de texto para los extras. */
	private JTextField tfExtras;
	/** JSpinner para la edad de los ni�os. */
	private JSpinner spEdadNi�o;
	/** Colores para el texto y el <i>background</i> del panel. */
	private Color colorFondo, colorTexto;
	/** Fuente. */
	private Font fuente;

	/**
	 * En el constructor por defecto de la clase PanelNi�o se llama �nicamente al
	 * m�todo <i>setUp()</i>.
	 */
	public PanelNi�o() {
		setUp();
	}

	/**
	 * M�todo <b>setUp()</b>. Este m�todo se encarga simplemente de instanciar los
	 * diferentes campos y etiquetas con un valor definido, distribuirlos en el
	 * panel y agregarlos a �ste. Agregamos una escucha a eventos del tipo
	 * <i>ChangeListener</i> sobre el campo <b>spEdadNi�o</b> en d�nde, dependiendo
	 * de la edad seleccionada, saldr� un texto u otro en el campo de texto
	 * <b>tfExtras</b>
	 * 
	 */
	private void setUp() {
		this.setLayout(null);
		colorFondo = new Color(254, 231, 29);
		colorTexto = new Color(0, 149, 163);
		fuente = new Font("", Font.BOLD, 17);

		// Inicializamos las variables
		edadNi�os = new JLabel("Edad de ni�os: ");
		edadNi�os.setFont(fuente);
		edadNi�os.setForeground(colorTexto);
		extras = new JLabel("Extras: ");
		extras.setFont(fuente);
		extras.setForeground(colorTexto);

		edadNi�os.setBounds(20, 20, 150, 30);
		extras.setBounds(20, 80, 100, 30);

		tfExtras = new JTextField("Cuna");
		tfExtras.setBounds(90, 80, 180, 30);
		tfExtras.setEditable(false);

		spEdadNi�o = new JSpinner(new SpinnerNumberModel(0, 0, 14, 1));
		spEdadNi�o.setBounds(160, 20, 50, 30);

		spEdadNi�o.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if (e.getSource() == spEdadNi�o) {
					int valor = (int) spEdadNi�o.getValue();
					if (valor > 0 && valor <= 3) {
						tfExtras.setText("");
						tfExtras.setText("Cuna");
					}
					if (valor >= 4 && valor <= 10) {
						tfExtras.setText("");
						tfExtras.setText("Cama supletoria peque�a");
					}
					if (valor > 10) {
						tfExtras.setText("");
						tfExtras.setText("Cama supletoria normal");
					}
				}

			}
		});

		this.add(edadNi�os);
		this.add(extras);
		this.add(spEdadNi�o);
		this.add(tfExtras);

		this.setBackground(colorFondo);
		this.hide();

	}

	/**
	 * M�todo <b>limpiadoCampos()</b> Establece el campo <i>spEdadNi�o</i> con su
	 * valor predeterminado
	 */
	public void limpiadoCampos() {
		spEdadNi�o.setValue(Integer.valueOf(0));
	}

	/**
	 * M�todo <b>getTfExtras()</b> Devuelve el texto del campo <i>tfExtras</i>
	 * 
	 * @return texto del campo <i>tfExtras</i>
	 */
	public String getTfExtras() {
		return "Extras: " + tfExtras.getText();
	}

	/**
	 * M�todo <b>spEdadNi�o()</b> Devuelve el valor del JSpinner <i>spEdadNi�o</i>
	 * 
	 * @return valor del JSpinner
	 */
	public String getSpEdadNi�o() {
		int valor = (Integer) spEdadNi�o.getValue();
		return "Edad del ni�o: " + valor;
	}

}

class PanelImagenes extends JPanel implements ActionListener {

	/** Etiquetas d�nde se colocar�n las diferentes im�genes. */
	private JLabel etiqueta;
	/** Color para el fondo del panel. */
	private Color colorFondo;
	private JButton b1, b2, b3;
	private ImageIcon imagen1, imagen2, imagen3;

	/**
	 * M�todo <b>PanelImagenes()</b>. Llamamos al m�todo <i>setUp()</i>
	 * 
	 */
	public PanelImagenes() {
		setUp();
	}

	/**
	 * M�todo <b>setUp()</b> Colocamos las im�genes en el panel.
	 */
	private void setUp() {
		this.setLayout(null);
		colorFondo = new Color(0, 149, 163);
		setVisible(true);
		b1 = new JButton("1");
		b1.setBounds(120, 220, 50, 50);
		b2 = new JButton("2");
		b2.setBounds(170, 220, 50, 50);
		b3 = new JButton("3");
		b3.setBounds(220, 220, 50, 50);
		this.add(b1);
		this.add(b2);
		this.add(b3);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		imagen1 = new ImageIcon(getClass().getResource("../recursos/resort1.jpg"));
		imagen2 = new ImageIcon(getClass().getResource("../recursos/resort2.jpg"));
		imagen3 = new ImageIcon(getClass().getResource("../recursos/resort3.jpg"));
		etiqueta = new JLabel("", JLabel.CENTER);
		etiqueta.setBounds(-20, 0, 400, 200);
		add(etiqueta, BorderLayout.CENTER);
		etiqueta.setIcon(imagen1);
		this.setBackground(colorFondo);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			etiqueta.setIcon(imagen1);
		}
		if (e.getSource() == b2) {
			etiqueta.setIcon(imagen2);
		}
		if (e.getSource() == b3) {
			etiqueta.setIcon(imagen3);
		}
	}

}
