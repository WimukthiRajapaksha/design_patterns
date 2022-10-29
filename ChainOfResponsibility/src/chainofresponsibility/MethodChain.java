/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chainofresponsibility;

/**
 *
 * @author wimukthirajapaksha
 */

class Creature {
    public String name;
    public int attack, defense;

    public Creature(String name, int attack, int defense) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
    }

    @Override
    public String toString() {
        return "Creature {" + "name=" + name + ", attack=" + attack + ", defense=" + defense + '}';
    }
}

class CreatureModifier {
    protected Creature creature;
    protected CreatureModifier next;

    public CreatureModifier(Creature creature) {
        this.creature = creature;
    }
    
    public void add(CreatureModifier cm) {
        if(this.next==null) this.next=cm;
        else next.add(cm);
    }
    
    public void handle() {
        if(next!=null) next.handle();
    }
}

class DoubleAttackModifier extends CreatureModifier {
    public DoubleAttackModifier(Creature creature) {
        super(creature);
    }

    @Override
    public void handle() {
        System.out.println("Doubling "+creature.name);
        creature.attack*=2;
        super.handle();
    }
}

class IncreaseDefenseModifier extends CreatureModifier {
    public IncreaseDefenseModifier(Creature creature) {
        super(creature);
    }

    @Override
    public void handle() {
        System.out.println("Increasing "+creature.name);
        creature.defense+=3;
        super.handle(); //To change body of generated methods, choose Tools | Templates.
    }
}

class NoBonusesModifier extends CreatureModifier{

    public NoBonusesModifier(Creature creature) {
        super(creature);
    }

    @Override
    public void handle() {
        System.out.println("No bonuses..");
    }
}

public class MethodChain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Creature goblin=new Creature("goblin", 2, 4);
        CreatureModifier root=new CreatureModifier(goblin);
        
        root.add(new NoBonusesModifier(goblin));
        
        
        System.out.println("Goblin's attack....");
        root.add(new DoubleAttackModifier(goblin));
        System.out.println("Goblin's defense");
        root.add(new IncreaseDefenseModifier(goblin));
        root.handle();
        System.out.println(goblin);
    }
}
