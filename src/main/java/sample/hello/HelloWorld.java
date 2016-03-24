package sample.hello;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class HelloWorld extends UntypedActor {

    private boolean greeterFinished;
    private boolean byerFinished;

    @Override
    public void preStart() {
        // create the greeter actor
        final ActorRef greeter = getContext().actorOf(Props.create(Greeter.class), "greeter");
        final ActorRef byer = getContext().actorOf(Props.create(Byer.class), "byer");
        // tell it to perform the greeting
        greeter.tell(Greeter.Msg.GREET, getSelf());
        byer.tell(Byer.Msg.TIME_TO_GO, getSelf());
    }

    @Override
    public void onReceive(Object msg) {
        if (msg == Greeter.Msg.DONE) {
            System.out.println("HW: Greeter done");
            greeterFinished = true;
        } else if (msg == Byer.Msg.DONE) {
            System.out.println("HW: Byer done");
            byerFinished = true;
        } else {
            unhandled(msg);
        }
        shutdown();
    }

    private void shutdown() {
        if (greeterFinished && byerFinished) {
            System.out.println("HW: Going to shutdown the app now. " + Thread.currentThread().getName());
            getContext().stop(getSelf());
        }
    }
}