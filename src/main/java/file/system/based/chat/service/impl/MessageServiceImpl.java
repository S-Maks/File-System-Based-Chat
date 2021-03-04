package file.system.based.chat.service.impl;

import file.system.based.chat.model.Message;
import file.system.based.chat.model.User;
import file.system.based.chat.repository.MessageRepository;
import file.system.based.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public void save(Message message) {
        messageRepository.save(message);
    }

    @Override
    public void edit(Message message) {

    }

    @Override
    public void delete(Message message) {

    }
}
