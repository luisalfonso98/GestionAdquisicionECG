package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.*;

import Control.ControladorLogin;


public class VentanaLogin extends JFrame{
	/**
	 *Esta es la Ventana de Login de la aplicacion en la que habra dos textfield
	 *correspondiente a nombre de usuario y contraseña, en caso de que la contraseña 
	 *y el nombre de usuario coincidan con el nombre y contraseña de algunos de los usuarios
	 *del archivo users.txt, en el caso en que coincidan los campos se pondran en verde y
	 *pasara a la ventana asignada a su rol, en el caso conrarrio los campos se pondran
	 *en rojo
	 * 
	 * @see ControladorLogin
	 * @see Logo
	 * @see Fondo
	 */
	private static final long serialVersionUID = 1L;
	private JButton acceder;
	public JTextField user;
	private JButton forget;
	private JButton help;
	private JPasswordField pass;
	private Logo logo;
	private String fondo="Resource/Imagenes/fondo.jpeg";
	private Fondo todo;
	private boolean anyMaster=false;
	
	
	public JTextField getUser() {
		return user;
	}

	public JPasswordField getPass() {
		return pass;
	}

	public VentanaLogin(){
		crear();
	}
	
	public void crear(){
		
		int x=(int) (Toolkit.getDefaultToolkit().getScreenSize().width);
		int y=(int) (Toolkit.getDefaultToolkit().getScreenSize().height);
		int porceX=50;
		int porceY=65;
		

		
		this.setMinimumSize(new Dimension((int)(x*0.18),(int)(y*0.40)));
		this.setLocation(x/2-(int) (x*porceX/100)/2,(int) (y/2)-(int) (y*porceY/100)/2);
		this.setSize(x*porceX/100,y*porceY/100);

		this.setExtendedState( this.getExtendedState()|JFrame.MAXIMIZED_BOTH );
		todo=new Fondo(this,fondo);
		todo.setLayout(new BorderLayout());
		ImageIcon img = new ImageIcon("Resource/Imagenes/Logos/logo-cardio-finito100x100.png");
		this.setIconImage(img.getImage());
		this.setName("0");
		logo=new Logo();
		logo.centrado(true);
		user=new JTextField(20);
		pass=new JPasswordField(20);
		JPanel fin=new JPanel();
		acceder=new JButton();
		forget=new JButton("Forgot Password?");
		forget.setFont(new Font("",Font.BOLD,15));
		JLabel id=new JLabel("Usuario");
		id.setFont(new Font("",Font.BOLD,15));
		
		JLabel password=new JLabel("Contrase\u00F1a");
		password.setFont(new Font("",Font.BOLD,15));
		
		id.setForeground(Color.WHITE);
		password.setForeground(Color.WHITE);
		JPanel primer=new JPanel();
		JPanel segun=new JPanel();
		JPanel terce=new JPanel();
		logo.setOpaque(false);
		Thread hilo=new Thread(logo);
		hilo.start();
		
		primer.setLayout(new BorderLayout());
		fin.setLayout(new BorderLayout());
		segun.setLayout(new BorderLayout());
		terce.setLayout(new BorderLayout());
		this.setLayout(new BorderLayout());
		
		primer.setOpaque(false);
		segun.setOpaque(false);
		terce.setOpaque(false);
		fin.setOpaque(false);
		acceder.setBorderPainted(false);
		
		pass.setHorizontalAlignment(SwingConstants.CENTER);
		user.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		forget.setBorderPainted(false);
		forget.setForeground(new Color(105,171,255));
		
		id.setHorizontalAlignment(SwingConstants.CENTER);
		password.setHorizontalAlignment(SwingConstants.CENTER);
		acceder.setHorizontalAlignment(SwingConstants.CENTER);
		acceder.setActionCommand(ControladorLogin.ACCEDER);
		forget.setActionCommand(ControladorLogin.FORGET);
		
		
		acceder.setIcon(new ImageIcon("Resource/Imagenes/enter.png"));
		logo.setLayout(new BorderLayout());
		help=new JButton();
		help.setActionCommand(ControladorLogin.HELP);
		help.setBorderPainted(false);
		help.setIcon(new ImageIcon("Resource/Imagenes/help.png"));
		JPanel ahhh=new JPanel();
		ahhh.setOpaque(false);
		ahhh.setLayout(new BorderLayout());
		
		acceder.setContentAreaFilled(false);
		acceder.setBorderPainted(false);
		help.setContentAreaFilled(false);
		help.setBorderPainted(false);
		forget.setContentAreaFilled(false);
		forget.setBorderPainted(false);
		
		//logo.centrado(true);
		ahhh.add(help,BorderLayout.NORTH);
		logo.add(ahhh,BorderLayout.EAST);
		todo.add(logo,BorderLayout.CENTER);
		primer.add(id,BorderLayout.NORTH);
		primer.add(user,BorderLayout.SOUTH);
		
		JPanel a=new JPanel();
		a.setOpaque(false);
		JPanel aa=new JPanel();
		aa.setOpaque(false);
		aa.setLayout(new FlowLayout());
		a.setLayout(new BorderLayout());
		segun.add(password,BorderLayout.NORTH);
		segun.add(pass,BorderLayout.SOUTH);
		fin.add(primer,BorderLayout.NORTH);
		fin.add(segun,BorderLayout.SOUTH);
		a.add(fin,BorderLayout.NORTH);
		terce.add(acceder,BorderLayout.NORTH);
		terce.add(forget,BorderLayout.SOUTH);
		a.add(terce,BorderLayout.SOUTH);
		
		aa.add(a);
		todo.add(aa, BorderLayout.SOUTH);
		this.add(todo,BorderLayout.CENTER);
		
	}
	public Logo getLogo() {
		return logo;
	}

	public void asignarControlador(ControladorLogin control){
		
		forget.addActionListener(control);
		help.addActionListener(control);
		acceder.addActionListener(control);
		acceder.addKeyListener(control);
		user.addKeyListener(control);
		pass.addKeyListener(control);
		logo.addMouseListener(control);
		this.addWindowListener(control);
		
	}	
	public void ver(){
		anyMaster=false;
		for(int i=0;i<JFrame.getFrames().length;i++) {
			if(JFrame.getFrames()[i] instanceof VentanaLogin) {
				VentanaLogin aux=(VentanaLogin) JFrame.getFrames()[i];
				int j=0;
				if(aux.isShowing())
						while(!anyMaster && j<aux.getWindowListeners().length) {
					if(aux.getWindowListeners()[j] instanceof ControladorLogin) {
						ControladorLogin auxcon=(ControladorLogin) aux.getWindowListeners()[j];
						if(auxcon.isMaster()) {
							anyMaster=true;
						}
					}
					j++;
				}
			}
		}

		if(!anyMaster) {
			this.setVisible(true);
		} else {
			this.dispose();
		}
		
	}
	
	public void loginMaster(ControladorLogin control){
		todo.setVisible(false);
		todo.removeAll();
		int x=(int) (Toolkit.getDefaultToolkit().getScreenSize().width);
		int y=(int) (Toolkit.getDefaultToolkit().getScreenSize().height);
		
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		int porceX=30;
		int porceY=7;
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.setMinimumSize(new Dimension(x*porceX/100,y*porceY/100));
		this.setLocation(x/2-(int) (x*porceX/100)/2,(int) (y/2)-(int) (y*porceY/100)/2);
		this.setSize(x*porceX/100,y*porceY/100);
		todo.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JButton med=new JButton("MEDICO");
		med.setActionCommand(ControladorLogin.MASTERMED);
		med.addActionListener(control);
		JButton tec=new JButton("TECNICO");
		tec.setActionCommand(ControladorLogin.MASTERTEC);
		tec.addActionListener(control);
		JButton adm=new JButton("ADMIN");
		adm.setActionCommand(ControladorLogin.MASTERADM);
		adm.addActionListener(control);
		
		JButton sal=new JButton("SALIR");
		sal.setActionCommand(ControladorLogin.SALIR);
		sal.addActionListener(control);
		
		todo.add(med);
		todo.add(tec);
		todo.add(adm);
		todo.add(sal);
	
		todo.setVisible(true);
	}
	
	public void relocate() {
		int x=(int) (Toolkit.getDefaultToolkit().getScreenSize().width);
		int y=(int) (Toolkit.getDefaultToolkit().getScreenSize().height);
		this.setLocation(x-this.getWidth(),(int) (y)-this.getHeight());
	}

	public JButton getAcceder() {
		return acceder;
	}
}
