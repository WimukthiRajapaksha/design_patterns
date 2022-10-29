package Static;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

enum OutputFormat {
    MARKDOWN, HTML
}

interface ListStrategy {
    default void start(StringBuilder sb) {

    }
    void addListItem(StringBuilder sb, String item);
    default void end(StringBuilder sb) {

    }
}

class MarkdownListStrategy implements ListStrategy {
    @Override
    public void addListItem(StringBuilder sb, String item) {
        sb.append(" * ").append(item).append(System.lineSeparator());
    }
}

class HtmlListStrategy implements ListStrategy {

    @Override
    public void start(StringBuilder sb) {
        sb.append("<ul>").append(System.lineSeparator());
    }

    @Override
    public void addListItem(StringBuilder sb, String item) {
        sb.append("  <li>").append(item).append("</li>").append(System.lineSeparator());
    }

    @Override
    public void end(StringBuilder sb) {
        sb.append("</ul>").append(System.lineSeparator());
    }
}

class TextProcessor<T extends ListStrategy> {
    private StringBuilder sb=new StringBuilder();
    private T listStrategy;
    public TextProcessor(Supplier<? extends T> supplier) {
        listStrategy=supplier.get();
    }

    public void appendList(List<String> items) {
        listStrategy.start(sb);
        for(String item: items) {
            listStrategy.addListItem(sb, item);
        }
        listStrategy.end(sb);
    }

    public void clear() {
        sb.setLength(0);
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}


public class StaticStrategy {
    public static void main(String[] args) {
        TextProcessor<MarkdownListStrategy> tp=new TextProcessor<>(MarkdownListStrategy::new);
        tp.appendList(Arrays.asList("liberte", "abc", "pqrs"));
        System.out.println(tp);

        tp.clear();
        TextProcessor<HtmlListStrategy> html=new TextProcessor<>(HtmlListStrategy::new);
        html.appendList(Arrays.asList("insert", "update", "delete"));
        System.out.println(html);
    }
}
