import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

import javax.swing.SwingConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import com.google.gson.Gson;

public class MainWindow {

	private JFrame frmChatApplication;
	private JTextField hostnameField;
	private JTextField portField;
	private JTextField usernameField;
	private JTextArea messageField;
	private JTextArea logField;
	private JLabel currentDateTimeLabel;
	private JLabel lblUsername;
	private JLabel lblNewLabel;
	private JLabel connectionStatusLabel;
	private JLabel lblHost;
	private JLabel lblPort;
	private JButton connectButton;
	private JButton disconnectButton;
	private JButton sendMessageButton;
	private JButton saveLogButton;
	private JButton clearLogButton;
	private JScrollPane messageFieldScrollPane;
	private JScrollPane logFieldScrollPane;
	private JFileChooser logFileDialog;
	private JLabel connectedUsersLabel;
	
	private boolean connectionLock;		//	To avoid accidentally duplicate connect or disconnect
	private Socket clientSocket;
	private String username;
	
	private DataOutputStream os;
	private DataInputStream is;
	
	DataRecievedEvent logUpdater;
	ConnectionFlagWatcher cfw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmChatApplication.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public MainWindow() throws Exception {
		initialize();
	}
	
	/**
	 * Builds the GUI
	 */
	public void buildGUI() {
		frmChatApplication = new JFrame();
		frmChatApplication.setResizable(false);
		frmChatApplication.setTitle("NAD Chat Application");
		frmChatApplication.getContentPane().setFont(new Font("Dialog", Font.BOLD, 18));
		frmChatApplication.setFont(new Font("URW Bookman L", Font.BOLD, 18));
		frmChatApplication.setBounds(100, 100, 840, 725);
		frmChatApplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChatApplication.getContentPane().setLayout(null);
		
		currentDateTimeLabel = new JLabel("");
		currentDateTimeLabel.setBounds(12, 649, 243, 15);
		frmChatApplication.getContentPane().add(currentDateTimeLabel);
		
		lblNewLabel = new JLabel("Connection status:");
		lblNewLabel.setBounds(579, 72, 135, 15);
		frmChatApplication.getContentPane().add(lblNewLabel);
		
		connectionStatusLabel = new JLabel("Disconnected");
		connectionStatusLabel.setForeground(Color.RED);
		connectionStatusLabel.setBounds(726, 72, 102, 15);
		frmChatApplication.getContentPane().add(connectionStatusLabel);
		
		lblHost = new JLabel("Host:");
		lblHost.setBounds(23, 28, 38, 15);
		frmChatApplication.getContentPane().add(lblHost);
		
		hostnameField = new JTextField();
		hostnameField.setText("127.0.0.1");
		hostnameField.setHorizontalAlignment(SwingConstants.CENTER);
		hostnameField.setFont(new Font("Dialog", Font.BOLD, 18));
		hostnameField.setBounds(92, 21, 469, 25);
		frmChatApplication.getContentPane().add(hostnameField);
		hostnameField.setColumns(10);
		
		lblPort = new JLabel("Port:");
		lblPort.setBounds(23, 58, 70, 15);
		frmChatApplication.getContentPane().add(lblPort);
		
		portField = new JTextField();
		portField.setText("45012");
		portField.setHorizontalAlignment(SwingConstants.CENTER);
		portField.setFont(new Font("Dialog", Font.BOLD, 18));
		portField.setBounds(92, 52, 469, 24);
		frmChatApplication.getContentPane().add(portField);
		portField.setColumns(10);
		
		lblUsername = new JLabel("Username:");
		lblUsername.setBounds(0, 88, 93, 15);
		frmChatApplication.getContentPane().add(lblUsername);
		
		usernameField = new JTextField();
		usernameField.setText("Someone");
		usernameField.setHorizontalAlignment(SwingConstants.CENTER);
		usernameField.setFont(new Font("Dialog", Font.BOLD, 18));
		usernameField.setBounds(92, 86, 469, 25);
		frmChatApplication.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		connectButton = new JButton("Connect");
		connectButton.setBounds(583, 12, 222, 25);
		frmChatApplication.getContentPane().add(connectButton);
		
		disconnectButton = new JButton("Disconnect");
		disconnectButton.setBounds(583, 35, 222, 25);
		frmChatApplication.getContentPane().add(disconnectButton);
		
		sendMessageButton = new JButton("Send message");
		sendMessageButton.setEnabled(false);
		sendMessageButton.setBounds(12, 248, 816, 25);
		frmChatApplication.getContentPane().add(sendMessageButton);
		
		saveLogButton = new JButton("Save log");
		saveLogButton.setEnabled(false);
		saveLogButton.setBounds(10, 575, 818, 25);
		frmChatApplication.getContentPane().add(saveLogButton);
		
		clearLogButton = new JButton("Clear log");
		clearLogButton.setEnabled(false);
		clearLogButton.setBounds(12, 612, 816, 25);
		frmChatApplication.getContentPane().add(clearLogButton);
		
		messageFieldScrollPane = new JScrollPane();
		messageFieldScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		messageFieldScrollPane.setBounds(10, 115, 818, 109);
		frmChatApplication.getContentPane().add(messageFieldScrollPane);
		
		messageField = new JTextArea();
		messageField.setEnabled(false);
		messageField.setLineWrap(true);
		messageField.setFont(new Font("Dialog", Font.BOLD, 18));
		messageFieldScrollPane.setViewportView(messageField);
		
		logFieldScrollPane = new JScrollPane();
		logFieldScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		logFieldScrollPane.setBounds(10, 295, 818, 261);
		frmChatApplication.getContentPane().add(logFieldScrollPane);
		
		logField = new JTextArea();
		logField.setLineWrap(true);
		logField.setFont(new Font("Dialog", Font.BOLD, 18));
		logField.setEditable(false);
		logFieldScrollPane.setViewportView(logField);
		
		JLabel lblConnectedUsers = new JLabel("Connected users:");
		lblConnectedUsers.setBounds(464, 649, 125, 15);
		frmChatApplication.getContentPane().add(lblConnectedUsers);
		
		connectedUsersLabel = new JLabel("");
		connectedUsersLabel.setBounds(630, 649, 70, 15);
		frmChatApplication.getContentPane().add(connectedUsersLabel);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		buildGUI();
		connectionLock = false;
		cfw = new ConnectionFlagWatcher(false);
		cfw.attachCallback(new Callback() {
			@Override
			public void execute(Message message) {
				connectionStatusLabel.setText("Connected");
				connectionStatusLabel.setForeground(Color.GREEN);
				messageField.setEnabled(true);
				sendMessageButton.setEnabled(true);
				saveLogButton.setEnabled(true);
				clearLogButton.setEnabled(true);
			}
		}, new Callback() {
			@Override
			public void execute(Message message) {
				connectionStatusLabel.setText("Disconnected");
				connectionStatusLabel.setForeground(Color.RED);
				messageField.setEnabled(false);
				sendMessageButton.setEnabled(false);
				saveLogButton.setEnabled(false);
				clearLogButton.setEnabled(false);
			}
		});
		
		new Thread(()-> {
			while (true) {
				String time = LocalDateTime.now().atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
				currentDateTimeLabel.setText(time);
			}
		}).start();
		
		hostnameField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				hostnameField.setBackground(Color.WHITE);
			}
		});
		
		portField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				portField.setBackground(Color.WHITE);
			}
		});
		
		usernameField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				usernameField.setBackground(Color.WHITE);
			}
		});
		
		connectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (connectionLock) {
					//	If client already connected, show warning message and do nothing
					JOptionPane.showMessageDialog(frmChatApplication, "You're already connected to a host.\nPlease disconnect first then try again.", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else {
					String hostname = hostnameField.getText().trim();
					int port = -1;
					username = usernameField.getText().trim();
					String usernamePattern = "[A-Za-z0-9_]{6,20}";	//	Username pattern
					
					boolean isValid = true;	//	Validation flag
					String validationError = "";	//	Error message accumulation
					
					//	The following section has been commented out because 
					//	java.net.Socket doesn't work well with protocols, and java.net.URL
					//	Will throw a MalformedURLException if no protocol was supplied
					
					/*
					//	Validate the entered hostname and check if it's a valid URL
					try {
						new URL(hostname);
					}
					catch (MalformedURLException e) {
						hostnameField.setBackground(new Color(255, 0, 127));	//	Change field background color to pink to notify user
						validationError += "Hostname doesn't represent a correct URL\n";
						isValid = false;
					}
					*/
					
					//	Instead of a full-blown check, we'll just check if the hostname field isn't empty
					if (hostname.length() == 0) {
						hostnameField.setBackground(new Color(255, 0, 127));	//	Change field background color to pink to notify user
						validationError += "Hostname cannot be empty\n";
						isValid = false;
					}
					
					//	Parse the port and check it's validity
					try {
						port = Integer.parseInt(portField.getText().trim());
					}
					catch (NumberFormatException e) {
						portField.setBackground(new Color(255, 0, 127));
						validationError += "The value in port field must be an integer\n";
						isValid = false;
					}
					
					//	A port cannot be negative
					if (portField.getText().startsWith("-")) {
						portField.setBackground(new Color(255, 0, 127));
						validationError += "The value in port field must be a positive integer\n";
						isValid = false;
					}
					
					if (!Pattern.matches(usernamePattern, username)) {
						usernameField.setBackground(new Color(255, 0, 127));
						validationError += "The username must contain only alphanumeric characters (A-Z and 0-9) or underscore (_) and can be between 6 and 20 characters in length\n";
						isValid = false;
					}
					
					if (!isValid) {
						String validationMessage = String.format("The connection couldn't be established due to the following validation errors:\n%s", validationError);
						JOptionPane.showMessageDialog(frmChatApplication, validationMessage, "Validation error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					
					//	Let's try to establish the connection
					try {
						clientSocket = new Socket(hostname, port);
						os = new DataOutputStream(clientSocket.getOutputStream());
						is = new DataInputStream(clientSocket.getInputStream());
						logUpdater = new DataRecievedEvent(is);
						cfw.setFlag(true);
							
						logUpdater.attachCallback(new Callback() {
							@Override
							public void execute(Message message) {
								if (message != null) {
									if (message.getType() == Message.NORMAL_MESSAGE) {
										DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
										Instant timestamp = Instant.ofEpochMilli(Double.valueOf(message.getProperty("timestamp").toString()).longValue());
										String time = LocalDateTime.ofInstant(timestamp, ZoneOffset.UTC).format(pattern);
										String result = String.format("[INFO] %s [%s] Says: %s\n", time, message.getProperty("sender"), message.getProperty("content"));
										logField.append(result);
									}
									else if (message.getType() == Message.USERS_COUNT_MESSAGE) {
										int result = Double.valueOf(message.getProperty("count").toString()).intValue();			
										connectedUsersLabel.setText("" + result);
									}
								}
							}
						}, new Callback() {
							@Override
							public void execute(Message message) {
								JOptionPane.showMessageDialog(null, "The connection has been lost.\nIt's believed that the server may have came down.", "Error", JOptionPane.ERROR_MESSAGE);
								connectionLock = false;
								cfw.setFlag(false);
							}
						});
						
						new Thread(logUpdater).start();
						new Thread(cfw).start();
						connectionLock = true;	//	We're connected now
						connectionStatusLabel.setText("Connected");
						connectionStatusLabel.setForeground(Color.GREEN);
					}
					catch (Exception e) {
						String errorMessage = String.format("The connection to %s:%d couldn't be established due to the following error:\n%s", hostname, port, e.getMessage());
						JOptionPane.showMessageDialog(frmChatApplication, errorMessage, "Fatal error", JOptionPane.ERROR_MESSAGE);
						connectionLock = false;	// But if something happens, we will release the lock
						connectionStatusLabel.setText("Disonnected");
						connectionStatusLabel.setForeground(Color.RED);
						username = null;
						logUpdater = null;
						cfw.setFlag(false);
						try {
							is.close();
							os.close();
						}
						catch (IOException ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		});
		
		disconnectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//	If the client isn't connected, then how can he disconnect ?!!!
				if (!connectionLock) {
					JOptionPane.showMessageDialog(frmChatApplication, "You're not connected to any host yet.", "Warning", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				//	Let's try closing the connection
				try {
					clientSocket.close();
					connectionLock = false;	//	We're Successfully disconnected
					connectionStatusLabel.setText("Disonnected");
					connectionStatusLabel.setForeground(Color.RED);
					cfw.setFlag(false);
				}
				catch (IOException e) {
					String errorMessage = String.format("The following error has occured while closing the connection:\n%s", e.getMessage());
					JOptionPane.showMessageDialog(frmChatApplication, errorMessage, "Fatal error", JOptionPane.ERROR_MESSAGE);
					connectionLock = true;	//	But if something wrong happens, we set the lock
					connectionStatusLabel.setText("Connected");
					connectionStatusLabel.setForeground(Color.GREEN);
					cfw.setFlag(true);
				}
			}
		});
		
		sendMessageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String message = messageField.getText().trim();
				if (message.length() > 1024) {
					JOptionPane.showMessageDialog(frmChatApplication, "Message length cannot exceed 1024 characters", "Send error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Message outgoing = new Message();
				outgoing.setProperty("sender", username);
				outgoing.setProperty("content", message);
				outgoing.setProperty("timestamp", LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
				outgoing.setType(Message.NORMAL_MESSAGE);
				String serializedMessage = new Gson().toJson(outgoing, Message.class);
				//boolean flag = false;
				
				try {
					os.write(serializedMessage.getBytes());
					os.flush();
					//flag = true;
				}
				catch (IOException e) {
					e.printStackTrace();
					//flag = false;
				}
				
				/*
				if (flag) {
					DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
					String time = LocalDateTime.now().atOffset(ZoneOffset.UTC).format(pattern);
					String log;
					log = String.	
		JLabel lblConnectedUsers = new JLabel("Connected users:");
		lblConnectedUsers.setBounds(438, 647, 	
		JLabel connectedUsersLabel = new JLabel("");
		connectedUsersLabel.setBounds(579, 647, 70, 15);
		frmChatApplication.getContentPane().add(connectedUsersLabel);
	0, 15);
		frmChatApplication.getContentPane().add(connectedUsersLabel);
	format("[INFO] %s Serialized message is: %s\n", time, serializedMessage);
					logField.append(log);
				}
				*/
			}
		});
		
		saveLogButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				logFileDialog = new JFileChooser();
				int choice = logFileDialog.showSaveDialog(null);
				if (choice == JFileChooser.APPROVE_OPTION) {
					File file = logFileDialog.getSelectedFile();
					boolean save = true;
					if (file.exists()) {
						int option = JOptionPane.showConfirmDialog(null, "The file: " + file.getPath() + " alread exists on the hard disk.\nWould you like to overwrite it ?", "Info", JOptionPane.YES_NO_OPTION);
						if (option == JOptionPane.NO_OPTION) {
							save = false;
						}
					}
					
					if (save) {
						try {
							FileWriter fw = new FileWriter(file);
							BufferedWriter bw = new BufferedWriter(fw);
							bw.write(logField.getText());
							bw.flush();
							bw.close();
							fw.close();
						}
						catch (IOException e) {
							JOptionPane.showMessageDialog(null, "Couldn't write log file to disk.\nMaybe you should check the permissions or free some space on the disk.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					
					//JOptionPane.showMessageDialog(null, "File path is: " + file.getPath());
				}
			}
		});
		
		clearLogButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				logField.setText(null);
			}
		});
		
	}
}
