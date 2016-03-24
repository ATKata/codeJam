import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class StoreCredit {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("A-small-practice.in");

        List<String> strings = Files.readAllLines(path);

        strings.stream().forEach(System.out::println);
    }
}
