package BaatCheet.ChatApplication.Repository;

import BaatCheet.ChatApplication.Entity.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {

    List<ChatMessage> findByRoomIdOrderByTimestampAsc(String roomId);
}
