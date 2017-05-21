package org.renato.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.renato.Navigation;
import org.renato.model.pojos.Candidate;
import org.renato.model.pojos.Company;
import org.renato.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class LoginController implements Initializable {

    @Autowired
    private UserService userService;
    @Autowired
    private Navigation navigation;

    private int myId;

    private boolean isLogin = true;

    private boolean isCompany = true;


    @Autowired
    public LoginController(UserService userService, Navigation navigation) {
        this.userService = userService;
        this.navigation = navigation;
    }

    public LoginController() {
    }

    @FXML // fx:id="entityTypeLabel"
    private Label entityTypeLabel; // Value injected by FXMLLoader

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

            if (!userService.exists(cadetTextField.getText())) {

                verificationText.setText("User doesn't exists");
                verificationText.setVisible(true);

            } else {

                if (!userService.authenticate(cadetTextField.getText(), passwordField.getText())) {

                    verificationText.setText(" WRONG PASSWORD");
                    verificationText.setVisible(true);

                } else {

                    verificationText.setText("WELCOME");
                    verificationText.setVisible(true);

                    if(!isCompany){

                        userService.setCandidateLogged(userService.findCadetByName(cadetTextField.getText()));

                    }else {

                        userService.setCompanyLogged(userService.findCompanyByName(cadetTextField.getText()));
                    }

                    userService.setIsCompany(isCompany);
                    navigation.loadScreen("Menu");
                }
            }


        } else {

            if (userService.findCadetByMail(emailTextField.getText()) == null) {

                if (cadetTextField.getText().isEmpty() || passwordField.getText().isEmpty() || emailTextField.getText().isEmpty() || mottoTexField.getText().isEmpty()) {

                    verificationText.setText("All blank spaces, SHALL NOT BE EMPTY");
                    verificationText.setVisible(true);
                } else {

                    if (!isCompany) {

                        userService.addCadet(new Candidate(passwordField.getText(), emailTextField.getText(), cadetTextField.getText(), mottoTexField.getText()));
                        verificationText.setText("Account Successfully created");
                        verificationText.setVisible(true);

                    } else {

                        userService.addCompany(new Company(passwordField.getText(), emailTextField.getText(), cadetTextField.getText(), mottoTexField.getText()));
                        verificationText.setText("Account Successfully created");
                        verificationText.setVisible(true);
                    }
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
    void imCadet() {

        isCompany = false;
        userService.setIsCompany(isCompany);
        navigation.loadScreen("LoginController");
    }

    @FXML
    void imCompany() {

        isCompany = true;
        userService.setIsCompany(isCompany);
        navigation.loadScreen("LoginController");

    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (entityTypeLabel != null) {

            if (isCompany) {

                entityTypeLabel.setText("Company");

            } else {

                entityTypeLabel.setText("Candidate");

            }
        }

    }
}
