package interval;

public class IntervalOutOfScopeException extends Exception {
	private static final long serialVersionUID = -4976916757021975548L;

	public IntervalOutOfScopeException(String errorMessage) {
		super(errorMessage);
	}
}
