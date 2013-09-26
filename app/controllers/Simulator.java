package controllers;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import models.RegisteredUser;
import play.Logger;
import play.Play;
import play.libs.Akka;
import play.libs.F.Callback;
import play.libs.F.Promise;
import play.mvc.Controller;
import play.mvc.Result;
import scala.concurrent.duration.Duration;
import actors.EventPublisher;
import actors.messages.UserRegistrationEvent;
import akka.actor.ActorRef;

import com.fasterxml.jackson.databind.JsonNode;

import external.services.TwitterOAuthService;

public class Simulator extends Controller {

    private static List<String> twitterIds = Arrays.asList("nraychaudhuri",
            "mmahawar", "playframework", "springrod", "huntchr", "jroper",
            "derekhenninger", "StefanZeiger", "h3nk3", "chadfowler",
            "mabrewer7", "havocp", "Sadache", "guillaumebort", "tbjerkes",
            "_JamesWard", "retronym", "patriknw", "adriaanm", "rit",
            "jsuereth", "jamie_allen", "viktorklang", "hseeberger");

    private static int counter = 0;

    private static final TwitterOAuthService service = new TwitterOAuthService(
            Play.application().configuration().getString("twitter.consumer.key"), 
            Play.application().configuration().getString("twitter.consumer.secret"));

    public static Result simulateRegistration() throws Exception {
        Akka.system()
                .scheduler()
                .schedule(Duration.create(1, TimeUnit.SECONDS),
                        Duration.create(10, TimeUnit.SECONDS), new Runnable() {
                            public void run() {
                                String twitterId = twitterIds.get(counter);
                                fireUserRegistrationEvent(twitterId);
                                counter++;
                                if (counter == twitterIds.size()) {
                                    counter = 0;
                                }
                            }
                        }, Akka.system().dispatcher());

        return ok("done");
    }

    private static void fireUserRegistrationEvent(String twitterId) {
        try {
            Promise<JsonNode> userProfile = service.userProfile.apply(twitterId);
            userProfile.onRedeem(new Callback<JsonNode>() {
                @Override
                public void invoke(JsonNode json) throws Throwable {
                    RegisteredUser u = RegisteredUser.fromJson(json);
                    EventPublisher.ref.tell(new UserRegistrationEvent(u), ActorRef.noSender());
                }
            });
        } catch (Throwable e) {
            Logger.error("Something went wrong", e);
        }
    }
}
