import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;

import java.util.ArrayList;
import java.util.List;

class EventBroker extends Observable<Integer> {
    private List<Observer<? super Integer>> observers=new ArrayList<>();

    @Override
    protected void subscribeActual(@NonNull Observer<? super Integer> observer) {
        observers.add(observer);
    }

    public void publish(int n) {
        System.out.println("publishing..");
        for(Observer<? super  Integer> o: observers) {
            o.onNext(n);
        }
    }
}

class FootballPlayer {
    private int goalsScored=0;
    private EventBroker broker;
    public String name;

    public FootballPlayer(EventBroker broker, String name) {
        this.broker = broker;
        this.name = name;
    }

    public void score() {
        broker.publish(++goalsScored);
    }
}

class FootballCoach {
    public FootballCoach(EventBroker broker) {
        broker.subscribe(i->System.out.println("Hello "+i));
    }
}

public class ReactiveEventBroker {
    public static void main(String[] args) {
        EventBroker broker=new EventBroker();
        FootballPlayer player=new FootballPlayer(broker, "John");
        FootballCoach coach=new FootballCoach(broker);

        player.score();
        player.score();
        player.score();
    }
}
