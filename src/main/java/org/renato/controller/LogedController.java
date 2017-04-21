package org.renato.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import org.renato.Navigation;
import org.renato.service.user.UserService;

import java.net.URL;
import java.util.ResourceBundle;

public class LogedController implements Initializable {

    private UserService userService;


    @FXML
    private MenuItem logout;

    @FXML
    private MenuItem quit;


    @FXML
    private Label welcomelabel;

    @FXML
    void logout(ActionEvent event) {

       Navigation.getInstance().back();
    }

    @FXML
    void quit(ActionEvent event) {
        System.exit(0);

    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       // welcomelabel.setText("Welcome " + userService.getUserAuth());
    }
}
