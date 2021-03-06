import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StoreCredit {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src/main/resources/A-small-practice.in");

        List<String> strings = Files.readAllLines(path);

        strings.remove(0);

        for (int i = 0; i < strings.size(); i += 3) {

            Integer credit = Integer.valueOf(strings.get(i));
            List<Integer> items = Stream.of(strings.get(i + 2)
                        .split(" "))
                        .map(Integer::valueOf).collect(Collectors.toList());

            List<Integer> remainders = items.stream().map(j -> credit - j).collect(Collectors.toList());
            for (int j=0; j<remainders.size(); j++) {
                if (items.contains(remainders.get(j))) {
                    int itemsIndex = items.lastIndexOf(remainders.get(j));
                    if(itemsIndex==j) continue;
                    System.out.printf("Case #%s: %s %s%n", i/3+1, j + 1, itemsIndex + 1, items.get(itemsIndex));
                    break;
                }
            }
        }
    }



}
