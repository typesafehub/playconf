package actors.messages;

import com.fasterxml.jackson.databind.JsonNode;

public interface UserEvent {

    public JsonNode json();
}
