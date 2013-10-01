package controllers.functional;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;
import static helpers.TestSetup.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import play.mvc.Http.Status;
import play.mvc.Result;

public class RoutingTest {

    @Test
    public void routeToIndexAction() {
        running(fakeApplication(), new Runnable() {

            @Override
            public void run() {
                Result result = route(fakeRequest("GET", "/"));
                assertThat(status(result)).isEqualTo(Status.OK);
                Document doc = Jsoup.parse(contentAsString(result));
                assertThat(doc.select("#title").text()).isEqualTo("Keynote - History of playframework");
                assertThat(doc.select("#speakerName").text()).isEqualTo("Guillaume Bort");
                assertThat(doc.select("#twitterId").text()).isEqualTo("guillaumebort");
            }
            
        });
    }
}
