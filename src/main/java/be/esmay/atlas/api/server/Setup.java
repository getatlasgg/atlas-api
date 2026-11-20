package be.esmay.atlas.api.server;

/**
 * The map setup this server runs for a map deploy. A template that asks for
 * setup starts a setup server with the deployed map, and the setup files of
 * that map are kept once the setup finishes.
 */
public interface Setup {

    /**
     * The name of the map being set up, as the builder typed it.
     *
     * @return the map's name
     */
    String map();

    /**
     * Saves the setup files of the map into its draft, sends the builder back
     * to the build server and stops this server. Calling it again saves
     * nothing new.
     */
    void finish();
}
