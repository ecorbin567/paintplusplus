package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

/**
 * The View for when the user is logging into the program.
 */
public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {
    private final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();

    private final JPasswordField passwordInputField = new JPasswordField(15);

    private final JButton logIn;
    private final LoginController loginController;

    /**
     * Creates the login view, wires listeners, and lays out components.
     *
     * @param loginViewModel the view model providing state and errors
     * @param controller     the controller that executes the login use case
     */
    public LoginView(LoginViewModel loginViewModel, LoginController controller) {
        this.setPreferredSize(new Dimension(ViewConstants.FOUR_HUNDRED, ViewConstants.FOUR_HUNDRED));
        this.loginController = controller;
        loginViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Log In to Paint++");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Username"), usernameInputField);
        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);

        final JPanel buttons = new JPanel();
        logIn = new JButton("log in");
        buttons.add(logIn);
        final JButton cancel = new JButton("cancel");
        buttons.add(cancel);

        logIn.addActionListener(
                evt -> {
                    if (evt.getSource().equals(logIn)) {
                        final LoginState currentState = loginViewModel.getState();

                        loginController.execute(
                                currentState.getUsername(),
                                currentState.getPassword()
                        );
                    }
                }
        );

        cancel.addActionListener(this);

        usernameInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LoginState currentState = loginViewModel.getState();
                currentState.setUsername(usernameInputField.getText());
                loginViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LoginState currentState = loginViewModel.getState();
                currentState.setPassword(new String(passwordInputField.getPassword()));
                loginViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        this.add(title);
        this.add(usernameInfo);
        this.add(usernameErrorField);
        this.add(passwordInfo);
        this.add(buttons);
    }

    /**
     * Reacts to a button click.
     *
     * @param evt the action event
     */
    public void actionPerformed(ActionEvent evt) {

    }

    /**
     * Receives updates from the view model and refreshes the fields.
     *
     * @param evt the property change event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final LoginState state = (LoginState) evt.getNewValue();
        setFields(state);
        usernameErrorField.setText(state.getLoginError());
    }

    /**
     * Updates input fields based on the given state.
     *
     * @param state the current login state
     */
    private void setFields(LoginState state) {
        usernameInputField.setText(state.getUsername());
    }

    /**
     * Returns the logical name of this view.
     *
     * @return the view name
     */
    public String getViewName() {
        return "log in";
    }
}
