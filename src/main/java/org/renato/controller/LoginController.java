package org.renato.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.renato.Navigation;
import org.renato.model.userTypes.Cadet;
import org.renato.service.user.UserService;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private UserService userService;
    private Navigation navigation;
    private boolean isLogin = true;

    public LoginController(UserService userService, Navigation navigation) {
        this.userService = userService;
        this.navigation = navigation;
    }

    public LoginController() {
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setNavigation(Navigation navigation) {
        this.navigation = navigation;
    }

    @FXML // fx:id="loginButton"
    private Button loginButton; // Value injected by FXMLLoader

    @FXML // fx:id="passwordField"
    private PasswordField passwordField; // Value injected by FXMLLoader

    @FXML // fx:id="cadetTextField"
    private TextField cadetTextField; // Value injected by FXMLLoader

    @FXML // fx:id="emailTextField"
    private TextField emailTextField; // Value injected by FXMLLoader

    @FXML // fx:id="mottoTextField"
    private TextField mottoTexField; //Value injected by FXMLLoader

    @FXML // fx:id="registerLink"
    private Hyperlink registerLink; // Value injected by FXMLLoader

    @FXML // fx:id="emailLabel"
    private Label emailLabel; // Value injected by FXMLLoader

    @FXML //fx:id="motto"
    private Label mottoLabel; //Value injected by FXMLLoader

    @FXML // fx:id="verificationText"
    private Label verificationText; // Value injected by FXMLLoader

    @FXML
    private Button companyButton;

    @FXML
    private Button cadetButton;


    @FXML
    public void onLogin(ActionEvent actionEvent) {


        if (isLogin) {

            if (userService.findByName(cadetTextField.getText()) == null) {

                verificationText.setText("User doesn't exists");
                verificationText.setVisible(true);

            } else {


                if (!userService.authenticate(cadetTextField.getText(), passwordField.getText())) {
                    verificationText.setText(" WRONG PASSWORD");
                    verificationText.setVisible(true);

                } else {
                    verificationText.setText("WELCOME");
                    verificationText.setVisible(true);
                    navigation.loadScreen("Menu");

                }
            }
        } else {

            if (userService.findByMail(emailTextField.getText()) == null) {

                if(cadetTextField.getText().isEmpty() || passwordField.getText().isEmpty() || emailTextField.getText().isEmpty() || mottoTexField.getText().isEmpty()) {

                    verificationText.setText("All blank spaces, SHALL NOT BE EMPTY");
                    verificationText.setVisible(true);
                }else {

                    userService.addUser(new Cadet(passwordField.getText(), emailTextField.getText(), cadetTextField.getText(), mottoTexField.getText(), "cadet"));
                    verificationText.setText("Account Successfully created");
                    verificationText.setVisible(true);
                }

            } else {
                verificationText.setText("USER ALREADY EXIST");
                verificationText.setVisible(true);
            }
        }
    }

    @FXML
    void onRegister(ActionEvent event) {

        if (isLogin) {
            verificationText.setVisible(false);
            registerLink.setText("CLOSE");
            emailLabel.setVisible(true);
            emailTextField.setVisible(true);
            mottoLabel.setVisible(true);
            mottoTexField.setVisible(true);
            loginButton.setText("REGISTER");
            isLogin = false;
        } else {
            registerLink.setText("REGISTER");
            verificationText.setVisible(false);
            emailLabel.setVisible(false);
            emailTextField.setVisible(false);
            mottoTexField.setVisible(false);
            mottoLabel.setVisible(false);
            loginButton.setText("LOGIN");
            isLogin = true;
        }
    }


    @FXML
    void imCadet(ActionEvent event) {

        navigation.loadScreen("LoginController");

    }

    @FXML
    void imCompany(ActionEvent event) {

        navigation.loadScreen("LoginController");

    }

    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
