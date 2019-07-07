package au.com.tabcorp.data.error;

public class BetTypeNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BetTypeNotFoundException(Long id) {
        super("Bet Type not found : " + id);
    }

}
