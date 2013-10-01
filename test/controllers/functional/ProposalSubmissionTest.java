package controllers.functional;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import controllers.routes;
import play.libs.F.Callback;
import play.test.TestBrowser;

public class ProposalSubmissionTest {

   @Test
   public void proposalSubmissionFlow() {
       Map<String, Object> dbSettings = new HashMap<String, Object>();
       dbSettings.put("db.default.url", "jdbc:mysql://localhost:3306/playconftest");
       running(testServer(3333, fakeApplication(dbSettings)), HTMLUNIT, new Callback<TestBrowser>() {

        @Override
        public void invoke(TestBrowser browser) throws Throwable {
            browser.goTo("http://localhost:3333" + routes.Application.newProposal().url());
            assertThat(browser.title()).isEqualTo("PlayConf 2014 - Submit new talk");
            browser.fill("#title").with("This is a test play presentation");
            browser.fill("#proposal").with("This presention is going to talk about testing in play");
            browser.fill("#speaker_name").with("Nilanjan Raychaudhuri");
            browser.fill("#speaker_email").with("nilanjan@typesafe.com");
            browser.fill("#speaker_bio").with("Developer/Consultant/Author and overall nice guy");
            browser.fill("#speaker_pictureUrl").with("mug shot");
            browser.fill("#speaker_twitterId").with("nraychaudhuri");
            browser.submit("#submitForm"); 
            assertThat(browser.findFirst("#message").getText()).isEqualTo("Thanks for submitting a proposal");
        }
       });
   }
   
}
