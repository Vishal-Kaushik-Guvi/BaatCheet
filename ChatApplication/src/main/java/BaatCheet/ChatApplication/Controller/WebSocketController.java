package BaatCheet.ChatApplication.Controller;

import BaatCheet.ChatApplication.Entity.ChatMessage;
import BaatCheet.ChatApplication.Service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class WebSocketController {

    private final ChatMessageService chatMessageService;

    // 📩 Send Message
    @MessageMapping("/chat.send/{roomId}")
    @SendTo("/topic/room/{roomId}")
    public ChatMessage sendMessage(
            @Payload ChatMessage message) {

        return chatMessageService.saveMessage(message);
    }

    @MessageMapping("/chat.delete")
    @SendTo("/topic/room")
    public ChatMessage deleteMessage(@Payload ChatMessage message) {
        return message;
    }
    
}
