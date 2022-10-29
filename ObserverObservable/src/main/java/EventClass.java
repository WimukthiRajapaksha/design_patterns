import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

class Event<TArgs> {
    private int count=0;
    public Map<Integer, Consumer<TArgs>> handlers=new HashMap<>();
    public Subscription addHandler(Consumer<TArgs> handler) {
        int i=count;
        handlers.put(count++, handler);
        return new Subscription(this, i);
    }

    public void fire(TArgs args) {
        for(Consumer<TArgs> handler: handlers.values()) {
            handler.accept(args);
        }
    }
}


class Subscription<TArgs> implements AutoCloseable {
    private Event<TArgs> event;
    private int id;

    public Subscription(Event<TArgs> event, int id) {
        this.event = event;
        this.id = id;
    }

    @Override
    public void close() {
        event.handlers.remove(id);
    }
}

class PropertyChangedEventTArgs {
    public Object source;
    public String propertyName;

    public PropertyChangedEventTArgs(Object source, String propertyName) {
        this.source = source;
        this.propertyName = propertyName;
    }
}

class PersonP {
    public Event<PropertyChangedEventTArgs> propertyChangedEventTArgs=new Event<>();
    private int age;

    public Event<PropertyChangedEventTArgs> getPropertyChangeEventArgsEvent() {
        return propertyChangedEventTArgs;
    }

    public void setPropertyChangeEventArgsEvent(Event<PropertyChangedEventTArgs> propertyChangeEventArgsEvent) {
        this.propertyChangedEventTArgs = propertyChangeEventArgsEvent;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(this.age==age) return;
        this.age = age;
        propertyChangedEventTArgs.fire(new PropertyChangedEventTArgs(this, "age"));
    }
}

public class EventClass {
    public static void main(String[] args) {
        PersonP person=new PersonP();
        Subscription propChange = person.propertyChangedEventTArgs.addHandler(x->{
            System.out.println("Person's"+x.propertyName+" changed");
        });
        person.setAge(16);
        person.setAge(18);
        propChange.close();
        person.setAge(20);
    }
}
