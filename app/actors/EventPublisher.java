package actors;

import java.util.HashMap;
import java.util.Map;

import play.Logger;
import play.libs.Akka;
import play.mvc.WebSocket.Out;
import actors.messages.CloseConnectionEvent;
import actors.messages.NewConnectionEvent;
import actors.messages.UserEvent;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

import com.fasterxml.jackson.databind.JsonNode;

public class EventPublisher extends UntypedActor {

    public static ActorRef ref = Akka.system().actorOf(Props.create(EventPublisher.class));
    
    private Map<String, Out<JsonNode>> connections = new HashMap<String, Out<JsonNode>>();
    
    @Override
    public void onReceive(Object message) throws Exception {
        // TODO add event handling logic
        if(message instanceof NewConnectionEvent) {
            final NewConnectionEvent nce = (NewConnectionEvent) message;
            connections.put(nce.uuid(), nce.out());
            Logger.info("New browser connected " + nce.uuid());
        } else if(message instanceof CloseConnectionEvent) {
            final CloseConnectionEvent cce = (CloseConnectionEvent) message;
            final String uuid = cce.uuid();
            connections.remove(uuid);
            Logger.info("Browser " + uuid + "is disconnected");
        } else if(message instanceof UserEvent) {
          broadcastEvent((UserEvent)message);
            
        } else {
            unhandled(message);
        }
        
    }

    private void broadcastEvent(UserEvent message) {
        for(Out<JsonNode> out: connections.values()) {
            out.write(message.json());
        }
    }

}
