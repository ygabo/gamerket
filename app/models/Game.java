package models;

import java.util.*;
import javax.persistence.*;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.*;
/**
 * Created with IntelliJ IDEA.
 * Author: tehee
 * Date: 9/18/13
 * Time: 12:39 AM
 */

@Entity
public class Game extends Model{
    @Id
    @Constraints.Required
    @Formats.NonEmpty
    public Long id;

    @Constraints.Required
    public String title;

    @Formats.DateTime(pattern="MM/dd/yy")
    public Date release;

    @Constraints.Required
    @ManyToOne
    public User developer;

    public Game (String title, Date release, User developer){
        this.title = title;
        this.release = release;
        this.developer = developer;
    }

    // create a game with the following properties
    public static Game create(String title, Date release, String developer){
        User s = User.find.ref(developer);
        System.out.println();System.out.println();System.out.println();System.out.println("----------");
        System.out.println(s.name + " " + s.email);System.out.println("----------");
        Game game = new Game(title, release, s);
        game.save();
        return game;
    }

    // find the games made by the dev with a name "dev_name"
    public static List<Game> findDevelopedBy(String dev_name){
        // fetch all games with developers that have dev_name
        // developer.name is referring to the developer variable
        // in this class/model which is a User.
        return find.fetch("developer").where().eq("developer.name", dev_name).findList();
    }

    public static Model.Finder<Long,Game> find =  new Model.Finder<Long, Game>(
            Long.class, Game.class
    );

}
