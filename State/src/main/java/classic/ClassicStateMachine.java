package classic;

class State {
    void on(LightSwitch ls) {

    }

    void off(LightSwitch ls) {

    }
}

class OnState extends State {
    public OnState() {
        System.out.println("Light turned on");
    }

    @Override
    void on(LightSwitch ls) {
        System.out.println("Light is already on");
    }

    @Override
    void off(LightSwitch ls) {
        System.out.println("Switching off..");
        ls.setState(new OffState());
    }
}

class OffState extends State {

    public OffState() {
        System.out.println("Light turned off");
    }

    @Override
    void on(LightSwitch ls) {
        System.out.println("Switching on");
        ls.setState(new OnState());
    }

    @Override
    void off(LightSwitch ls) {
        System.out.println("Light is already off");
    }
}
class LightSwitch {
    private State state; // on or off

    public LightSwitch() {
        this.state = new OffState();
    }

    void on() {
        state.on(this);
    }

    void off() {
        state.off(this);
    }

    public void setState(State state) {
        this.state = state;
    }
}

public class ClassicStateMachine {
    public static void main(String[] args) {
        LightSwitch lightSwitch=new LightSwitch();
        lightSwitch.off();
        lightSwitch.on();
        lightSwitch.off();
        lightSwitch.off();
    }
}
