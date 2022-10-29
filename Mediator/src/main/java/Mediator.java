/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wimukthirajapaksha
 */

class Person {
    public String name;
    public ChatRoom room;
    private List<String> chatLog=new ArrayList<>();

    public Person(String name) {
        this.name = name;
    }
    
    public void receive(String sender, String message) {
        String s=sender+": '"+message+"'";
        System.out.println("["+name+"'s chat session] "+s);
        chatLog.add(s);
    }
    
    public void say(String message) {
        room.broadcast(name, message);
    }
    
    public void privateMessage(String who, String message) {
        room.message(name, who, message);
    }
}

class ChatRoom {
    private List<Person> people=new ArrayList<>();
    
    public void join(Person p) {
        String joinMsg=p.name+" joins the room";
        broadcast("room", joinMsg);
        p.room=this;
        people.add(p);
    }
    
    public void broadcast(String source, String message) {
        for(Person p: people) {
            if(!p.name.equals(source)) {
                p.receive(source, message);
            }
        }
    }
    
    public void message(String source, String destination, String message) {
        people.stream().filter(p->p.name.equals(destination)).findFirst().ifPresent(p->p.receive(source, message));
    }
}

public class Mediator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ChatRoom chatRoom=new ChatRoom();
        Person john=new Person("John");
        Person james=new Person("James");
        chatRoom.join(john);
        chatRoom.join(james);
        john.say("hi there");
        james.say("i'm using");
        Person kim=new Person("Kim");
        chatRoom.join(kim);
        kim.say("Whatsapp");
        kim.privateMessage("John", "Do we know each other.");
    }
    
}
