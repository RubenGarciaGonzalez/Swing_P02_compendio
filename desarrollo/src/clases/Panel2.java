/**
 * Panel2.java
   30 nov. 2020 13:02:11
 */
package clases;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;


/**
 * La Clase Panel2.
 *
 * @author Rubén García González
 */
public class Panel2 extends JPanel implements DocumentListener {

	/** Colores para el texto y el <i>background</i> del panel. */
	private Color colorTexto, colorFondo;

	/** Diferentes etiquetas para los campos. */
	private JLabel titulo, nombre, apellidos, dni, telefono, fechaEntrada, fechaSalida, numEstancias;

	/**
	 * Campos de texto con posibilidad de formateo previo para aplicarle las
	 * "máscaras".
	 */
	private JFormattedTextField tfDni, tfTelefono, tfFechaEntrada, tfFechaSalida;

	/** Máscaras a aplicar a los campos con posibilidad de formateo. */
	private MaskFormatter mascaraDni, mascaraTelefono, mascaraFecha;

	/** Campos de texto. */
	public JTextField tfNombre, tfApellidos, tfNumEntancias;

	/**
	 * Variable <b>static</b> en dónde se almacenará el total de días de la
	 * instancia. Se declara de ésta forma para poder enviar su valor a otra clase
	 */
	static int totalDias;

	/**
	 * En el constructor por defecto llamamos al método <i>setUp()</i> para la
	 * configuración del panel.
	 */
	public Panel2() {
		setUp();
	}

	/**
	 * Método <b>setUp()</b>. Este método se encarga simplemente de instanciar los
	 * diferentes campos y etiquetas con un valor definido, distribuirlos en el
	 * panel y agregarlos a éste. Se llama al método <i>configuracionFechas()</i>
	 * para establecer las fechas en los campos correspondientes, y se le añade una
	 * escucha al campo de <i>tfFechaSalida</i> del tipo <b>DocumentListener</b>
	 */
	private void setUp() {
		this.setLayout(null);
		colorTexto = new Color(81, 45, 177);
		colorFondo = new Color(78, 205, 196);

		titulo = new JLabel("Datos del cliente");
		titulo.setForeground(colorTexto);
		nombre = new JLabel("Nombre: ");
		nombre.setForeground(colorTexto);
		apellidos = new JLabel("Apellidos: ");
		apellidos.setForeground(colorTexto);
		dni = new JLabel("DNI: ");
		dni.setForeground(colorTexto);
		telefono = new JLabel("Teléfono: ");
		telefono.setForeground(colorTexto);
		fechaEntrada = new JLabel("Fecha Entrada: ");
		fechaEntrada.setForeground(colorTexto);
		fechaSalida = new JLabel("Fecha Salida: ");
		fechaSalida.setForeground(colorTexto);
		numEstancias = new JLabel("Nº de días estancia: ");
		numEstancias.setForeground(colorTexto);
		tfNombre = new JTextField();
		tfApellidos = new JTextField();
		tfNumEntancias = new JTextField();

		titulo.setBounds(360, 3, 100, 30);
		nombre.setBounds(20, 40, 100, 30);
		tfNombre.setBounds(87, 43, 130, 25);
		apellidos.setBounds(20, 75, 100, 30);
		tfApellidos.setBounds(87, 76, 130, 25);
		
		telefono.setBounds(320, 40, 100, 25);
		dni.setBounds(320, 75, 100, 25);
		
		fechaEntrada.setBounds(560, 40, 100, 30);
		fechaSalida.setBounds(560, 75, 100, 30);
		numEstancias.setBounds(330, 115, 130, 30);
		tfNumEntancias.setBounds(460, 115, 50, 30);
		tfNumEntancias.setEditable(false);

		try {
			mascaraDni = new MaskFormatter("AAAAAAAAA");
			tfDni = new JFormattedTextField(mascaraDni);
			tfDni.setFocusLostBehavior(JFormattedTextField.PERSIST);
			tfDni.setBounds(385, 75, 100, 25);
			mascaraTelefono = new MaskFormatter("#########");
			tfTelefono = new JFormattedTextField(mascaraTelefono);
			tfTelefono.setFocusLostBehavior(JFormattedTextField.PERSIST);
			tfTelefono.setBounds(385, 40, 100, 25);
			mascaraFecha = new MaskFormatter("##/##/####");
			tfFechaEntrada = new JFormattedTextField(mascaraFecha);
			tfFechaEntrada.setBounds(660, 40, 120, 30);
			tfFechaSalida = new JFormattedTextField(mascaraFecha);
			tfFechaSalida.setBounds(660, 75, 120, 30);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		configuracionFechas();

		this.add(titulo);
		this.add(nombre);
		this.add(tfNombre);
		this.add(apellidos);
		this.add(tfApellidos);
		this.add(telefono);
		this.add(tfTelefono);
		this.add(dni);
		this.add(tfDni);
		this.add(fechaEntrada);
		this.add(tfFechaEntrada);
		this.add(fechaSalida);
		this.add(tfFechaSalida);
		this.add(numEstancias);
		this.add(tfNumEntancias);
		setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		setBackground(colorFondo);

		tfFechaSalida.getDocument().addDocumentListener(this);

	}

	/**
	 * Método <b>configuracionFechas()</b>. Crea dos fechas, una con la fecha
	 * actual, y otra con un día sumado, y las establece en los respectivos campos.
	 * Por último, llama al método <i>restaDias(Date fechaActual, Date
	 * fechaFinal)</i> y le pasa como parámetros dichas fechas.
	 */
	private void configuracionFechas() {
		Date fechaActual = new Date();
		Date fechaFinal = new Date(fechaActual.getTime() + (1000 * 60 * 60 * 24));
		tfFechaEntrada.setText(new SimpleDateFormat("dd/MM/yyyy").format(fechaActual));
		tfFechaSalida.setText(new SimpleDateFormat("dd/MM/yyyy").format(fechaFinal));

		restaDias(fechaActual, fechaFinal);

	}

	/**
	 * Método <b>restaDias(Date fechaActual, Date fechaFinal)</b>.
	 * Se obtiene el tiempo en milisegundos de cada fecha, y se almacena en variables, se restan y parsea a días utilizando la clase <i>TimeUnit</i>
	 * Una vez obtenido, lo establece en el campo <i>tfNumEstancias</i>, e iguala la variable estática <i>totalDias</i> a este valor
	 * @param fechaActual: la fecha actual (fecha de entrada)
	 * @param fechaFinal:  la fecha final (fecha de salida)
	 */
	private void restaDias(Date fechaActual, Date fechaFinal) {
		long comienzo = fechaActual.getTime();
		long finalizacion = fechaFinal.getTime();
		long diferenciaDias = finalizacion - comienzo;
		int numDias = (int) TimeUnit.DAYS.convert(diferenciaDias, TimeUnit.MILLISECONDS);
		this.setTfNumEntancias(numDias);
		totalDias = Integer.parseInt(tfNumEntancias.getText());
	}

	/**
	 * Método <b>limpiadoCampos()</b>
	 * Limpia los campos y los vuelve a su valores por defecto, poniendo el foco en el campo del nombre finalmente (<i>tfNombre</i>)
	 */
	public void limpiadoCampos() {
		tfNombre.setText("");
		tfApellidos.setText("");
		tfDni.setValue(null);
		tfTelefono.setValue(null);
		Date fechaActual = new Date();
		Date fechaFinal = new Date(fechaActual.getTime() + (1000 * 60 * 60 * 24));
		tfFechaEntrada.setText(new SimpleDateFormat("dd/MM/yyyy").format(fechaActual));
		tfFechaSalida.setText(new SimpleDateFormat("dd/MM/yyyy").format(fechaFinal));
		tfNombre.requestFocus();
	}

	/**
	 * Método <b>comprobacionVacio()</b>
	 * Comprueba ninguno de los campos este vacío.
	 *
	 * @return true, si están todos completos
	 */
	public boolean comprobacionVacio() {
		if (tfNombre.getText().length() == 0 || tfApellidos.getText().length() == 0 || tfDni.getText().length() == 0 || tfTelefono.getText().length() == 0) {
			return false;
		}
		return true;
	}

	/**
	 * Método <b>insertUpdate(DocumentEvent e)</b>.
	 * Parsea las fechas obtenidas de los campos de texto con un formato específico, y las resta, actualizando el campo
	 * <i>tfNumEstancias</i> con el valor de la resta
	 *
	 * @param e: evento del tipo <b>DocumentEvent</b>
	 */
	@Override
	public void insertUpdate(DocumentEvent e) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String fInicial = tfFechaEntrada.getText();
		String fFinal = tfFechaSalida.getText();
		Date fIni = null;
		Date fFin = null;
		try {
			fIni = formato.parse(fInicial);
			fFin = formato.parse(fFinal);
		} catch (ParseException e1) {
			System.out.println("Error al parsear las fechas las fechas: " + e1.getMessage());
		}
		restaDias(fIni, fFin);
	}

	/**
	 * Método <b>removeUpdate(DocumentEvent e)</b>.
	 *
	 * @param e: evento del tipo <b>DocumentEvent</b>
	 */
	@Override
	public void removeUpdate(DocumentEvent e) {
	}

	/**
	 * Método <b>changedUpdate(DocumentEvent e)</b>.
	 *
	 * @param e: evento del tipo <b>DocumentEvent</b>
	 */
	@Override
	public void changedUpdate(DocumentEvent e) {
	}

	/**
	 * Método <b>getTfDni()</b>.
	 * Devuelve el texto del campo <i>tfDni</i> 
	 *
	 * @return texto del tfDni
	 */
	public String getTfDni() {
		return "DNI: " + tfDni.getText();
	}

	/**
	 * Método <b>getTfTelefono()</b>.
	 * Devuelve el texto del campo <i>tfTelefono</i> 
	 *
	 * @return texto del tfTelefono
	 */
	public String getTfTelefono() {
		return "Teléfono: " + tfTelefono.getText();
	}

	/**
	 * Método <b>getTfNombre()</b>.
	 * Devuelve el texto del campo <i>tfNombre</i> 
	 * @return texto del tfNombre
	 */
	public String getTfNombre() {
		return "Nombre: " + tfNombre.getText();
	}

	/**
	 * Método <b>getTfApellidos()</b>.
	 * Devuelve el texto del campo <i>tfNombre</i> 
	 * @return texto del tfNombre
	 */
	public String getTfApellidos() {
		return "Apellidos: " + tfApellidos.getText();
	}

	/**
	 * Método <b>getTfFechaEntrada()</b>.
	 * Devuelve el texto del campo <i>tfFechaEntrada</i> 
	 *
	 * @return texto del tfFechaEntrada
	 */
	public String getTfFechaEntrada() {
		return "Fecha de entrada: " + tfFechaEntrada.getText();
	}

	/**
	 * Método <b>getTfFechaSalida()</b>.
	 * Devuelve el texto del campo <i>tfFechaSalida</i> 
	 *
	 * @return texto del tfFechaEntrada
	 */
	public String getTfFechaSalida() {
		return "Fecha de salida: " + tfFechaSalida.getText();
	}

	/**
	 * Método <b>getTfNumEntancias()</b>.
	 * Devuelve el texto del campo <i>tfNumEntancias</i> 
	 * @return texto del tfNumEntancias
	 */
	public String getTfNumEntancias() {
		return "Nº de días de la estancia: " + tfNumEntancias.getText();
	}

	/**
	 * Método <b>setTfNumEntancias(int valor)</b>.
	 * Establece un valor nuevo para el texto del campo <i>tfNumEstancias</i>
	 * @param valor: el nuevo valor para el campo <i>tfNumEstancias</i>
	 */
	public void setTfNumEntancias(int valor) {
		this.tfNumEntancias.setText(Integer.toString(valor));
	}

	/**
	 * Método <b>getNumDias()</b>.
	 * Devuelve el numero total de días de la estancia
	 * @return totalDias
	 */
	public static int getNumDias() {
		return totalDias;
	}

	/**
	 * Método <b>setNumDias(int numDias)</b>.
	 * Establece un valor nuevo para la variable <i>totalDias</i>
	 * @param numDias: el valor a establecer
	 */
	public static void setNumDias(int numDias) {
		Panel2.totalDias = numDias;
	}

}
