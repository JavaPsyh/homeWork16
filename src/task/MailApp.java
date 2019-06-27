package task;

import java.util.*;
import java.util.function.*;

public class MailApp {
    public static void main(String[] args) {
    }
    public interface Sendable<T>{
        String getFrom();
        String getTo();
        T getContent();
    }
    public static class MailMessage implements Sendable<String> {
        private final String FROM, TO, CONTENT;

        public MailMessage(String from, String to, String content) {
            this.FROM = from;
            this.TO = to;
            this.CONTENT = content;
        }

        @Override
        public String getFrom() {
            return FROM;
        }

        @Override
        public String getTo() {
            return TO;
        }

        @Override
        public String getContent() {
            return CONTENT;
        }
    }

    public static class Salary implements Sendable<Integer>{

        private final String FROM, TO;
        private Integer salary;

        public Salary(String from, String to, Integer salary) {
            this.FROM = from;
            this.TO = to;
            this.salary = salary;
        }

        public Integer getSalary() {
            return salary;
        }

        @Override
        public String getFrom() {
            return FROM;
        }

        @Override
        public String getTo() {
            return TO;
        }

        @Override
        public Integer getContent() {
            return getSalary();
        }
    }

    public static class MailService<T> extends HashMap implements Consumer<Sendable<T>> {

        @Override
        public void accept(Sendable<T> sendable) {
            String key = "";
            try {
                key = sendable.getTo();
                if (!map.containsKey(key)) {
                    map.put(key, new LinkedList<>());
                }
                map.get(key).add(sendable.getContent());
            }catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }
        private Map<String, List<T>> map  = new HashMap<String, List<T>>() {
            @Override
            public List<T> get(Object key) {
                return super.getOrDefault(key, new ArrayList<>());
            }
        };
        public Map<String, List<T>> getMailBox(){
            return this.map;
        }
    }
}
