package sample.hello;

import akka.actor.UntypedActor;

public class Greeter extends UntypedActor {

    public enum Msg {
        GREET, DONE
    }

    @Override
    public void onReceive(Object msg) {
        System.out.println("Receiving message on Greeter.");
        if (msg == Msg.GREET) {
            System.out.println("Greeter says: Hello World! " + Thread.currentThread().getName());
            getSender().tell(Msg.DONE, getSelf());
        } else
            System.out.println("Greeter says: This is not my message.");
            unhandled(msg);
    }

}