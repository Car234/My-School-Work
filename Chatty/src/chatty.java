import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

/** Carlo Rodriguez
 * 11/15/2017
 * Object Oriented Design
 */
		
/**
 * The login screen after pressing "login"
 */
class LoginDialog extends JDialog {
	private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private boolean succeeded;
    /**
     * Construct a LoginDialog object
     * @param parent: the parent frame 
     * @param bank: the list of accounts registered
     */
    public LoginDialog(Frame parent, userBank bank) {
        super(parent, "Login", true);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
 
        cs.fill = GridBagConstraints.HORIZONTAL;
 
        lbUsername = new JLabel("Username: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbUsername, cs);
 
        tfUsername = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(tfUsername, cs);
 
        lbPassword = new JLabel("Password: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbPassword, cs);
 
        pfPassword = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(pfPassword, cs);
        panel.setBorder(new LineBorder(Color.GRAY));
 
        btnLogin = new JButton("Login");
 
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Login.authenticate(getUsername(), getPassword(), bank)) {
                    JOptionPane.showMessageDialog(LoginDialog.this,
                            "Hi " + getUsername() + "! You have successfully logged in.",
                            "Login",
                            JOptionPane.INFORMATION_MESSAGE);
                    succeeded = true;
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginDialog.this,
                            "Invalid username or password",
                            "Login",
                            JOptionPane.ERROR_MESSAGE);
                    tfUsername.setText("");
                    pfPassword.setText("");
                    succeeded = false;
                }
            }
        });
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JPanel bp = new JPanel();
        bp.add(btnLogin);
        bp.add(btnCancel);
 
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);
 
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }
    /**
     * gets username of logged in user
     * @return: Username of user
     */
    public String getUsername() {
        return tfUsername.getText().trim();
    }
    /**
     * gets password of logged in user
     * @return: Password of user
     */
    public String getPassword() {
        return new String(pfPassword.getPassword());
    }
    /**
     * checks if the succeeded private variable is true 
     * or false
     * @return: the value of succeeded
     */
    public boolean isSucceeded() {
        return succeeded;
    }
}
/**
 * The register screen after pressing "register"
 */
class RegisterDialog extends JDialog {
	private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JPasswordField pf2Password;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JLabel lb2Password;
    private JButton btnRegister;
    private JButton btnCancel;
    /**
     * Register Constructor 
     * @param parent: the parent frame 
     * @param bank: the bank of registered accounts
     */
    public RegisterDialog(Frame parent, userBank bank) {
        super(parent, "Register", true);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
 
        cs.fill = GridBagConstraints.HORIZONTAL;
 
        lbUsername = new JLabel("Enter a Username: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbUsername, cs);
 
        tfUsername = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(tfUsername, cs);
 
        lbPassword = new JLabel("Enter a Password: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbPassword, cs);
 
        pfPassword = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(pfPassword, cs);
        
        lb2Password = new JLabel("Confirm Password: ");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        panel.add(lb2Password, cs);
        
        pf2Password = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        panel.add(pf2Password, cs);       
        panel.setBorder(new LineBorder(Color.GRAY));
 
        btnRegister = new JButton("Register");
 
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            		if (getUsername().equals("")) {
            			JOptionPane.showMessageDialog(RegisterDialog.this,
            					"Please enter a username.",
            					"Register",
            					JOptionPane.INFORMATION_MESSAGE);
            			tfUsername.setText("");
            			pfPassword.setText("");
            			pf2Password.setText("");	
            		}
            		else if (bank.getMap().containsKey(getUsername())) {
            			JOptionPane.showMessageDialog(RegisterDialog.this,
            					"That username is already taken.",
            					"Register",
            					JOptionPane.INFORMATION_MESSAGE);
            			tfUsername.setText("");
            			pfPassword.setText("");
            			pf2Password.setText("");	
            		}
            		else if (getPassword().equals(getPassword2())) {
            			Register.register(getUsername(), getPassword(), bank);
            			JOptionPane.showMessageDialog(RegisterDialog.this,
            					"Hi " + getUsername() + "! You have successfully registered.",
            					"Register",
            					JOptionPane.INFORMATION_MESSAGE);
            			dispose();
            		}
            		else {
            			JOptionPane.showMessageDialog(RegisterDialog.this,
            					"Password could not be verified.",
            					"Register",
            					JOptionPane.ERROR_MESSAGE);
            			tfUsername.setText("");
            			pfPassword.setText("");
            			pf2Password.setText(""); 
            		}
            	}
        });
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JPanel bp = new JPanel();
        bp.add(btnRegister);
        bp.add(btnCancel);
 
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);
 
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }
    /**
     * gets username of entered username
     * @return the username entered
     */
    public String getUsername() {
        return tfUsername.getText().trim();
    }
    /**
     * gets the first password entered
     * @return: the first password
     */
    public String getPassword() {
        return new String(pfPassword.getPassword());
    }
    /**
     * gets the second password for verification
     * @return: the second password entered
     */
    public String getPassword2() {
    		return new String(pf2Password.getPassword());
    }	
}
/**
 * The chatty frame 
 */
class chattyFrame extends Frame {
	/**
	 * creates the parent frame 
	 * @return: the parent frame
	 */
	public JFrame welcomeFrame() {
		userBank bank = new userBank();
		final JFrame frame = new JFrame("Welcome to Chatty!");
		final JButton btnLogin = new JButton("Login");
		final JButton btnRegister = new JButton("Register");
		final JButton btnLogout = new JButton("Logout");
		JTextArea chatBox = new JTextArea(13, 50);
		chatBox.setVisible(false);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 75);
		frame.setLayout(new FlowLayout());
		frame.getContentPane().add(btnLogin);
		frame.getContentPane().add(btnRegister);
		frame.setVisible(true);
		
		btnLogin.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        LoginDialog loginDlg = new LoginDialog(frame, bank);
                        loginDlg.setVisible(true);
                        if (loginDlg.isSucceeded()){
                        		chatFrame(frame, loginDlg, btnRegister, btnLogin, btnLogout, chatBox);
                        }
                    }
                });
		
		btnRegister.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RegisterDialog registerDlg = new RegisterDialog(frame, bank);
						registerDlg.setVisible(true);
					}
				});
		return frame;
	}
	/**
	 * Creates the "chat" frame
	 * @param frame: the parent frame
	 * @param loginDlg: LoginDialog Object, to see who is logged in
	 * @param btnRegister: Register button, set visibility
	 * @param btnLogin: Login button, set visibility
	 * @param btnLogout: Logout button, create actionListener
	 * @param chatBox: to show what all users are typing in the group chat
	 */
	public void chatFrame(JFrame frame, LoginDialog loginDlg, 
			             JButton btnRegister, JButton btnLogin, 
			             JButton btnLogout, JTextArea chatBox) {
		frame.setLayout(new BorderLayout());
		JPanel southernPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JTextField tfsend = new JTextField(30);
		JButton btnsend = new JButton("Send");
		JScrollPane scrollPane = new JScrollPane(chatBox);
		
		chatBox.setEditable(false);
		chatBox.setLineWrap(true);
		chatBox.setVisible(true);
			
		southernPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		southernPanel.add(tfsend);
		southernPanel.add(btnsend);
		southernPanel.add(Box.createRigidArea(new Dimension(185,0)));
		southernPanel.add(btnLogout);
		
		centerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		centerPanel.add(scrollPane);
		
		frame.getContentPane().add(centerPanel);
		frame.add(southernPanel, BorderLayout.SOUTH);
		frame.setTitle("Welcome " + loginDlg.getUsername() + "!");
		frame.setSize(750, 300);
		btnRegister.setVisible(false);
		btnLogin.setVisible(false);
		
		btnsend.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (!tfsend.getText().trim().equals("")) {
							chatBox.append(loginDlg.getUsername() + ": " + tfsend.getText() + "\n");
							tfsend.setText("");
						}
					}
				});
		
		btnLogout.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.setTitle("Welcome to Chatty!");
						frame.setSize(300, 75);
						btnLogin.setVisible(true);
						btnRegister.setVisible(true);
						southernPanel.setVisible(false);
						centerPanel.setVisible(false);
					}
				});
	}
}

/**
 * Verifies if Login is successful
 */
class Login {
	/**
	 * Authenticates the validity of information given
	 * @param username: username entered
	 * @param password: password entered
	 * @param bank: the list of users registered
	 * @return: A boolean value whether login is a success
	 * or not
	 */
	public static boolean authenticate(String username, String password, userBank bank) {
		Map<String, String> map; 
		map = bank.getMap();
		if (map.containsKey(username)) {
			if (map.get(username).equals(password)) 
				return true;
			else 
				return false;
		}
		else
			return false;
	}
}
/**
 * Register class to register users
 */
class Register {
	/**
	 * inputs a username and a password into the bank
	 * @param username: the username entered
	 * @param password: the password entered
	 * @param bank: the bank of usernames
	 */
	public static void register(String username, String password, userBank bank) {
		bank.addUser(username, password);
	}
}
/**
 * userBank class that holds the list of registered users
 */
class userBank {
	private Map<String, String> map;
	/**
	 * instantiate a HashMap to hold usernames and passwords
	 */
	public userBank() {
		map = new HashMap<String, String>();
	}
	/**
	 * adds a user to the map
	 * @param username: the username entered
	 * @param password: the password entered
	 */
	public void addUser(String username, String password) {
		map.put(username, password);
	}
	/**
	 * gets the map of users in the bank
	 * @return: the map 
	 */
	public Map<String, String> getMap() {
		return map;
	}
}

/**
 * The main class of chatty
 */
public class chatty {
	/**
	 * creates a parent chat frame
	 * @param args
	 */
	public static void main(String[] args) {
		chattyFrame frame = new chattyFrame();
		frame.welcomeFrame();		
	}
}

