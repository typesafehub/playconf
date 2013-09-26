package actors.messages;

import models.RegisteredUser;
import play.libs.Json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class UserRegistrationEvent implements UserEvent {
    
    private RegisteredUser ru;

    public UserRegistrationEvent(RegisteredUser ru) {
        this.ru = ru;
        
    }

    @Override
    public JsonNode json() {
        final ObjectNode result = Json.newObject();
        result.put("messageType", "registeredUser");
        result.put("name", ru.name);
        result.put("twitterId", ru.twitterId);
        result.put("description", ru.description);
        result.put("pictureUrl", ru.pictureUrl);
        return result;
    }

}
