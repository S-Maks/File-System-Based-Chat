package file.system.based.chat.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    private String content;
    private int toUser;
    private int sendUser;
    private String date = new SimpleDateFormat("yyyy.MM.dd-hh.mm.ss").format(new Date());

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getToUser() {
        return toUser;
    }

    public void setToUser(int toUser) {
        this.toUser = toUser;
    }

    public int getSendUser() {
        return sendUser;
    }

    public void setSendUser(int sendUser) {
        this.sendUser = sendUser;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                ", toUser=" + toUser +
                ", sendUser=" + sendUser +
                ", date='" + date + '\'' +
                '}';
    }
}
