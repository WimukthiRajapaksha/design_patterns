/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chainofresponsibility.BrokerChain;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 *
 * @author wimukthirajapaksha
 */

class Event<Args> {
    private int index=0;
    private Map<Integer, Consumer<Args>> handlers=new HashMap<>();
    
    public int subscribe(Consumer<Args> handler) {
        int i=index;
        handlers.put(index++, handler);
        return i;
    }
    
    public void unsubscribe(int key)  {
        handlers.remove(key);
    }
    
    public void fire(Args args) {
        for(Consumer<Args> handler: handlers.values()) {
            handler.accept(args);
        }
    }
}

class Query {
    public String creatureName;
    enum Argument {
        ATTACK, DEFENSE
    }
    public Argument argument;
    public int result;

    public Query(String creatureName, Argument argument, int result) {
        this.creatureName = creatureName;
        this.argument = argument;
        this.result = result;
    }
}

class Game { //mediator
    public Event<Query> queries=new Event<>();
    
}

class Creature {
    private Game game;
    public String name;
    public int baseAttack, baseDefense;

    public Creature(Game game, String name, int baseAttack, int baseDefense) {
        this.game = game;
        this.name = name;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
    }
    
    public int getAttack() {
        Query query=new Query(name, Query.Argument.ATTACK, baseAttack);
        game.queries.fire(query);
        return query.result;
    }
    
    public int getDefense() {
        Query query=new Query(name, Query.Argument.DEFENSE, baseDefense);
        game.queries.fire(query);
        return query.result;
    }

    @Override
    public String toString() {
        return "Creature{" + "game=" + game + ", name=" + name + 
                ", attack=" + getAttack() + ", defense=" + getDefense() + '}';
    }
}

class CreatureModifier {
    protected Game game;
    protected Creature creature;

    public CreatureModifier(Game game, Creature creature) {
        this.game = game;
        this.creature = creature;
    }
}

class DoubleAttackModifier extends CreatureModifier implements AutoCloseable {
    private final int token;

    public DoubleAttackModifier(Game game, Creature creature) {
        super(game, creature);
        token = game.queries.subscribe(q->{
            if(q.creatureName.equals(creature.name) && q.argument==Query.Argument.ATTACK) {
                q.result*=2;
            }
        });
    }

    @Override
    public void close() {
        game.queries.unsubscribe(token);
    }
}

class IncreaseDefenseModifier extends CreatureModifier {

    public IncreaseDefenseModifier(Game game, Creature creature) {
        super(game, creature);
        game.queries.subscribe(q->{
            if(q.creatureName.equals(creature.name) && q.argument==Query.Argument.DEFENSE) {
                q.result+=3;
            }
        });
    }
    
}

public class BrokerChain {
    public static void main(String[] args) {
        Game game=new Game();
        Creature goblin=new Creature(game, "Strong Goblin", 2, 2);
        System.out.println(goblin);
        
        IncreaseDefenseModifier increaseDefenseModifier=new IncreaseDefenseModifier(game, goblin);
        try(DoubleAttackModifier modifier=new DoubleAttackModifier(game, goblin)) {
            System.out.println(goblin);
        }
        System.out.println(goblin);
    }
}
