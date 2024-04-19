package view;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controlador;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Login extends JFrame {

	/**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private JPanel BodyLayout, panelLeft, panelRight;
    private JLabel logo, labelEmail, labelPassword, labelNoRegister;
    private JTextField inputEmail;
    private JPasswordField inputPassword;
    private JButton toggleButton;
    private Controlador cont;

	/**
	 * Create the frame.
	 */
    
	public Login() {
		InicioSesion(false);
	}
	public Login(boolean oscuro) {
		InicioSesion(oscuro);
	}
	public void InicioSesion(boolean oscuro) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 931, 574);
        BodyLayout = new JPanel();
        BodyLayout.setBackground(new Color(255, 255, 255));
        BodyLayout.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(BodyLayout);
        BodyLayout.setLayout(null);
        ImageIcon icon = new ImageIcon(getClass().getResource("/assets/logo.png"));
        
        // Division Login
        panelLeft = new JPanel();
        panelLeft.setBackground(new Color(0, 128, 255));
        panelLeft.setBounds(0, 0, 416, 539);
        BodyLayout.add(panelLeft);
        panelLeft.setLayout(null);

        panelRight = new JPanel();
        panelRight.setBackground(new Color(255, 255, 255));
        panelRight.setBounds(414, 0, 501, 539);
        BodyLayout.add(panelRight);
        panelRight.setLayout(null);
        
        toggleButton = new JButton(new ImageIcon(getClass().getResource("/assets/icons/nover.png"))); // Establece el icono "nover" por defecto
        toggleButton.setBounds(412, 313, 29, 20);
        toggleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                togglePasswordVisibility();
            }
        });
        toggleButton.setOpaque(false); // Establece el botón como transparente
        toggleButton.setContentAreaFilled(false); // No rellena el área del botón
        toggleButton.setBorderPainted(false);
        panelRight.add(toggleButton);
        
        // Logo centrado
        logo = new JLabel("");
        logo.setBounds(132, 66, 245, 51);
        panelRight.add(logo);
        ImageIcon img = new ImageIcon(icon.getImage().getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_SMOOTH));
        logo.setIcon(img);
        
        labelEmail = new JLabel("Correo electrónico");
        labelEmail.setBounds(74, 171, 123, 14);
        panelRight.add(labelEmail);
        
        inputEmail = new JTextField();
        inputEmail.setBounds(74, 196, 378, 51);
        inputEmail.setColumns(10);
        panelRight.add(inputEmail);

        labelPassword = new JLabel("Contraseña");
        labelPassword.setBounds(74, 270, 123, 14);
        panelRight.add(labelPassword);
        
        inputPassword = new JPasswordField();
        inputPassword.setBounds(74, 295, 378, 51);
        panelRight.add(inputPassword);

        JButton btnLogin = new JButton("Iniciar sesión");
        btnLogin.setBounds(74, 381, 378, 45);
        panelRight.add(btnLogin);
	        
		// Agregar ActionListener al botón btnIniciarSesion
        btnLogin.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Obtener los datos ingresados por el usuario
		        String email = inputEmail.getText();
		        char[] contrasenaChars = inputPassword.getPassword(); // Obtener la contraseña como un array de caracteres
		        String contrasena = new String(contrasenaChars); // Convertir el array de caracteres en un String
		
		        // Llamar al método iniciarSesion del controlador
		        Controlador controlador = new Controlador();
		        boolean sesionIniciada = controlador.iniciarSesion(email, contrasena);
		
		        // Verificar si la sesión se inició correctamente
		        if (sesionIniciada) {
		            System.out.println("Sesión iniciada correctamente");
		            dispose();
		            Main ventanaPrincipal = new Main(oscuro);
		            ventanaPrincipal.setVisible(true);
		        } else {
		            System.out.println("Error: Nombre de usuario o contraseña incorrectos");
		        }
		    }
		});
        
        labelNoRegister = new JLabel("¿No tienes cuenta? Click aquí para");
        labelNoRegister.setBounds(59, 454, 209, 14);
        labelNoRegister.setHorizontalAlignment(SwingConstants.CENTER);
        panelRight.add(labelNoRegister);
        JLabel linkRegister = new JLabel("registrarme");
        linkRegister.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) { // Clic en la palabra remarcada y muestra ventana de registro
        		Register registro = new Register(oscuro);
        		registro.setVisible(true);
        		setVisible(false);
        		
        	}
        });
        linkRegister.setBounds(267, 454, 75, 14);
        linkRegister.setFont(new Font("Tahoma", Font.BOLD, 11));
        linkRegister.setForeground(new Color(0, 128, 255));
        linkRegister.setHorizontalAlignment(SwingConstants.LEFT);
        panelRight.add(linkRegister);
        
        
        if (oscuro) {
        	cambioFondo();
        }
        
    }
	
	private void cambioFondo() {
		panelRight.setBackground(Color.DARK_GRAY);
		labelEmail.setForeground(Color.WHITE);
		labelPassword.setForeground(Color.WHITE);
		labelNoRegister.setForeground(Color.WHITE);
	}
	private void togglePasswordVisibility() {
	    char echoChar = (inputPassword.getEchoChar() == '\u2022') ? '\u0000' : '\u2022'; // Si el echoChar es '•', cambiar a mostrar texto, de lo contrario, ocultar texto
	    inputPassword.setEchoChar(echoChar);
	    toggleButton.setIcon((echoChar == '\u2022') ? new ImageIcon(getClass().getResource("/assets/icons/ver.png")) : new ImageIcon(getClass().getResource("/assets/icons/nover.png"))); // Cambiar el icono según el echoChar
	}

}