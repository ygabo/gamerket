/**
 * Created with IntelliJ IDEA.
 * Author: Yelnil Gabo
 * Date: 9/18/13
 * Time: 4:33 AM
 */
import play.*;
import play.libs.*;
import java.util.*;
import com.avaje.ebean.Ebean;
import models.*;

// global class so we can load initial data
// for testing
public class Global extends GlobalSettings {
    @Override
    public void onStart(Application app) {
        if (User.find.findRowCount() == 0) {
            Ebean.save((List) Yaml.load("initial-data.yml"));
        }
    }
}
