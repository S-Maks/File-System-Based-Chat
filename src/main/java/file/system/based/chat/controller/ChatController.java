package file.system.based.chat.controller;

import file.system.based.chat.model.Message;
import file.system.based.chat.model.User;
import file.system.based.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private MessageService messageService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public Message sendMessage(@Payload Message message, Authentication authentication) {
        User user = (User)authentication.getPrincipal();
        message.setSendUser((int) user.getId());
        messageService.save(message);
        System.out.println(message);
        return message;
    }
}
