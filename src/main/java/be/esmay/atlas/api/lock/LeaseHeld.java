package be.esmay.atlas.api.lock;

/**
 * Another server held the name for the whole wait.
 */
public final class LeaseHeld extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Describes the name that stayed held.
     *
     * @param name the lease's name
     */
    public LeaseHeld(String name) {
        super("another server holds the lease on " + name);
    }
}
