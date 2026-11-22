package be.esmay.atlas.api.lock;

/**
 * The panel could not be reached, so nothing was granted.
 */
public final class PanelUnreachable extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Describes what could not be done.
     *
     * @param asked what this server asked the panel for
     */
    public PanelUnreachable(String asked) {
        super("the panel could not be reached for " + asked);
    }
}
