import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Client extends JFrame {

    Socket socket;
    BufferedReader br;
    PrintWriter out;

    // UI Components
    private JLabel heading = new JLabel("Client Chat Area");
    private JTextArea textArea = new JTextArea();
    private JTextField textField = new JTextField();
    private JButton sendButton = new JButton("Send");
    private Font font = new Font("Roboto", Font.PLAIN, 16);

    public Client() {
        try {
            System.out.println("Sending request to server...");
            socket = new Socket("127.0.0.1", 7777);
            System.out.println("Connection established.");

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true); // auto-flush enabled

            createGUI();
            handleEvents();
            startReading();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createGUI() {
        // Frame settings
        setTitle("Client Messenger");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Layout
        setLayout(new BorderLayout());

        // Heading
        heading.setFont(new Font("Roboto", Font.BOLD, 20));
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(heading, BorderLayout.NORTH);

        // Text area (chat history)
        textArea.setFont(font);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        // Bottom panel for input and send button
        JPanel inputPanel = new JPanel(new BorderLayout());
        textField.setFont(font);
        sendButton.setFont(font);

        inputPanel.add(textField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void handleEvents() {
        // Send message on button click or Enter key
        sendButton.addActionListener(e -> sendMessage());
        textField.addActionListener(e -> sendMessage());
    }

    private void sendMessage() {
        String message = textField.getText().trim();
        if (!message.isEmpty()) {
            textArea.append("Me: " + message + "\n");
            out.println(message);
            textField.setText("");
            if (message.equalsIgnoreCase("exit")) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                dispose(); // Close GUI
            }
        }
    }

    // Thread to read data from the server
    public void startReading() {
        Runnable r1 = () -> {
            System.out.println("Reader started...");
            try {
                while (!socket.isClosed()) {
                    String msg = br.readLine();
                    if (msg == null || msg.equalsIgnoreCase("exit")) {
                        System.out.println("Server terminated the chat.");
                        JOptionPane.showMessageDialog(this, "Server terminated the chat.", "Disconnected", JOptionPane.INFORMATION_MESSAGE);
                        socket.close();
                        break;
                    }
                    textArea.append("Server: " + msg + "\n");
                }
            } catch (Exception e) {
                System.out.println("Connection closed.");
            }
        };
        new Thread(r1).start();
    }

    public static void main(String[] args) {
        System.out.println("This is the client... starting client...");
        new Client();
    }
}
