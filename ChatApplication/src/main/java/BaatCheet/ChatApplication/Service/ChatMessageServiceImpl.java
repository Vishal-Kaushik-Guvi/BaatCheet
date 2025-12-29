package BaatCheet.ChatApplication.Service;

import BaatCheet.ChatApplication.Entity.ChatMessage;
import BaatCheet.ChatApplication.Repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;

    @Override
    public ChatMessage saveMessage(ChatMessage message) {
        message.setTimestamp(Instant.now());
        return chatMessageRepository.save(message);
    }

    @Override
    public List<ChatMessage> getMessagesByRoomForUser(String roomId, String username) {

        return chatMessageRepository.findByRoomIdOrderByTimestampAsc(roomId)
                .stream()
                .filter(msg -> !msg.getDeletedForUsers().contains(username))
                .map(msg -> {
                    if (msg.isDeletedForEveryone()) {
                        msg.setContent("🚫 This message was deleted");
                    }
                    return msg;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteForMe(String messageId, String username) {

        ChatMessage message = chatMessageRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("Message not found"));

        message.getDeletedForUsers().add(username);
        chatMessageRepository.save(message);
    }

    @Override
    public void deleteForEveryone(String messageId, String username) {

        ChatMessage message = chatMessageRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("Message not found"));

        // Optional security check
        if (!message.getSender().equals(username)) {
            throw new RuntimeException("You can delete only your own messages");
        }

        message.setDeletedForEveryone(true);
        message.setContent(null);

        chatMessageRepository.save(message);
    }
}
