package models;
/**
 * Author: Yelnil Gabo
 * Date: 9/17/13
 * Time: 11:42 PM
 */
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

import play.mvc.*;
import play.libs.*;
import play.test.*;
import static play.test.Helpers.*;
import com.avaje.ebean.Ebean;
import models.*;
import com.google.common.collect.ImmutableMap;

// withapplication base class provides start()
// that lets us pretend we have the app running
public class ModelsTest extends WithApplication {
    @Before
    public void setUp(){
        // start a fake app using the play in memory db, h2
        // since its in memory db, we dont need to clear data
        // each test we get new db anyways
        start( fakeApplication(inMemoryDatabase(), fakeGlobal()) );
        Ebean.save((List) Yaml.load("test-data.yml"));
    }

    @Test
    public void checkSeedData(){
        User user;
        List<User> users = User.find.all();
        for( Iterator<User> i = users.iterator(); i.hasNext();){
            user = i.next();
            assertNotNull("Name was null.",user.name);
            assertNotNull("Email was null.",user.email);
            assertNotNull("Password was null.",user.password);
        }

        assertEquals("First user wasn't mark.","mark", users.get(0).name);
        assertEquals("Second user wasn't barkbark.","barkbark", users.get(1).name);
        assertEquals("Third user wasn't pete.","pete", users.get(2).name);
    }

    @Test
    public void createAndRetrieveUser(){
        // save user into db
        new User("new@gmail.com", "newuserguy", "password").save();
        // find user
        User t = User.find.where().eq("email", "new@gmail.com").findUnique();
        // see if query returned anything
        assertNotNull(t);
        // see if the names are the same
        assertEquals("newuserguy", t.name);
        assertEquals("Size didn't increase.", 4, User.find.all().size());
    }

    @Test
    public void tryAuthenticateUser() {
        assertNotNull(User.authenticate("lol@example.com", "password"));
        assertNull(User.authenticate("lol@gmail.com", "hello"));
        assertNull(User.authenticate("mark@gmail.com", "wrongone"));
    }

    @Test
    public void createThenFindGames(){
        Game h = Game.create("Mug War",   new Date(), "mark") ;
        Game g = Game.create("Mug War 2", new Date(), "barkbark");
        List<Game> games = Game.find.all();
        assertEquals(games.size(), 2);

        assertEquals("Size isnt 3.", 3, User.find.all().size());
        assertNotNull("Couldn't find release date.", g.release );
        assertEquals("Couldn't find game.", "Mug War 2", g.title );
        assertEquals("Couldn't find developer name.", "barkbark", g.developer.name);
        assertEquals("Couldn't find developer email.", "bark@example.com", g.developer.email);

        List<Game> games_by_mark = Game.findDevelopedBy("mark");
        assertEquals(games_by_mark.size(), 1, games_by_mark.size());
        assertEquals("Problem with name.", "mark", games_by_mark.get(0).developer.name);
        assertEquals("Problem with email.", "lol@example.com", games_by_mark.get(0).developer.email);

    }
}
