package view.topmenubar;

/**
 * Listener for menu item actions.
 */
public interface MenuActionListener {

    /**
     * Called when a menu item is selected.
     *
     * @param command the menu command
     */
    void onMenuItemSelected(String command);
}
