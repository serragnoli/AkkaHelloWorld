package sample.hello;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class HelloWorld extends UntypedActor {

    @Override
    public void preStart() {
        // create the greeter actor
        final ActorRef greeter = getContext().actorOf(Props.create(Greeter.class), "greeter");
        final ActorRef byer = getContext().actorOf(Props.create(Byer.class), "byer");
        // tell it to perform the greeting
        greeter.tell(byer, getSelf());
//        byer.tell(Byer.Msg.TIME_TO_GO, getSelf());
    }

    @Override
    public void onReceive(Object msg) {
        if (msg == Greeter.Msg.DONE) {
            // when the greeter is done, stop this actor and with it the application
            System.out.println("HW: Going to shutdown the app now. " + Thread.currentThread().getName());
            getContext().stop(getSelf());
        } else {
            unhandled(msg);
        }
    }
}