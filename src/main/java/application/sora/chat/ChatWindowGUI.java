// File: src/main/java/application/sora/chat/ChatWindowGUI.java

package application.sora.chat;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import application.sora.constants.Globals;
import application.sora.util.JFrameDraggable;

public class ChatWindowGUI {
    private JTextArea chatArea;
    private JTextArea messageField;
    private JFrame chatFrame;

    public ChatWindowGUI() {
        // Create a draggable JFrame
        chatFrame = new JFrameDraggable() {
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
        messageField = new JTextArea();
        messageField.setLineWrap(true); // Enable line wrapping
        messageField.setWrapStyleWord(true); // Enable word wrapping
        messageField.setPreferredSize(new Dimension(0, 100));

        // Allow the user to scroll through the messageField
        JScrollPane messageScrollPane = new JScrollPane(messageField);

        // Create a close button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chatFrame.dispose();
            }
        });

        // Create a "Logged in as username" label
        JLabel loggedInLabel = new JLabel(
                String.format(
                        "Logged in as %s %s",
                        Globals.getLoggedUser().getFirstName(),
                        Globals.getLoggedUser().getLastName() //
                ) //
        );

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

    public JTextArea getChatArea() {
        return chatArea;
    }

    public JTextArea getMessageField() {
        return messageField;
    }

    public JFrame getChatFrame() {
        return chatFrame;
    }
}