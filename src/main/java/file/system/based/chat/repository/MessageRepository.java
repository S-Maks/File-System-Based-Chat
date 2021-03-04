package file.system.based.chat.repository;

import file.system.based.chat.model.Message;

public interface MessageRepository {
    void save(Message message);

    void edit(Message message);

    void delete(Message message);
}
