package file.system.based.chat.repository;

import file.system.based.chat.model.Message;

import java.util.List;

public interface MessageRepository {
    void save(Message message);

    void edit(Message message);

    void delete(Message message);

    List<Message> findAllDialog(int sendUser, int toUser);
}
