package org.renato.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import org.renato.Navigation;
import org.renato.model.userTypes.Cadet;
import org.renato.model.userTypes.Company;
import org.renato.service.user.UserService;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LogedController implements Initializable {

    private UserService userService;
    private Navigation navigation;
    private List<Company> companysList;
    private List<Cadet> cadetsList;
    int iterator = 0;


    @FXML
    private MenuItem logout;

    @FXML
    private MenuItem quit;

    @FXML
    private Label welcomelabel;

    @FXML
    private Button previousButton;

    @FXML
    private Button nextButton;

    @FXML
    private Button moreInfoButton;

    @FXML
    private Button matchButton;

    @FXML
    private Label textToShow;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        welcomelabel.setText("Welcome " + userService.getUserAuth());
        companysList = userService.getCompanies();

    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setNavigation(Navigation navigation) {
        this.navigation = navigation;
    }

    public void previous(ActionEvent actionEvent) {

        //TODO CHECK IF WE REally want a previous button

        if (iterator>=0 && iterator < companysList.size()) {

            textToShow.setText(companysList.get(iterator).getMotto());
            iterator--;

        } else {
            textToShow.setText("NO MORE ITEMS TO SHOW ");
            iterator = 0;
        }

    }

    public void next(ActionEvent actionEvent) {

        if (companysList.size() > iterator) {

            textToShow.setText(companysList.get(iterator).getMotto());
            iterator++;

        } else {
            textToShow.setText("NO MORE ITEMS TO SHOW ");
            iterator = 0;
        }
    }

    public void moreInfo(ActionEvent actionEvent) {


    }

    public void match(ActionEvent actionEvent) {
    }

    public void logout(ActionEvent actionEvent) {
        navigation.loadScreen("LoginController");
    }

    public void quit(ActionEvent actionEvent) {
        System.exit(0);

    }
}
