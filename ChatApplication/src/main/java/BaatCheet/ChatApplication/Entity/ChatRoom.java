package BaatCheet.ChatApplication.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Document(collection = "chat_rooms")
public class ChatRoom {

    @Id
    private String id;

    private String name;

    private Set<String> participants;
}
