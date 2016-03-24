package sample.hello;

import akka.actor.UntypedActor;

public class Byer extends UntypedActor {

    public enum Msg {
        TIME_TO_GO, DONE
    }

    @Override
    public void onReceive(Object msg) {
        if (msg == Msg.TIME_TO_GO) {
            System.out.println("Byer: Time to go folks. " + Thread.currentThread().getName());
            getSender().tell(Msg.DONE, getSelf());
        } else {
            System.out.println("Byer: Not my message so I don't care. " + Thread.currentThread().getName());
            unhandled(msg);
        }
    }
}
