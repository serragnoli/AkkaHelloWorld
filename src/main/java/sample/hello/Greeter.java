package sample.hello;

import akka.actor.UntypedActor;

public class Greeter extends UntypedActor {

    public enum Msg {
        GREET, DONE
    }

    @Override
    public void onReceive(Object msg) {
        System.out.println("Greeter: Receiving message on Greeter.");
        if (msg == Msg.GREET) {
            for (int i = 0; i < 1000; i++) {
                System.out.println("Greeter: Hello. " + i + " " + Thread.currentThread().getName());
            }
            getSender().tell(Msg.DONE, getSelf());
        } else {
            System.out.println("Greeter: This is not my message. " + Thread.currentThread().getName());
            unhandled(msg);
        }
    }
}