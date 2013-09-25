package controllers;

import models.Proposal;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;

public class Application extends Controller {
    
    private static final Form<Proposal> proposalForm = Form.form(Proposal.class);
    
    public static Result index() {
        Proposal keynote = Proposal.findKeynote();
        return ok(views.html.index.render(keynote));
    }
    
    public static Result newProposal() {
        return ok(views.html.newProposal.render(proposalForm));
    }
    
    public static Result submitProposal() {
        Form<Proposal> submittedForm = proposalForm.bindFromRequest();
        if(submittedForm.hasErrors()) {
            return ok(views.html.newProposal.render(submittedForm));
        } else {
            Proposal proposal = submittedForm.get();
            proposal.save();
            flash ("message", "Thanks for submitting a proposal");
            return redirect(routes.Application.index());
        }
    }
    
}
