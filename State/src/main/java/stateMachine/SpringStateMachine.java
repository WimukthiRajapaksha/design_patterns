package stateMachine;

import handmade.Trigger;
import javafx.animation.Transition;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.state.State;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

enum States { // as package have a attribute called state internally
    OFF_HOOK, // START
    ON_HOOK, // TERMINATE
    CONNECTING,
    CONNECTED,
    ON_HOLD
}

enum Events {
    CALL_DIALED,
    HANG_UP,
    CALL_CONNECTED,
    PLACE_ON_HOLD,
    TAKEN_OFF_HOLD,
    LEFT_MESSAGE,
    STOP_USING_PHONE
}



public class SpringStateMachine {

    public static StateMachine<States, Events> buildMachine() throws Exception {
        StateMachineBuilder.Builder<States, Events> builder = StateMachineBuilder.builder();
        builder.configureStates().withStates()
                .initial(States.OFF_HOOK)
                .states(EnumSet.allOf(States.class));
        builder.configureTransitions()
                .withExternal()
                .source(States.OFF_HOOK)
                .event(Events.CALL_DIALED)
                .target(States.CONNECTING)
                .and();
        // from withExternal() till end of states
        return builder.build();
    }
    public static void main(String[] args) {
        StateMachine<States, Events> machine = buildMachine();
        machine.start();
        States exitState = States.ON_HOOK;


        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            State<States, Events> statee=machine.getState();
            System.out.println("The phone is currently "+statee.getId());
            System.out.println("select a trigger: ");

            List<Transition<States, Events>> ts=machine.getTransitions()
                    .stream()
                    .filter(t-> t.getSource()==statee);
//                    .collect(Collectors.toList());

//            for(int i=0; i<ts.size(); i++) { // NOT CLEAR THE REST OF IMPLEMENTATIONS
//                System.out.println(i+". "+ts.get(i));
//            }
//            boolean parseOk;
//            int choice=0;
//            do {
//                try {
//                    System.out.println("Please enter your choice: ");
//                    choice = Integer.parseInt(reader.readLine());
//                    parseOk = choice >= 0 && choice < rules.get(currentState).size();
//                } catch (Exception e)
//                {
//                    parseOk=false;
//                }
//            } while(!parseOk);
//            machine.sendEvent(ts).get(choice).getValue();
//            if(currentState==exitState) break;
        }
        System.out.println("we are done.");
    }
}
