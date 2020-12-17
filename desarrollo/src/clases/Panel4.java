/**
 * Panel4.java
   2 dic. 2020 9:06:49
 */
package clases;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * La Clase Panel4
 *
 * @author Rubén García González
 */
public class Panel4 extends JPanel {

	/** JTabbedPane. */
	private JTabbedPane tabPane;
	
	/** Paneles a insertar en el JTabbedPane. */
	private JPanel panel1, panel2;
	
	/** Ambos JTextAreas. */
	private JTextArea ta1, ta2;
	
	/** Fuente. */
	private Font fuente;
	
	/** JSlider. */
	private JSlider slider;
	
	/** Etiqueta. */
	private JLabel etiqueta;

	/**
	 * En el constructor por defecto Panel4 llamamos al método encargado de colocar los elementos, <i>setUp()</i>
	 */
	public Panel4() {
		setUp();
	}

	/**
	 * Método <b>setUp()</b>
	 * Instancia el JTabbedPane, y agrega dos pestañas, ambos con sus paneles correspondientes, y por último
	 * lo agrega al panel principal. También se instancia un JSlider, el cual esta puesto a la escucha de eventos del tipo
	 * <i>ChangeListener</i> y que gracias a esto, va cambiando el tamaño del texto de los JTextAreas
	 */
	private void setUp() {
		this.setLayout(null);
		tabPane = new JTabbedPane();

		// Componentes panel 1
		componentesPanel1();
		// Componentes panel 2
		componentesPanel2();

		tabPane.addTab("Datos del cliente", panel1);
		tabPane.addTab("Datos de la habitación", panel2);
		tabPane.setBounds(1, 1, 832, 788);
		this.add(tabPane);

		slider = new JSlider(SwingConstants.HORIZONTAL, 22, 45, 22);
		etiqueta = new JLabel("Tamaño de la fuente");

		etiqueta.setFont(new Font("", Font.BOLD, 24));
		etiqueta.setBounds(300, 800, 300, 30);
		slider.setBounds(320, 830, 150, 30);
		slider.setSize(200, 50);

		slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				ta1.setFont(new Font("", Font.PLAIN, slider.getValue()));
				ta2.setFont(new Font("", Font.PLAIN, slider.getValue()));
			}
		});

		this.add(slider);
		this.add(etiqueta);

	}

	/**
	 * Método <b>componentesPanel1()</b>
	 * Crea y coloca los componentes del panel 1
	 */
	private void componentesPanel1() {
		panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setSize(832, 860);

		ta1 = new JTextArea();
		ta1.setBounds(0, 0, 832, 860);
		fuente = ta1.getFont();
		float tamaño = fuente.getSize() + 8.0f;
		ta1.setFont(fuente.deriveFont(tamaño));
		ta1.setEditable(false);

		panel1.add(ta1);

	}

	/**
	 * Método <b>componentesPanel2()</b>
	 * Crea y coloca los componentes del panel 2
	 */
	private void componentesPanel2() {
		panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setSize(832, 860);

		ta2 = new JTextArea();
		ta2.setBounds(0, 0, 832, 860);
		fuente = ta2.getFont();
		float tamaño = fuente.getSize() + 8.0f;
		ta2.setFont(fuente.deriveFont(tamaño));
		ta2.setEditable(false);

		panel2.add(ta2);

	}

	/**
	 * Método <b>limpiadoCampos()</b>
	 * Limpia los JTextArea
	 */
	public void limpiadoCampos() {
		ta1.setText("");
		ta2.setText("");
	}


	/**
	 * Método <b>setTa1(String t)</b>.
	 * Establece el texto del componente con la variable recibida.
	 *
	 * @param t: nuevo valor para ta1
	 */
	public void setTa1(String t) {
		this.ta1.setText(t);
	}

	/**
	 * Método <b>setTa1(String t)</b>.
	 * Establece el texto del componente con la variable recibida.
	 *
	 * @param t: nuevo valor para ta2
	 */
	public void setTa2(String t) {
		this.ta2.setText(t);
	}
}
