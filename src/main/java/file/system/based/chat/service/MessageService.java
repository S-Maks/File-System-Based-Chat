package file.system.based.chat.service;

import file.system.based.chat.model.Message;

import java.util.List;

public interface MessageService {
    void save(Message message);

    void edit(Message message);

    void delete(Message message);

    List<Message> findAllDialog(int sendUser, int toUser);
}
