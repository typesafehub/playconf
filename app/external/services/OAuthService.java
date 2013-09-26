package external.services;

import play.libs.F.Promise;
import play.libs.F.Tuple;
import play.libs.OAuth.RequestToken;

import com.fasterxml.jackson.databind.JsonNode;

public interface OAuthService {

    public Tuple<String, RequestToken> retrieveRequestToken(String callbackUrl);
    
    public Promise<JsonNode> registeredUserProfile(RequestToken token, String authVerifier);
}
