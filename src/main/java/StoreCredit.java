import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class StoreCredit {
    public static void main(String[] args) {
        Path path = Paths.get("A-small-practice.in");

        List<String> strings = Files.readAllLines(path);

    }
}
