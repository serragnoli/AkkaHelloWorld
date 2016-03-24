package sample.hello;

import akka.actor.UntypedActor;

public class Byer extends UntypedActor {

    public enum Msg {
        TIME_TO_GO, DONE
    }

    @Override
    public void onReceive(Object msg) {
        System.out.println("Byer: Receiving message on Byer.");
        if (msg == Msg.TIME_TO_GO) {
            for (int i = 0; i < 1000; i++) {
                System.out.println("Byer: Time to go folks. " + i + " " + Thread.currentThread().getName());
            }
            getSender().tell(Msg.DONE, getSelf());
        } else {
            System.out.println("Byer: Not my message so I don't care. " + Thread.currentThread().getName());
            unhandled(msg);
        }
    }
}
