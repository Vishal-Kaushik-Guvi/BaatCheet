package BaatCheet.ChatApplication.Service;

import BaatCheet.ChatApplication.Entity.ChatRoom;
import BaatCheet.ChatApplication.Repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    @Override
    public ChatRoom getOrCreateRoom(String roomName) {

        return chatRoomRepository.findByName(roomName)
                .orElseGet(() -> {
                    ChatRoom room = new ChatRoom();
                    room.setName(roomName);
                    room.setParticipants(new HashSet<>());
                    return chatRoomRepository.save(room);
                });
    }
}
