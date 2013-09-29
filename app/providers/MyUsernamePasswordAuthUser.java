package providers;

/**
 * Created with IntelliJ IDEA.
 * Author: Yelnil Gabo
 * Date: 9/29/13
 * Time: 5:46 AM
 */
import providers.MyUsernamePasswordAuthProvider.MySignup;

import com.feth.play.module.pa.providers.password.UsernamePasswordAuthUser;
import com.feth.play.module.pa.user.NameIdentity;

public class MyUsernamePasswordAuthUser extends UsernamePasswordAuthUser
        implements NameIdentity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final String name;

    public MyUsernamePasswordAuthUser(final MySignup signup) {
        super(signup.password, signup.email);
        this.name = signup.name;
    }

    /**
     * Used for password reset only - do not use this to signup a user!
     * @param password
     */
    public MyUsernamePasswordAuthUser(final String password) {
        super(password, null);
        name = null;
    }

    @Override
    public String getName() {
        return name;
    }
}
