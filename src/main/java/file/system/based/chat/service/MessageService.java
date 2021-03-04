package file.system.based.chat.service;

import file.system.based.chat.model.Message;

public interface MessageService {
    void save(Message message);

    void edit(Message message);

    void delete(Message message);
}
