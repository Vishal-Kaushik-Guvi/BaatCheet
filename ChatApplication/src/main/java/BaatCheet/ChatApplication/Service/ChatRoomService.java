package BaatCheet.ChatApplication.Service;

import BaatCheet.ChatApplication.Entity.ChatRoom;

public interface ChatRoomService {

    ChatRoom getOrCreateRoom(String roomName);
}
