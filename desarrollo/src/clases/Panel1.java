/**
 * Panel1.java
   26 nov. 2020 10:36:04
 */
package clases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import org.w3c.dom.css.CSSPrimitiveValue;
import org.w3c.dom.css.RGBColor;


/**
 * La Clase Panel1.
 *
 * @author Rubén García González
 */
public class Panel1 extends JPanel {

	/** Etiqueta del título. */
	private JLabel titulo;
	
	/** Colores para el fondo del panel y el texto. */
	private Color color1, color2;

	/**
	 * En el constructor por defecto, simplemente llamamos al método <i>setUp()</i>.
	 */
	public Panel1() {
		setUp();
	}

	/**
	 * Método <b>setUp()</b>.
	 * Instancia los elementos del panel, y los agrega a éste.
	 * Se le agrega también un borde.
	 */
	private void setUp() {
		this.setLayout(new BorderLayout());
		color1 = new Color(81,45,177);
		color2 = new Color(254,231,29);

		titulo = new JLabel("RUBEN'S RESORTS",SwingConstants.CENTER);
		titulo.setFont(new Font("Eras Bold ITC", Font.BOLD, 45));
		titulo.setForeground(color2);
		this.setBackground(color1);

		//Establecemos un borde a nuestro panel
		setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

		this.add(titulo, BorderLayout.CENTER);
	}

}
