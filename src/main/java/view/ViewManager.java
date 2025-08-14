package view;

import java.awt.CardLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;

import interface_adapter.ViewManagerModel;

/**
 * Manages visible views using a CardLayout.
 * Listens to the ViewManagerModel and switches the shown view by name.
 */
public class ViewManager implements PropertyChangeListener {
    private final CardLayout cardLayout;
    private final JPanel views;

    /**
     * Creates a view manager.
     *
     * @param views            the container holding all views (cards)
     * @param cardLayout       the CardLayout controlling the container
     * @param viewManagerModel the model emitting view name changes
     */
    public ViewManager(JPanel views, CardLayout cardLayout, ViewManagerModel viewManagerModel) {
        this.views = views;
        this.cardLayout = cardLayout;
        viewManagerModel.addPropertyChangeListener(this);
    }

    /**
     * Responds to model changes and shows the requested view.
     * Expects property name "state" with a String view name.
     *
     * @param evt the property change event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final String viewModelName = (String) evt.getNewValue();
            cardLayout.show(views, viewModelName);
        }
    }
}
