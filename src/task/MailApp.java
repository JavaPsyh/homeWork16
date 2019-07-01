package task;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class MailApp {
    public static void main(String[] args) {
    }

    public interface Sendable<T> {
        String getFrom();

        String getTo();

        T getContent();
    }

    public static class MailMessage implements Sendable<String> {
        private String from;
        private String to;
        private String content;

        public MailMessage(String from, String to, String content) {
            this.from = from;
            this.to = to;
            this.content = content;
        }

        @Override
        public String getFrom() {
            return from;
        }

        @Override
        public String getTo() {
            return to;
        }

        @Override
        public String getContent() {
            return content;
        }
    }

    public static class Salary implements Sendable<Integer> {

        private String from;
        private String to;
        private Integer salary;

        public Salary(String from, String to, Integer salary) {
            this.from = from;
            this.to = to;
            this.salary = salary;
        }

        public Integer getSalary() {
            return salary;
        }

        @Override
        public String getFrom() {
            return from;
        }

        @Override
        public String getTo() {
            return to;
        }

        @Override
        public Integer getContent() {
            return getSalary();
        }
    }

    public static class MailService<T> extends HashMap implements Consumer<Sendable<T>> {

        @Override
        public void accept(Sendable<T> sendable) {
            String key = sendable.getTo();
            if (!map.containsKey(key)) { // without this check Stepik don't validate
                map.put(key, new LinkedList<>());
            }
            map.get(key).add(sendable.getContent());
        }

        private Map<String, List<T>> map = new HashMap<String, List<T>>() {
            @Override
            public List<T> get(Object key) {
                return super.getOrDefault(key, new LinkedList<>());
            }
        };

        public Map<String, List<T>> getMailBox() {
            return this.map;
        }
    }
}
