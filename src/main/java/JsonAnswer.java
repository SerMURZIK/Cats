import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class JsonAnswer {
    private String message;
    private final File file = Main.file;

    public JsonAnswer(String message) {
        this.message = message;
    }

    public void saveJson() {
        try (OutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(message.getBytes());
        } catch (Exception e) {
            System.err.println("Save json err");
            System.out.println(e.getMessage());
        }
    }

    public int loadJson() {
        int i = 0;
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Vote> list = Arrays.asList(mapper.readValue(file, Vote[].class));
            for (int j = 0; j < list.size(); j++) {
                Vote vote = list.get(j);
                if (vote.upvotes == null || vote.upvotes == 0) {
                    i++;
                }
            }
        } catch (Exception e) {
            System.err.println("Load json err");
            System.out.println(e.getMessage());
        }
        return i;
    }
}