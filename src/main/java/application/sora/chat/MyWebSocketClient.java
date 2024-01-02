package application.sora.chat;

import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MyWebSocketClient extends WebSocketClient {
    private static final Logger logger = LoggerFactory.getLogger(MyWebSocketClient.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public MyWebSocketClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        logger.info("Connected");
    }

    @Override
    public final void onMessage(String message) {
        try {
            Message msg = objectMapper.readValue(message, Message.class);
            onMessage(msg);
        } catch (Exception e) {
            logger.error("Error parsing message: " + e.getMessage());
        }
    }

    public void onMessage(Message message) {
        logger.info("Received: " + message.getContent() + " from " + message.getUsername());
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        logger.info("Connection closed");
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

    public void sendMessage(Message message) {
        try {
            String msg = objectMapper.writeValueAsString(message);
            this.send(msg);
        } catch (Exception e) {
            logger.error("Error sending message: " + e.getMessage());
        }
    }
}
