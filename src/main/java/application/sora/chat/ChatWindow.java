// File: src/main/java/application/sora/chat/ChatWindow.java

package application.sora.chat;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import application.hibernate.entities.User;
import application.hibernate.services.UserService;
import application.hibernate.services.UserServiceImpl;
import application.sora.constants.Globals;
import application.sora.util.JFrameDraggable;

public class ChatWindow {
    private final String url = "ws://localhost:8888";
    private MyWebSocketClient webSocketClient;

    private UserService userService;
    private User currentUser;

    private JTextArea chatArea; // Assuming you have a JTextArea for chat messages

    public ChatWindow() {
        this.userService = new UserServiceImpl();
        this.currentUser = userService.getUserById(Globals.getLoggedUserId());

        if (currentUser == null) {
            throw new RuntimeException("User not found!");
        }

        try {
            this.webSocketClient = new MyWebSocketClient(new URI(url)) {
                @Override
                public void onMessage(String message) {
                    chatArea.append(message + "\n");
                }
            };
            this.webSocketClient.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void show() {
        // Create a draggable JFrame
        JFrame chatFrame = new JFrameDraggable() {
            private static final long serialVersionUID = 1L;

            public void paint(java.awt.Graphics g) {
                super.paint(g);
            };
        };
        chatFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JTextArea for the chat messages
        chatArea = new JTextArea();
        chatArea.setEditable(false); // So users can't edit previous messages
        chatArea.setLineWrap(true); // Enable line wrapping
        chatArea.setWrapStyleWord(true); // Enable word wrapping

        // Create a JTextArea for the user to enter messages
        JTextArea messageField = new JTextArea();
        messageField.setLineWrap(true); // Enable line wrapping
        messageField.setWrapStyleWord(true); // Enable word wrapping
        messageField.setPreferredSize(new Dimension(0, 100));

        // Allow the user to scroll through the messageField
        JScrollPane messageScrollPane = new JScrollPane(messageField);

        messageField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    e.consume(); // Consume the event so the newline isn't added to the JTextArea

                    // Send the message over the WebSocket connection
                    String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                    String message = String.format(
                            "%s: %s (%s)",
                            currentUser.getFirstName() + " " + currentUser.getLastName(),
                            messageField.getText().trim(),
                            date);
                    chatArea.append(message + "\n");
                    webSocketClient.send(message);

                    // Clear the messageField
                    messageField.setText("");
                }
            }
        });

        // Create a close button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chatFrame.dispose();
            }
        });

        // Create a "Logged in as username" label
        JLabel loggedInLabel = new JLabel(String.format("Logged in as %s", currentUser.getUsername()));

        // Create a menu bar with a BorderLayout
        JPanel menuBar = new JPanel(new BorderLayout());
        menuBar.add(loggedInLabel, BorderLayout.LINE_START);
        menuBar.add(closeButton, BorderLayout.LINE_END);

        // Create a main panel with a BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Add the chatArea, messageField, and menuBar to the main panel
        mainPanel.add(new JScrollPane(chatArea), BorderLayout.CENTER);
        mainPanel.add(messageScrollPane, BorderLayout.PAGE_END);
        mainPanel.add(menuBar, BorderLayout.PAGE_START);

        // Set the size of the JFrame and make it always on top
        chatFrame.setSize(500, 800);
        chatFrame.setAlwaysOnTop(true);
        chatFrame.setVisible(true);
        chatFrame.add(mainPanel);
    }
}