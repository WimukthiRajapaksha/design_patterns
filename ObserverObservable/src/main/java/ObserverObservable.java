import java.util.ArrayList;
import java.util.List;

class PropertyChangeEventArgs<T> {
    public T source;
    public String propertyName;
    public Object newValue;

    public PropertyChangeEventArgs(T source, String propertyName, Object newValue) {
        this.source = source;
        this.propertyName = propertyName;
        this.newValue = newValue;
    }
}

interface Observer<T> {
    void handle(PropertyChangeEventArgs<T> args);
}

class Observable<T> {
    private List<Observer<T>> observerList=new ArrayList<>();
    public void subscribe(Observer<T> observer) {
        observerList.add(observer);
    }

    protected void propertyChanged(T source, String propertyName, Object newValue) {
        for(Observer<T> o: observerList) {
            o.handle(new PropertyChangeEventArgs<>(source, propertyName, newValue));
        }
    }
}

class Person extends Observable<Person> {
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(this.age==age) return;
        this.age = age;
        propertyChanged(this, "age", age);
    }
}

public class ObserverObservable implements Observer<Person> {

    public ObserverObservable() {
        Person person=new Person();
        person.subscribe(this);
        for(int i=20; i<29; i++) {
            person.setAge(i);
        }
    }

    @Override
    public void handle(PropertyChangeEventArgs<Person> args) {
        System.out.println("Person's "+args.propertyName+" -> "+args.newValue);
    }

    public static void main(String[] args) {
        new ObserverObservable();
    }
}
