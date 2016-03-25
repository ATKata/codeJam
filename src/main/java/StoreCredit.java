import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StoreCredit {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src/main/resources/A-small-practice.in");
        List<String> strings = Files.readAllLines(path);
        strings.remove(0);
        int counter = 1;
        while (!strings.isEmpty()) {
            parseRecord(counter++, strings.remove(0), strings.remove(0), strings.remove(0));
        }
    }
    public static void parseRecord(int counter, String credit, String size, String values) {
        final List<Item> items = Stream.of(values.split(" "))
                .map(Integer::valueOf)
                .collect(items());
        processRecord(counter, Integer.valueOf(credit), items);
    }

    public static void processRecord(final int counter, final int credit, final List<Item> items) {
        String selection = items.stream()
                .flatMap(item -> items
                        .stream()
                        .filter(otherItem -> item.index > otherItem.index)
                        .map(otherItem -> new PairOfItem(item, otherItem))
                        .filter(pair -> pair.sum() == credit))
                .map(PairOfItem::toString)
                .collect(Collectors.joining(" "));
        System.out.printf("Case #%d: %s%n", counter, selection);
    }

    private static class ItemCollector implements Collector<Integer, List<Item>, List<Item>> {
        @Override
        public Supplier supplier() {
            return ArrayList::new;
        }

        @Override
        public BiConsumer<List<Item>, Integer> accumulator() {
            return (items, i) -> items.add(new Item(items.size() + 1, i));
        }

        @Override
        public BinaryOperator combiner() {
            return null;
        }

        @Override
        public Function finisher() {
            return (x) -> x;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return EnumSet.of(Characteristics.UNORDERED);        }
    }
    private static ItemCollector items() {
        return new ItemCollector();
    }

    private static class PairOfItem {
        private final Item item;
        private final Item otherItem;

        public PairOfItem(Item item, Item otherItem) {
            this.item = item;
            this.otherItem = otherItem;
        }
        public int sum() {
            return this.item.value + this.otherItem.value;
        }
        @Override
        public String toString() {
            return String.format("%s %s", this.item.index, this.otherItem.index);
        }
    }

    private static class Item {
        private final int index;
        private final int value;
        public Item(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("(%s, %s)", this.index, this.value);
        }
    }
}
