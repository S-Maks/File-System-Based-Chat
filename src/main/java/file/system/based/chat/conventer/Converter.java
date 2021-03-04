package file.system.based.chat.conventer;

import com.google.gson.Gson;
import file.system.based.chat.model.Message;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Converter {
    private final static String baseFile = "message.json";

    public static void toJSON(Message message) {
        Gson gson = new Gson();
        String json = gson.toJson(message);
        try (FileWriter fileWriter = new FileWriter(baseFile, true)){
            fileWriter.write(json);

            fileWriter.flush();

            fileWriter.write(json);

            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void toJSON(List<Message> messageList){
        Gson gson = new Gson();
        try(FileWriter fileWriter = new FileWriter(baseFile)){
            String json = gson.toJson(messageList);
            fileWriter.write(json);
            fileWriter.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
