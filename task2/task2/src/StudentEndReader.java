import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class StudentEndReader {

    private List<String> getDataFromFile(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            return bufferedReader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new IllegalArgumentException("something goes wrong");
    }

    public List<StudentEffort> mapStringToModel(String fileName) {
        List<String> dataFromFile = getDataFromFile(fileName);

        return dataFromFile.stream()
                .map(str -> {
                    String[] split = str.split(" ");
                    String lastName = split[0];
                    int taskNumber = Integer.parseInt(split[1]);
                    int attemptNumber = Integer.parseInt(split[2]);
                    int score = Integer.parseInt(split[3]);

                    return new StudentEffort(lastName, taskNumber, attemptNumber, score);
                }).collect(Collectors.toList());
    }
}
