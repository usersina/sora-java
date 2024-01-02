package application.sora.chat;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URI;
import java.net.URISyntaxException;

import application.sora.constants.Globals;

public class ChatWindow {
    private final String url = "ws://localhost:8888";
    private MyWebSocketClient webSocketClient;
    private ChatWindowGUI gui;

    public ChatWindow() {
        if (Globals.getLoggedUser() == null) {
            throw new RuntimeException("User not found!");
        }

        gui = new ChatWindowGUI();

        try {
            this.webSocketClient = new MyWebSocketClient(new URI(url)) {
                @Override
                public void onMessage(Message message) {
                    if (!message.getUsername().equals(Globals.getLoggedUser().getUsername())) {
                        gui.getChatArea().append(message.getUsername() + ": " + message.getContent() + "\n");
                    }
                }
            };
            this.webSocketClient.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        gui.getMessageField().addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    e.consume(); // Consume the event so the newline isn't added to the JTextArea

                    // Send the message over the WebSocket connection
                    Message message = new Message(
                            Globals.getLoggedUser().getUsername(),
                            gui.getMessageField().getText().trim() //
                    );
                    gui.getChatArea().append("You: " + message.getContent() + "\n");
                    webSocketClient.sendMessage(message);

                    // Clear the message field
                    gui.getMessageField().setText("");
                }
            }
        });
    }

    public void show() {
        gui.getChatFrame().setVisible(true);
    }
}