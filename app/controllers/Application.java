package controllers;

import play.*;
import play.api.templates.Html;
import play.mvc.*;
import play.data.*;

import views.html.*;
import models.*;

public class Application extends Controller {
  
    public static Result index() {
        return ok(index.render("hi"));
    }

    public static Result users() {
        return ok(users.render(User.find.all()));
    }

    public static Result login(){
        // this referencese the inner login class below
        return ok(login.render(Form.form(Login.class)));
    }

    public static Result authenticate(){
        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();

        // hasErrors is from Form class in Play
        // just need to implement "validate" function to use it
        if( loginForm.hasErrors()){
            return badRequest(login.render(loginForm));
        }
        else{
            // create a new session for the guy who just logged in
            // "email" string is just a key
            session().clear();
            session("email", loginForm.get().email);
            return redirect(routes.Application.index());
        }
    }

    public static class Login {
        public String email;
        public String password;

        public String validate(){
            if( User.authenticate(email, password) == null){
                return "Invalid email/password.";
            }
            return null;
        }
    }
}
