package BaatCheet.ChatApplication.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document(collection = "messages")
public class ChatMessage {

    @Id
    private String id;

    private String sender;
    private String roomId;

    private String content;

    private MessageType type;

    private Instant timestamp = Instant.now();
}
