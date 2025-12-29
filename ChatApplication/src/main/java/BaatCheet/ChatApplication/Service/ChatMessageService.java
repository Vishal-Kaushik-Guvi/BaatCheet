package BaatCheet.ChatApplication.Service;

import BaatCheet.ChatApplication.Entity.ChatMessage;

import java.util.List;

public interface ChatMessageService {

    ChatMessage saveMessage(ChatMessage message);

    List<ChatMessage> getMessagesByRoomForUser(String roomId, String username);

    void deleteForMe(String messageId, String username);

    void deleteForEveryone(String messageId, String username);
}
