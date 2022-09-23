import java.util.*;
public class StudentEndHandler {
    public Map<String, Integer> getStudentResultsInfo(String fileName) {
        StudentEndReader studentResultsReader = new StudentEndReader();
        List<StudentEffort> studentEfforts = studentResultsReader.mapStringToModel(fileName);

        return getResults(studentEfforts);
    }

    private Map<String, List<StudentEffort>> createStudentAttemptsMap(List<StudentEffort> studentEfforts) {
        Map<String, List<StudentEffort>> studentAttemptsMap = new HashMap<>();

        for (StudentEffort studentEffort : studentEfforts) {
            if (!studentAttemptsMap.containsKey(studentEffort.getLastName())) {
                studentAttemptsMap.put(studentEffort.getLastName(), new ArrayList<>());
            }
        }

        for (StudentEffort studentEffort : studentEfforts) {
            if (studentAttemptsMap.containsKey(studentEffort.getLastName())) {
                studentAttemptsMap.get(studentEffort.getLastName()).add(studentEffort);
            }
        }

        return studentAttemptsMap;
    }

    private Map<String, Map<Integer, List<StudentEffort>>> createStudentTasksAttemptsMap(List<StudentEffort> list) {
        Map<String, Map<Integer, List<StudentEffort>>> studentTasksAttemptsMap = new HashMap<>();

        Map<String, List<StudentEffort>> studentAttemptsMap = createStudentAttemptsMap(list);

        for (Map.Entry<String, List<StudentEffort>> entrySet : studentAttemptsMap.entrySet()) {
            studentTasksAttemptsMap.put(entrySet.getKey(), new HashMap<>());
        }

        for (Map.Entry<String, List<StudentEffort>> entrySet : studentAttemptsMap.entrySet()) {
            for (StudentEffort studentEffort : entrySet.getValue()) {
                studentTasksAttemptsMap.get(entrySet.getKey()).put(studentEffort.getTaskNumber(), new ArrayList<>());
            }
        }

        for (Map.Entry<String, List<StudentEffort>> entrySet : studentAttemptsMap.entrySet()) {
            for (StudentEffort studentEffort : entrySet.getValue()) {
                studentTasksAttemptsMap.get(entrySet.getKey()).get(studentEffort.getTaskNumber()).add(studentEffort);
            }
        }

        return studentTasksAttemptsMap;
    }

    private Map<String, Integer> getResults(List<StudentEffort> list) {
        Map<String, Integer> resultMap = new HashMap<>();

        Map<String, Map<Integer, List<StudentEffort>>> studentTasksAttemptsMap = createStudentTasksAttemptsMap(list);

        for (Map.Entry<String, Map<Integer, List<StudentEffort>>> entrySet : studentTasksAttemptsMap.entrySet()) {
            String student = entrySet.getKey();
            int totalScore = 0;
            for (Map.Entry<Integer, List<StudentEffort>> entry : entrySet.getValue().entrySet()) {
                int maxAttempt = Collections.max(entry.getValue(),
                        Comparator.comparingInt(StudentEffort::getAttemptNumber)).getAttemptNumber();
                int maxScore = Collections.max(entry.getValue(),
                        Comparator.comparingInt(StudentEffort::getScore)).getScore();

                if (maxAttempt < 5) {
                    totalScore += maxScore;
                } else if (maxAttempt < 10) {
                    totalScore += maxScore / 2;
                } else {
                    totalScore += 1;
                }
            }
            resultMap.put(student, totalScore);
        }

        return resultMap;
    }
}
