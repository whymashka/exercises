import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TestStudentEndHandler {

    @Test
    public void getResultsTest(){
        StudentEndHandler studentEndHandler = new StudentEndHandler();

        StudentEffort studentEffort = new StudentEffort("Ivanov", 1, 1, 100);
        StudentEffort studentEffort1 = new StudentEffort("Alekseev", 1, 6, 66);
        StudentEffort studentEffort2 = new StudentEffort("Pavlov", 1, 15, 33);

        List<StudentEffort> studentEfforts = new ArrayList<>();
        studentEfforts.add(studentEffort);
        studentEfforts.add(studentEffort1);
        studentEfforts.add(studentEffort2);

        Map<String, Integer> actual = studentEndHandler.getResults(studentEfforts);

        Map<String, Integer> expected = new HashMap<>();
        expected.put("Ivanov", 100);
        expected.put("Alekseev", 22);
        expected.put("Pavlov", 1);

        Assert.assertEquals(actual, expected);
    }
}
