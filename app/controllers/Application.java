package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {
    
    public static Result welcome(String name) {
      return ok("<h1> Welcome " + name + "</h1>").as("text/html");    
    }
    
    public static Result index() {
        return ok(views.html.index.render("Hello Play Framework"));
    }
    
}
