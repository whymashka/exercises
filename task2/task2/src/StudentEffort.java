

public class StudentEffort {
    private String lastName;
    private int taskNumber;
    private int attemptNumber;
    private int score;

    public StudentEffort(String lastName, int taskNumber, int attemptNumber, int score) {
        this.lastName = lastName;
        this.taskNumber = taskNumber;
        this.attemptNumber = attemptNumber;
        this.score = score;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public int getAttemptNumber() {
        return attemptNumber;
    }

    public void setAttemptNumber(int attemptNumber) {
        this.attemptNumber = attemptNumber;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
