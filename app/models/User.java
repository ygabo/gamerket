package models;

import javax.persistence.*;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.*;
import com.avaje.ebean.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tehee
 * Date: 9/17/13
 * Time: 11:33 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class User extends Model{

    // id annotation is to
    // tell jpa this is the main ids
    @Column(unique=true)
    @Id
    @Constraints.Required
    @Formats.NonEmpty
    public String name;

    @Column(unique=true)
    @Constraints.Required
    public String email;

    @Constraints.Required
    @Formats.NonEmpty
    public String password;

    // cascade all means if we delete
    // user, delete all its games
    @OneToMany(mappedBy="developer",cascade=CascadeType.ALL)
    public List<Game> games;

    public User(String email, String name, String password){
        this.name = name;
        this.email = email;
        this.password = password;
        this.games = new ArrayList<Game>();
    }

    // authenticate for login
    public static User authenticate(String email, String password){
        // to authenticate, we just find in our db the user that
        // has the same email and password
        // improve this later
        return find.where().eq("email", email).eq("password", password).findUnique();
    }

    // This "find" is for the ebean so you can query the User class
    public static Finder<String, User> find = new Finder<String,User>(
            String.class, User.class
    );

    public User(User another){
        if(another != null){
            this.email = another.email;
            this.name = another.name;
            this.password = another.password;
        }
    }
}
