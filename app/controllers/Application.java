package controllers;

import models.Proposal;
import play.data.Form;
import play.libs.F.Callback0;
import play.libs.F.Function;
import play.libs.F.Promise;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;
import actors.EventPublisher;
import actors.messages.CloseConnectionEvent;
import actors.messages.NewConnectionEvent;
import actors.messages.NewProposalEvent;
import akka.actor.ActorRef;

import com.fasterxml.jackson.databind.JsonNode;

public class Application extends Controller {
    
    private static final Form<Proposal> proposalForm = Form.form(Proposal.class);
    
    
    public static WebSocket<JsonNode> buzz() {
        return new WebSocket<JsonNode>() {

            @Override
            public void onReady(play.mvc.WebSocket.In<JsonNode> in,
                    play.mvc.WebSocket.Out<JsonNode> out) {
                //use the out channel to push the data back to the client
                final String uuid = java.util.UUID.randomUUID().toString();
                EventPublisher.ref.tell(new NewConnectionEvent(uuid, out), ActorRef.noSender());
                
                in.onClose(new Callback0() {
                    @Override
                    public void invoke() throws Throwable {
                        EventPublisher.ref.tell(new CloseConnectionEvent(uuid), ActorRef.noSender());
                    }
                });
            }
            
        };
    }
    
    
    public static Promise<Result> index() {
        Promise<Proposal> keynote = Proposal.findKeynote();
        Promise<Result> result = keynote.map(new Function<Proposal, Result>() {

            @Override
            public Result apply(Proposal keynote) throws Throwable {
                return ok(views.html.index.render(keynote));
            }
        });
        return result;
    }
    
    public static Result newProposal() {
        return ok(views.html.newProposal.render(proposalForm));
    }
    
    public static Promise<Result> submitProposal() {
        Form<Proposal> submittedForm = proposalForm.bindFromRequest();
        if(submittedForm.hasErrors()) {
            return Promise.<Result>pure(ok(views.html.newProposal.render(submittedForm)));
        } else {
            final Proposal proposal = submittedForm.get();
            Promise<Result> r = proposal.asyncSave().map(new Function<Void, Result>(){
                @Override
                public Result apply(Void arg0) throws Throwable {
                    flash ("message", "Thanks for submitting a proposal");
                    EventPublisher.ref.tell(new NewProposalEvent(proposal), ActorRef.noSender());
                    return redirect(routes.Application.index());
                }
            });
            return r;
        }
    }
    
}
