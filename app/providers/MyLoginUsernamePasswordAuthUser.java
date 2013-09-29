package providers;

/**
 * Created with IntelliJ IDEA.
 * Author: Yelnil Gabo
 * Date: 9/29/13
 * Time: 5:45 AM
 */
import com.feth.play.module.pa.providers.password.DefaultUsernamePasswordAuthUser;

public class MyLoginUsernamePasswordAuthUser extends
        DefaultUsernamePasswordAuthUser {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * The session timeout in seconds
     * Defaults to two weeks
     */
    final static long SESSION_TIMEOUT = 24 * 14 * 3600;
    private long expiration;

    /**
     * For logging the user in automatically
     *
     * @param email
     */
    public MyLoginUsernamePasswordAuthUser(final String email) {
        this(null, email);
    }

    public MyLoginUsernamePasswordAuthUser(final String clearPassword,
                                           final String email) {
        super(clearPassword, email);

        expiration = System.currentTimeMillis() + 1000 * SESSION_TIMEOUT;
    }

    @Override
    public long expires() {
        return expiration;
    }

}