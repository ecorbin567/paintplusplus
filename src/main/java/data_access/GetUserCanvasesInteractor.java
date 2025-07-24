package data_access;

import entity.ActionHistory;
import entity.DrawingCanvas;
import entity.User;

import java.util.ArrayList;
import java.util.List;

/** Use case: retrieving all the documents for a certain user. **/
public class GetUserCanvasesInteractor {
    private final User user;
    private final UserDataAccessInterface userDataAccessInterface;

    public GetUserCanvasesInteractor(User user, UserDataAccessInterface userDataAccessInterface) {
        this.user = user;
        this.userDataAccessInterface = userDataAccessInterface;
    }

    public List<ActionHistory> getUserCanvases() {
        return userDataAccessInterface.getAllCanvases(user);
    }
}
