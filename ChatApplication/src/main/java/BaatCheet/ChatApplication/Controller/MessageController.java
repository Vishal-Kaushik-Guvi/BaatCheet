package BaatCheet.ChatApplication.Controller;

import BaatCheet.ChatApplication.Entity.ChatMessage;
import BaatCheet.ChatApplication.Service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/messages")
public class MessageController {

    private final ChatMessageService chatMessageService;

    // 📜 Load messages for a room
    @GetMapping("/{roomId}")
    public List<ChatMessage> getMessages(
            @PathVariable String roomId,
            Authentication authentication) {

        return chatMessageService.getMessagesByRoomForUser(
                roomId,
                authentication.getName()
        );
    }

    // 🗑 Delete for Me
    @DeleteMapping("/{messageId}/me")
    public void deleteForMe(
            @PathVariable String messageId,
            Authentication authentication) {

        chatMessageService.deleteForMe(messageId, authentication.getName());
    }

    // 🗑 Delete for Everyone
    @DeleteMapping("/{messageId}/everyone")
    public void deleteForEveryone(
            @PathVariable String messageId,
            Authentication authentication) {

        chatMessageService.deleteForEveryone(messageId, authentication.getName());
    }
}
