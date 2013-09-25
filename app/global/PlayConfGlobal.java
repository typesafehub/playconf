package global;

import java.util.concurrent.TimeUnit;

import models.Proposal;
import play.Application;
import play.GlobalSettings;
import play.libs.Akka;
import play.libs.F;
import play.libs.F.Callback;
import play.libs.F.Promise;
import play.mvc.Http.RequestHeader;
import play.mvc.Results;
import play.mvc.SimpleResult;
import scala.concurrent.duration.Duration;
import actors.EventPublisher;
import actors.messages.RandomlySelectedTalkEvent;
import akka.actor.ActorRef;

public class PlayConfGlobal extends GlobalSettings {
    
    @Override
    public void onStart(Application arg0) {
        super.onStart(arg0);
        Akka.system()
        .scheduler()
        .schedule(Duration.create(1, TimeUnit.SECONDS),
                Duration.create(10, TimeUnit.SECONDS),
                selectRandomTalks(), Akka.system().dispatcher());
    }
    
    private Runnable selectRandomTalks() {
       return new Runnable() {
        @Override
        public void run() {
           Promise<Proposal> proposal = Proposal.selectRandomTalk();     
           proposal.onRedeem(new Callback<Proposal>() {
               public void invoke(Proposal p) {
                  EventPublisher.ref.tell(new RandomlySelectedTalkEvent(p), ActorRef.noSender()); 
               }
           });
        }
       };
    }

    @Override
    public Promise<SimpleResult> onHandlerNotFound(RequestHeader arg0) {
       return F.Promise.<SimpleResult>pure(Results.notFound(views.html.error.render()));
    }
    
    @Override
    public Promise<SimpleResult> onError(RequestHeader arg0, Throwable arg1) {
        return F.Promise.<SimpleResult>pure(Results.internalServerError(views.html.error.render()));
    }
}
