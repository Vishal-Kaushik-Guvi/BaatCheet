package BaatCheet.ChatApplication.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Set;
import java.util.HashSet;

@Document(collection = "chat_messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessage {

    @Id
    private String id;

    private String roomId;
    private String sender;

    private String content;

    private MessageType type; // TEXT, IMAGE, AUDIO

    private Instant timestamp;

    // 🔥 Delete for everyone
    private boolean deletedForEveryone;

    // 🔥 Delete for me (user-specific)
    private Set<String> deletedForUsers = new HashSet<>();
}
