package sample.hello;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

public class Greeter extends UntypedActor {

    public enum Msg {
        GREET, DONE
    }

    @Override
    public void onReceive(Object msg) {
        System.out.println("Receiving message on Greeter.");
        if (msg instanceof ActorRef) {
            System.out.println("Greeter says: Hello World! " + Thread.currentThread().getName());
            ((ActorRef) msg).tell(Byer.Msg.TIME_TO_GO, getSelf());
        } else if (msg == Byer.Msg.DONE) {
            System.out.println("Greeter: Goodbye finished. " + Thread.currentThread().getName());
            getContext().parent().tell(Msg.DONE, getSelf());
        } else {
            System.out.println("Greeter says: This is not my message. " + Thread.currentThread().getName());
            unhandled(msg);
        }
    }
}