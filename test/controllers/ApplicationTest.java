package controllers;

import static helpers.TestSetup.testHttpContext;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import play.libs.F;
import play.libs.F.Tuple;
import play.libs.OAuth.RequestToken;
import play.mvc.Http.Context;
import play.mvc.Http.Flash;
import play.mvc.Http.Status;
import play.mvc.Result;
import play.test.Helpers;
import akka.actor.ActorRef;
import external.services.OAuthService;
import static play.test.Helpers.status;
import static org.fest.assertions.Assertions.assertThat;

public class ApplicationTest {

    @Test
    public void redirectToOAuthProviderForRegistration() {
        Context.current.set(testHttpContext());
        OAuthService oauth = mock(OAuthService.class);
        Tuple<String, RequestToken> t = new F.Tuple<String, RequestToken>(
                "twitter.redirect.url", new RequestToken("twitter.token", "twitter.secret"));
        when(oauth.retrieveRequestToken(anyString())).thenReturn(t);
        
        Application app = new Application(mock(ActorRef.class), oauth);
        
        Result result = app.register();
        
        assertThat(status(result)).isEqualTo(Status.SEE_OTHER);
        assertThat(Helpers.redirectLocation(result)).isEqualTo("twitter.redirect.url");
        
        Flash flash = Context.current().flash();
        assertThat(flash.get("request_token")).isEqualTo("twitter.token");
        assertThat(flash.get("request_secret")).isEqualTo("twitter.secret");
        
    }
}
