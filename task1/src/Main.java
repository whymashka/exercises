import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws NegativeNumberException {
        List<Integer> list = new ArrayList<Integer>();
// list.add(-1);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        System.out.println(task1(list));
    }

    public static int task1(List<Integer> list) throws NegativeNumberException {
        int ex = list.stream().filter(it -> it <= 0).findFirst().orElse(1);
        if (ex != 1)
            throw new NegativeNumberException(ex);

        return (int) list.stream()
                .filter(n -> n > 1)
                .filter(it -> IntStream.range(2, it - 1).anyMatch(i -> it % i == 0))
                .count();
    }
}

class NegativeNumberException extends Exception {
    private final int numberException;
    public NegativeNumberException(int numberException) {
        this.numberException = numberException;
    }
    public int getNumberException() {
        return numberException;
    }
}