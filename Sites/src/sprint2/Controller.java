package sprint2;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

@SuppressWarnings({ "unused", "restriction" })
public class Controller implements Initializable {

    // Backend Variables
    File file = new File("demoData.ser");
    PersistenceManager pm = new PersistenceManager();
    @SuppressWarnings("static-access")
    SiteManager sm = pm.read(file);
    Member member;

    // Hamburger Menu Variables
    private boolean pressed = false;
    private HBox drawerSave;
    @FXML
    private JFXHamburger menu;

    @FXML
    private HBox drawer;

    // Main Window
    @FXML
    private BorderPane MainWindow;

    // Main Content Window
    @FXML
    private VBox window;

    // Main Window Buttons
    @FXML
    private Button groups, register, login, cancel, profile, searchButton, home;

    @FXML
    private Separator topMenuSeparator, bottomMenuSeparator;

    // Text for search field
    @FXML
    private TextField searchText;

    // For loading back pages
    @FXML
    private Pane previous;
    
    // For top navigation
    @FXML
    private MenuItem saveData, loadData, rick, tyler, mallory, michael;

    // Custom List of Member's Group Buttons
    @FXML
    private ArrayList<Button> memberGroups = new ArrayList<Button>();

    // Loads the New Group Form
    @FXML
    public void loadNewGroupPane(ActionEvent event) {
        try {
            Pane newGroupPane = FXMLLoader.load(getClass().getResource("/NewGroupPane.fxml"));

            // Clears the window for new content and saves the old for restore
            if (window.getChildren().size() != 0) {
                previous = (Pane) window.getChildren().get(0);
                window.getChildren().clear();
            }

            // Traversing FXML file & Assigning Nodes
            AnchorPane tempAP = (AnchorPane) newGroupPane.getChildren().get(0);
            VBox tempVB = (VBox) tempAP.getChildren().get(0);

            TextField title = (TextField) tempVB.getChildren().get(1);
            TextArea description = (TextArea) tempVB.getChildren().get(2);

            HBox tempHB = (HBox) tempVB.getChildren().get(tempVB.getChildren().size() - 1);
            Button create = (Button) tempHB.getChildren().get(1);
            Button cancel = (Button) tempHB.getChildren().get(2);

            // Event Listeners
            title.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    if (keyEvent.getCode() == KeyCode.ENTER) {
                        description.requestFocus();
                    }
                    if (keyEvent.getCode() == KeyCode.ESCAPE) {
                        title.setText("");
                    }

                }
            });

            description.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    if (keyEvent.getCode() == KeyCode.ESCAPE) {
                        description.setText("");
                    }

                }
            });

            create.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    boolean tempB = false;
                    if ((title.getText().toString().length() != 0)
                            && (description.getText().toString().length() != 0)) {
                        tempB = sm.addGroup(title.getText().toString(), description.getText().toString(),
                                LocalDateTime.now());

                        // save new data
                        savePM();

                        // move frames
                        loadGroupPane(event, title.getText().toString());
                    } else {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        String t = "Invalid Title";
                        String d = "Invalid Description";
                        String message = "";
                        if (title.getText().toString().length() == 0)
                            message += t;
                        if (description.getText().toString().length() == 0) {
                            if (message.length() > 0) {
                                message += " and ";
                            }
                            message += d;
                        }
                        alert.setTitle("Input Error");
                        alert.setHeaderText(null);
                        alert.setContentText(message);

                        alert.showAndWait();
                    }
                }
            });

            cancel.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (window.getChildren().size() != 0) {
                        window.getChildren().clear();
                        window.getChildren().add(previous);
                    }
                }
            });

            // Load the Window for New Group Pane
            window.getChildren().add(newGroupPane);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Loads the New Member Form
    @FXML
    public void loadNewMemberPane(ActionEvent event) {
        try {
            Pane newMemberPane = FXMLLoader.load(getClass().getResource("/NewMemberPane.fxml"));

            // Clears the window for new content and saves the old for restore
            if (window.getChildren().size() != 0) {
                previous = (Pane) window.getChildren().get(0);
                window.getChildren().clear();
            }

            // Traversing FXML file & Assigning Nodes
            AnchorPane tempAP = (AnchorPane) newMemberPane.getChildren().get(0);
            VBox tempVB = (VBox) tempAP.getChildren().get(0);

            TextField firstName = (TextField) tempVB.getChildren().get(1);
            TextField lastName = (TextField) tempVB.getChildren().get(2);
            TextField emailAddress = (TextField) tempVB.getChildren().get(3);
            TextField screenName = (TextField) tempVB.getChildren().get(4);

            HBox tempHB = (HBox) tempVB.getChildren().get(tempVB.getChildren().size() - 1);
            Button create = (Button) tempHB.getChildren().get(1);
            cancel = (Button) tempHB.getChildren().get(2);

            // Event Listeners

            firstName.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    if (keyEvent.getCode() == KeyCode.ENTER) {
                        lastName.requestFocus();
                    }
                    if (keyEvent.getCode() == KeyCode.ESCAPE) {
                        firstName.setText("");
                    }

                }
            });

            lastName.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    if (keyEvent.getCode() == KeyCode.ENTER) {
                        screenName.requestFocus();
                    }
                    if (keyEvent.getCode() == KeyCode.ESCAPE) {
                        lastName.setText("");
                    }

                }
            });

            screenName.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    if (keyEvent.getCode() == KeyCode.ENTER) {
                        emailAddress.requestFocus();
                    }
                    if (keyEvent.getCode() == KeyCode.ESCAPE) {
                        screenName.setText("");
                    }

                }
            });

            emailAddress.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    if (keyEvent.getCode() == KeyCode.ENTER) {
                        create.fire();
                    }
                    if (keyEvent.getCode() == KeyCode.ESCAPE) {
                        emailAddress.setText("");
                    }

                }
            });

            create.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    boolean tempB = false;
                    if ((firstName.getText().toString().length() != 0) && (lastName.getText().toString().length() != 0)
                            && (screenName.getText().toString().length() != 0)
                            && (emailAddress.getText().toString().length() != 0)) {
                        sm.addMember(firstName.getText().toString(), lastName.getText().toString(),
                                screenName.getText().toString(), emailAddress.getText().toString(),
                                LocalDateTime.now());

                        // save data
                        savePM();

                        // move frames
                        loadLoginPane(event);
                    }

                }
            });

            cancel.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (window.getChildren().size() != 0) {
                        window.getChildren().clear();
                        window.getChildren().add(previous);
                    }
                }
            });

            // Load a New Member Pane
            window.getChildren().add(newMemberPane);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Loads the New Question Form
    @FXML
    public void loadNewQuestionPane(ActionEvent event, String groupTitle) {
        try {
            Pane newQuestionPane = FXMLLoader.load(getClass().getResource("/NewQuestionPane.fxml"));

            if (window.getChildren().size() != 0) {
                previous = (Pane) window.getChildren().get(0);
                window.getChildren().clear();
            }

            // traverse Pane
            AnchorPane tempAP = (AnchorPane) newQuestionPane.getChildren().get(0);
            VBox tempVB = (VBox) tempAP.getChildren().get(0);

            // grab fields
            TextField title = (TextField) tempVB.getChildren().get(1);
            TextArea details = (TextArea) tempVB.getChildren().get(2);

            // grab HBox because its children are the buttons
            HBox tempHB = (HBox) tempVB.getChildren().get(3);
            Button askQuestion = (Button) tempHB.getChildren().get(1);
            Button cancelQuestion = (Button) tempHB.getChildren().get(2);

            // Event Listeners
            title.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    if (keyEvent.getCode() == KeyCode.ENTER) {
                        details.requestFocus();
                    }
                    if (keyEvent.getCode() == KeyCode.ESCAPE) {
                        title.setText("");
                    }

                }
            });

            details.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    if (keyEvent.getCode() == KeyCode.ESCAPE) {
                        details.setText("");
                    }

                }
            });

            // create askQuestion EventHandler
            askQuestion.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Group group = sm.getGroup(groupTitle);

                    // create question from title and details
                    Question question = new Question(title.getText().toString(), details.getText().toString(),
                            LocalDateTime.now());

                    if (title.getText().toString().length() != 0 && details.getText().toString().length() != 0) {
                        // add question to SiteManager
                        sm.getMember(member.getEmailAddress()).addQuestion(group, question, LocalDateTime.now());

                        // save data
                        savePM();

                        // move frames
                        loadQuestionPane(event, question.getTitle());
                    } else {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        String t = "Invalid Title";
                        String d = "Invalid Details";
                        String message = "";
                        if (title.getText().toString().length() == 0)
                            message += t;
                        if (details.getText().toString().length() == 0) {
                            if (message.length() > 0) {
                                message += " and ";
                            }
                            message += d;
                        }
                        alert.setTitle("Input Error");
                        alert.setHeaderText(null);
                        alert.setContentText(message);

                        alert.showAndWait();
                    }

                }
            });

            // create cancelQuestion EventHandler
            cancelQuestion.setOnAction(new EventHandler<ActionEvent>() {
                // set to previous pane
                @Override
                public void handle(ActionEvent event) {
                    if (window.getChildren().size() != 0) {
                        window.getChildren().clear();
                        window.getChildren().add(previous);
                    }
                }
            });

            // set the window
            window.getChildren().add(newQuestionPane);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Loads the Login Form
    @FXML
    public void loadLoginPane(ActionEvent event) {
        try {
            Pane newLoginPane = FXMLLoader.load(getClass().getResource("/LoginPane.fxml"));

            // Clears the window for new content and saves the old for restore
            if (window.getChildren().size() != 0) {
                previous = (Pane) window.getChildren().get(0);
                window.getChildren().clear();
            }

            // Traversing FXML file & Assigning Nodes
            AnchorPane tempAP = (AnchorPane) newLoginPane.getChildren().get(0);
            VBox tempVB = (VBox) tempAP.getChildren().get(0);

            TextField userName = (TextField) tempVB.getChildren().get(1);
            TextField password = (TextField) tempVB.getChildren().get(2);

            HBox tempHB = (HBox) tempVB.getChildren().get(tempVB.getChildren().size() - 1);
            Button action = (Button) tempHB.getChildren().get(1);
            cancel = (Button) tempHB.getChildren().get(2);

            // Event Listeners
            userName.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    if (keyEvent.getCode() == KeyCode.ENTER) {
                        password.requestFocus();
                    }
                    if (keyEvent.getCode() == KeyCode.ESCAPE) {
                        userName.setText("");
                    }

                }
            });

            password.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    if (keyEvent.getCode() == KeyCode.ENTER) {
                        action.fire();
                    }
                    if (keyEvent.getCode() == KeyCode.ESCAPE) {
                        password.setText("");
                    }

                }
            });

            action.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    boolean tempB = false;

                    if (sm.getMember(userName.getText().toString()) != null
                            && (password.getText().toString().length() != 0)) {
                        member = sm.getMember(userName.getText().toString());

                        login.setText("Logout");
                        register.setVisible(false);

                        loadMemberPane(event, userName.getText().toString());
                        reloadDrawer();
                    } else if(member != null){
                        login.setText("Logout");
                        register.setVisible(false);

                        loadMemberPane(event, member.getEmailAddress());
                        reloadDrawer();
                    }else {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        String un = "Invalid User";
                        String pass = "Invalid Passowrd";
                        String message = "";
                        if (sm.getMember(userName.getText().toString()) == null)
                            message += un;
                        if (password.getText().toString().length() == 0) {
                            if (message.length() > 0) {
                                message += " and/or ";
                            }
                            message += pass;
                        }
                        alert.setTitle("Input Error");
                        alert.setHeaderText(null);
                        alert.setContentText(message);

                        alert.showAndWait();
                    }
                }
            });

            cancel.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (window.getChildren().size() != 0) {
                        window.getChildren().clear();
                        window.getChildren().add(previous);
                    }
                }
            });
            

            // Loads the login form
            window.getChildren().add(newLoginPane);

            if(member!=null) {
            	action.fire();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Helper Method to reload the drawer
    public void reloadDrawer() {
        VBox buttonVbox = (VBox) drawer.getChildren().get(0);
        memberGroups.clear();
        for (Group g : member.getGroups()) {
            JFXButton tempButton;

            try {
                tempButton = FXMLLoader.load(getClass().getResource("/MenuButton.fxml"));
                tempButton.setText(g.getTitle());
                tempButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        loadGroupPane(event, g.getTitle());
                    }
                });
                memberGroups.add(tempButton);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (int i = buttonVbox.getChildren().size(); i > 0; i--) {
            buttonVbox.getChildren().remove(i - 1);
        }
        buttonVbox.getChildren().add(home);
        buttonVbox.getChildren().add(groups);
        buttonVbox.getChildren().add(topMenuSeparator);

        for (Button b : memberGroups) {
            buttonVbox.getChildren().add(b);
        }

        buttonVbox.getChildren().add(bottomMenuSeparator);
        buttonVbox.getChildren().add(profile);
        buttonVbox.getChildren().add(login);
        buttonVbox.getChildren().add(register);
    }

    // Loads a Group
    @FXML
    public void loadGroupPane(ActionEvent event, String title) {
        try {
            Pane GroupPage = FXMLLoader.load(getClass().getResource("/GroupPane.fxml"));

            if (window.getChildren().size() != 0)
                window.getChildren().clear();

            // traverse
            VBox masterVBox = (VBox) GroupPage.getChildren().get(0);
            Label groupTitle = (Label) masterVBox.getChildren().get(0);

            HBox innerHBox = (HBox) masterVBox.getChildren().get(1);
            ListView<String> listView = (ListView<String>) masterVBox.getChildren().get(2);

            VBox vBoxTextArea = (VBox) innerHBox.getChildren().get(0);
            TextArea description = (TextArea) vBoxTextArea.getChildren().get(0);

            VBox vBoxButtons = (VBox) innerHBox.getChildren().get(1);
            Button join = (Button) vBoxButtons.getChildren().get(0);
            Button askQuestion = (Button) vBoxButtons.getChildren().get(2);

            // set group Title
            groupTitle.setText(title);

            // set group description
            description.setText(sm.getGroup(title).getDescription());
            description.setStyle("-jfx-unfocus-color: #d5d5d5;");

            // join Event Handler
            join.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    sm.getMember(member.getEmailAddress()).joinGroup(sm.getGroup(title), LocalDateTime.now());
                    member = sm.getMember(member.getEmailAddress());

                    // save new member
                    savePM();

                    // move frames
                    askQuestion.setDisable(false);
                    loadGroupPane(event, title);
                    reloadDrawer();
                }
            });

            // askQuesiton Event Handler
            askQuestion.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    loadNewQuestionPane(event, title);
                }
            });

            // add questions for this group to ListView
            ArrayList<Question> questions = (ArrayList<Question>) sm.getGroup(title).getQuestions();
            
            // Sort Questions
            Collections.sort(questions, new PostPointsComparator());
            Collections.reverse(questions);
            
            for (Question q : questions) {
                listView.getItems().add(q.getTitle());
            }

            listView.setCellFactory(lv -> {
                ListCell<String> cell = new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(item == null ? "" : item.toString());
                    }
                };
                cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (!cell.isEmpty()) {
                            loadQuestionPane(event, listView.getSelectionModel().getSelectedItem().toString());
                        }
                    }
                });
                return cell;
            });

            // set initial view of buttons

            for (Member m : sm.getGroup(title).getMembers()) {
                if (member != null) {
                    if (member.getEmailAddress().compareTo(m.getEmailAddress()) == 0) {
                        join.setDisable(true);
                        askQuestion.setDisable(false);
                        break;
                    } else {
                        askQuestion.setDisable(true);
                    }

                } else {
                    join.setDisable(true);
                    askQuestion.setDisable(true);
                }
            }

            if (sm.getGroup(title).getMembers().size() == 0) {
                askQuestion.setDisable(true);
            }

            if (member == null) {
                join.setDisable(true);
            }

            // Load a Group's Page
            window.getChildren().add(GroupPage);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Loads a list of Groups to a list view in a pane
    @FXML
    public void loadGroupsPane(ActionEvent event) {
        try {
            Pane groupsPage = FXMLLoader.load(getClass().getResource("/GroupsPane.fxml"));

            // Clears the window for new content
            if (window.getChildren().size() != 0)
                window.getChildren().clear();

            // Traversing FXML file & Assigning Nodes
            VBox masterVBox = (VBox) groupsPage.getChildren().get(0);
            Label groupTitle = (Label) masterVBox.getChildren().get(0);
            ListView<String> listView = (ListView<String>) masterVBox.getChildren().get(1);
            HBox masterHBox = (HBox) masterVBox.getChildren().get(2);
            Button newGroupButton = (Button) masterHBox.getChildren().get(1);

            // Event Listeners
            newGroupButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    loadNewGroupPane(event);
                }
            });

            if (member == null) {
                newGroupButton.setVisible(false);

            } else {
                newGroupButton.setVisible(true);
            }
            
            // Groups List View
            ArrayList<Group> groups = (ArrayList<Group>) sm.getGroups();
            
            // Sort Groups
            Collections.sort(groups, new GroupOverallActiveComparator());
            Collections.reverse(groups);

            for (Group g : groups) {
                listView.getItems().add(g.getTitle());
            }

            // Show groups in a dynamic list
            listView.setCellFactory(lv -> {
                ListCell<String> cell = new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(item == null ? "" : item.toString());
                    }
                };
                cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (!cell.isEmpty()) {
                            loadGroupPane(event, listView.getSelectionModel().getSelectedItem().toString());
                        }
                    }
                });
                return cell;
            });

            window.getChildren().add(groupsPage);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Loads a member profile pane with a list of its groups, questions, and stats
    @FXML
    public void loadMemberPane(ActionEvent event, String email) {
        try {
            Pane MemberPage = FXMLLoader.load(getClass().getResource("/UserPane.fxml"));

            // Need a copy of the member to load
            Member memberLoaded = sm.getMember(email);

            // Clears the window for new content
            if (window.getChildren().size() != 0)
                window.getChildren().clear();

            // Traversing FXML file & Assigning Nodes
            VBox masterVBox = (VBox) MemberPage.getChildren().get(0);
            Label screenName = (Label) masterVBox.getChildren().get(0);

            HBox userDetailsHB = (HBox) masterVBox.getChildren().get(1);
            TabPane tabPane = (TabPane) masterVBox.getChildren().get(2);

            // inside HB
            VBox innerVBox = (VBox) userDetailsHB.getChildren().get(0);
            Text firstAndLastName = (Text) innerVBox.getChildren().get(0);
            Text memberEmail = (Text) innerVBox.getChildren().get(1);
            Text activity = (Text) innerVBox.getChildren().get(2);
            Text questionsAsked = (Text) innerVBox.getChildren().get(3);

            // inside tabPane
            Tab questionsTab = (Tab) tabPane.getTabs().get(0);
            AnchorPane questionAnchorPane = (AnchorPane) questionsTab.getContent();
            ListView<String> questionsListView = (ListView<String>) questionAnchorPane.getChildren().get(0);

            Tab groupsTab = (Tab) tabPane.getTabs().get(1);
            AnchorPane groupsAnchorPane = (AnchorPane) groupsTab.getContent();
            ListView<String> groupsListView = (ListView<String>) groupsAnchorPane.getChildren().get(0);

            // set screenName
            screenName.setText(memberLoaded.getScreenName());

            // set user details
            firstAndLastName.setText(memberLoaded.getFirstName() + " " + memberLoaded.getLastName());
            memberEmail.setText(memberLoaded.getEmailAddress());

            // get user activity and questions asked
            ArrayList<Group> groups = (ArrayList) memberLoaded.getGroups();
            int questionsCount = 0;
            int activityCount = 0;
            for (Group g : groups) {
                questionsCount += memberLoaded.getQuestions(g).size();
                activityCount += memberLoaded.getAnswers(g).size();
                activityCount += memberLoaded.getQuestions(g).size();
            }

            activity.setText("Activity: " + activityCount);
            questionsAsked.setText("Questions Asked: " + questionsCount);

            // Questions List View
            // add questions for this group to ListView
            ArrayList<Question> questions = new ArrayList<Question>();
            for (Group g : sm.getMember(memberLoaded.getEmailAddress()).getGroups()) {
                questions.addAll(g.getQuestions());
            }

            // Sort Questions
            Collections.sort(questions, new PostPointsComparator());
            Collections.reverse(questions);
            
            for (Question q : questions) {
                questionsListView.getItems().add(q.getTitle());
            }

            questionsListView.setCellFactory(lv -> {
                ListCell<String> cell = new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(item == null ? "" : item.toString());
                    }
                };
                cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (!cell.isEmpty()) {
                            loadQuestionPane(event, questionsListView.getSelectionModel().getSelectedItem().toString());
                        }
                    }
                });
                return cell;
            });
            
            // Sort Groups
            Collections.sort(groups, new GroupOverallActiveComparator());
            Collections.reverse(groups);
            

            // Groups List View
            for (Group g : groups) {
                groupsListView.getItems().add(g.getTitle());
            }

            groupsListView.setCellFactory(lv -> {
                ListCell<String> cell = new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(item == null ? "" : item.toString());
                    }
                };
                cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (!cell.isEmpty()) {
                            loadGroupPane(event, groupsListView.getSelectionModel().getSelectedItem().toString());
                        }
                    }
                });
                return cell;
            });

            // Loads a User Profile
            window.getChildren().add(MemberPage);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Loads the default page of the application
    @FXML
    public void loadHomePane(ActionEvent event) {
        try {
            Pane HomePage = FXMLLoader.load(getClass().getResource("/HomePane.fxml"));

            // Clears the window for new content
            if (window.getChildren().size() != 0)
                window.getChildren().clear();

            // Traversing FXML file & Assigning Nodes
            VBox masterVBox = (VBox) HomePage.getChildren().get(0);
            TextArea about = (TextArea) masterVBox.getChildren().get(0);
            TabPane tabPane = (TabPane) masterVBox.getChildren().get(1);

            Tab groupsTab = (Tab) tabPane.getTabs().get(0);
            AnchorPane groupAnchorPane = (AnchorPane) groupsTab.getContent();
            ListView<String> groupsListView = (ListView<String>) groupAnchorPane.getChildren().get(0);

            Tab questionsTab = (Tab) tabPane.getTabs().get(1);
            AnchorPane questionsAnchorPane = (AnchorPane) questionsTab.getContent();
            ListView<String> questionsListView = (ListView<String>) questionsAnchorPane.getChildren().get(0);

            Tab membersTab = (Tab) tabPane.getTabs().get(2);
            AnchorPane membersAnchorPane = (AnchorPane) membersTab.getContent();
            ListView<String> membersListView = (ListView<String>) membersAnchorPane.getChildren().get(0);

            // TODO: Update the About Text
            about.setText(
                    "\tWelcome to Stacky! This open source application is used as a tool for members to ask questions that they cannot find answers to. To get started, all you need to do is register, join a group, and start asking away. Each group is designed around a specific topic, be sure to search for the group that best fits your needs or browse the homepage for topics that interest you. Each question is rated by the users of the group, to help determine the quality of a question at a glance. Answers are classified similarly with the exception that they have the ability to be marked as correct to better help users. If you ask a question and deem it to be correct, it can be marked as a solution to the problem to help others.");

            // Questions List View
            ArrayList<Question> questions = new ArrayList<Question>();
            for (Group g : sm.getGroups()) {
                questions.addAll(g.getQuestions());
            }

            // Sort Questions
            Collections.sort(questions, new PostPointsComparator());
            Collections.reverse(questions);

            for (Question q : questions) {
                questionsListView.getItems().add(q.getTitle());
            }

            questionsListView.setCellFactory(lv -> {
                ListCell<String> cell = new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(item == null ? "" : item.toString());
                    }
                };
                cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (!cell.isEmpty()) {
                            loadQuestionPane(event, questionsListView.getSelectionModel().getSelectedItem().toString());
                        }
                    }
                });
                return cell;
            });

            // Groups List View
            ArrayList<Group> groups = (ArrayList<Group>) sm.getGroups();

            // Sort Groups
            Collections.sort(groups, new GroupOverallActiveComparator());
            Collections.reverse(groups);

            for (Group g : groups) {
                groupsListView.getItems().add(g.getTitle());
            }

            groupsListView.setCellFactory(lv -> {
                ListCell<String> cell = new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(item == null ? "" : item.toString());
                    }
                };
                cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (!cell.isEmpty()) {
                            loadGroupPane(event, groupsListView.getSelectionModel().getSelectedItem().toString());
                        }
                    }
                });
                return cell;
            });

            // Member List View
            ArrayList<Member> members = (ArrayList<Member>) sm.getMembers();

            // Sort Members
            Collections.sort(members, new MemberOverallActiveComparator());
            Collections.reverse(members);

            for (Member m : members) {
                membersListView.getItems().add(m.getEmailAddress());
            }

            membersListView.setCellFactory(lv -> {
                ListCell<String> cell = new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(item == null ? "" : item.toString());
                    }
                };
                cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (!cell.isEmpty()) {
                            loadMemberPane(event, membersListView.getSelectionModel().getSelectedItem().toString());
                        }
                    }
                });
                return cell;
            });

            // Loads the Default Page to the Application
            window.getChildren().add(HomePage);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Loads a Question
    @FXML
    public void loadQuestionPane(ActionEvent event, String title) {
        try {

            VBox question = FXMLLoader.load(getClass().getResource("/QuestionVBox.fxml"));
            VBox answerForm = FXMLLoader.load(getClass().getResource("/SubmitAnswerVBox.fxml"));

            // Clears the window for new content
            if (window.getChildren().size() != 0)
                window.getChildren().clear();

            String groupTitle = "";
            for (Group g : sm.getGroups()) {
                for (Question q : g.getQuestions()) {
                    if (q.getTitle().compareTo(title) == 0) {
                        groupTitle = g.getTitle();
                    }
                }
            }

            ArrayList<Question> questions = (ArrayList) sm.getGroup(groupTitle).getQuestions();

            // Traversing FXML file & Assigning Nodes

            // Question Title
            HBox tempHB = (HBox) question.getChildren().get(0);
            Text questionTitle = (Text) tempHB.getChildren().get(0);
            questionTitle.setText(title);

            // Ask Button
            final String groupTitleFinal = groupTitle;
            Button questionButton = (Button) tempHB.getChildren().get(2);

            // Event Handler for Ask Button
            questionButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    loadNewQuestionPane(event, groupTitleFinal);
                }
            });

            // Control Buttons
            VBox tempVB = (VBox) question.getChildren().get(1);
            HBox tempHB2 = (HBox) tempVB.getChildren().get(1);
            VBox tempVB2 = (VBox) tempHB2.getChildren().get(0);

            Button upvote = (Button) tempVB2.getChildren().get(0);
            Text points = (Text) tempVB2.getChildren().get(1);
            Button downvote = (Button) tempVB2.getChildren().get(2);
            Button correct = (Button) tempVB2.getChildren().get(3);
            Button inapp = (Button) tempVB2.getChildren().get(4);
            Button offTopic = (Button) tempVB2.getChildren().get(5);

            VBox tempVB3 = (VBox) tempHB2.getChildren().get(1);
            TextArea questionTextArea = (TextArea) tempVB3.getChildren().get(0);
            HBox tempHB3 = (HBox) tempVB3.getChildren().get(1);

            VBox tempVB4 = (VBox) tempHB3.getChildren().get(1);
            Label datePosted = (Label) tempVB4.getChildren().get(0);
            HBox tempHB4 = (HBox) tempVB4.getChildren().get(1);

            VBox tempVB5 = (VBox) tempHB4.getChildren().get(1);
            Hyperlink link = (Hyperlink) tempVB5.getChildren().get(0);
            HBox tempHB5 = (HBox) tempVB5.getChildren().get(1);

            Label activity = (Label) tempHB5.getChildren().get(0);
            Label questionsAsked = (Label) tempHB5.getChildren().get(2);

            // Answer Form
            TextArea answerBox = (TextArea) answerForm.getChildren().get(2);
            Button postNewAnswer = (Button) answerForm.getChildren().get(3);

            // Handle Ask Button and tags show logic
            if (member == null) {
                questionButton.setDisable(true);
                upvote.setDisable(true);
                downvote.setDisable(true);
                inapp.setDisable(true);
                offTopic.setDisable(true);

            } else {
                questionButton.setDisable(true);
                upvote.setDisable(true);
                downvote.setDisable(true);
                inapp.setDisable(true);
                offTopic.setDisable(true);
                for (Group g : member.getGroups()) {
                    if (g.getTitle().compareTo(groupTitle) == 0) {
                        questionButton.setDisable(false);
                        upvote.setDisable(false);
                        downvote.setDisable(false);
                        inapp.setDisable(false);
                        offTopic.setDisable(false);
                    }
                }
            }

            questionTextArea.setStyle("-jfx-unfocus-color: #d5d5d5;");

            // Event Listeners
            for (int i = 0; i < questions.size(); i++) {
                Question q = questions.get(i);

                if (q.getTitle().compareTo(title) == 0) {
                    final int j = i;
                    upvote.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                        	//Set the data in the object
                            q.upvote();
                            
                            //Set the points GUI
                            setPointsText(q, points);
                            
                            //Save
                        	savePM();
                        }
                    });

                    downvote.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            
                        	//Set the data in the object
                        	q.downvote();
                            
                            //Set the points GUI
                            setPointsText(q, points);
                            
                            //Save
                        	savePM();
                        	
                        }
                    });

                    correct.setVisible(false);
                    
                    inapp.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                        	
                        	//Set the flag
                        	q.setFlag("inappropriate", !q.isInappropriate());
                        	
                        	//Save
                        	savePM();
                        	
                        	//Manage Colors
                            ColorAdjust ca = (ColorAdjust) inapp.getGraphic().getEffect();
                            double value = ca.getSaturation();
                            ColorAdjust grayscale = new ColorAdjust();
                            if (value < 0) {
                                grayscale.setSaturation(0);
                                inapp.getGraphic().setEffect(grayscale);
                            } else {
                                grayscale.setSaturation(-1);
                                inapp.getGraphic().setEffect(grayscale);
                            }

                            
                        }
                    });

                    offTopic.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                        	//Set the flag
                        	q.setFlag("irrelevant", !q.isIrrelevant());
                        	
                        	//Save
                        	savePM();
                        	
                        	//Manage Colors
                            
                            ColorAdjust ca = (ColorAdjust) offTopic.getGraphic().getEffect();
                            double value = ca.getSaturation();
                            ColorAdjust grayscale = new ColorAdjust();
                            if (value < 0) {
                                grayscale.setSaturation(0);
                                offTopic.getGraphic().setEffect(grayscale);
                            } else {
                                grayscale.setSaturation(-1);
                                offTopic.getGraphic().setEffect(grayscale);
                            }

                        }
                    });

                    // Sets points
                    setPointsText(q, points);

                    // Set Flags to false
                    ColorAdjust grayscale = new ColorAdjust();

                    if (q.isInappropriate()) {
                        grayscale.setSaturation(0);
                        inapp.getGraphic().setEffect(grayscale);
                    } else {
                        grayscale.setSaturation(-1);
                        inapp.getGraphic().setEffect(grayscale);
                    }

                    grayscale = new ColorAdjust();
                    if (q.isIrrelevant()) {
                        grayscale.setSaturation(0);
                        offTopic.getGraphic().setEffect(grayscale);
                    } else {
                        grayscale.setSaturation(-1);
                        offTopic.getGraphic().setEffect(grayscale);
                    }

                    // Set Question Text
                    questionTextArea.setText(q.getText());

                    // Set date Label

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd yyyy - HH:MMa");

                    datePosted.setText("Asked: " + q.getDate().format(formatter));

                    // Set Link to User
                    link.setText(q.getAuthor().getScreenName());
                    link.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            loadMemberPane(e, q.getAuthor().getEmailAddress());
                        }
                    });

                    // Stats
                    int questionsCount = 0;
                    int activityCount = 0;
                    for (Group g : q.getAuthor().getGroups()) {
                        questionsCount += q.getAuthor().getQuestions(g).size();
                        activityCount += q.getAuthor().getAnswers(g).size();
                        activityCount += q.getAuthor().getQuestions(g).size();
                    }

                    activity.setText("" + activityCount);
                    questionsAsked.setText("" + questionsCount);
                    window.getChildren().add(question);
                    
                    ArrayList<Answer> ans = (ArrayList<Answer>) q.getAnswers();
                    Collections.sort(ans, new PostPointsComparator());
                    Collections.reverse(ans);
                    ArrayList<Answer> ansC = new ArrayList<Answer>();
                    
                    for (Answer a : ans) {
                    	if(a.isCorrect()) {
                    		ansC.add(a);
                    	}
                    }
                    
                    for (Answer a : ans) {
                    	if(!a.isCorrect()) {
                    		ansC.add(a);
                    	}
                    }
                    
                    for (Answer a : ansC) {
                        loadAnswer(a, q);
                    }

                    if (member != null) {
                        answerBox.setOnKeyPressed(new EventHandler<KeyEvent>() {
                            @Override
                            public void handle(KeyEvent keyEvent) {
                                if (keyEvent.getCode() == KeyCode.ESCAPE) {
                                    answerBox.setText("");
                                }

                            }
                        });

                        postNewAnswer.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                if (answerBox.getText().toString().length() != 0) {
                                    Answer tempAnswer = new Answer(q, answerBox.getText().toString(),
                                            LocalDateTime.now());
                                    sm.getMember(member.getEmailAddress()).addAnswer(q.getGroup(), q, tempAnswer,
                                            tempAnswer.getDate());

                                    // save data
                                    savePM();

                                    // move frames
                                    loadQuestionPane(event, q.getTitle());
                                } else {
                                    Alert alert = new Alert(AlertType.INFORMATION);
                                    String a = "Invalid Answer";

                                    alert.setTitle("Input Error");
                                    alert.setHeaderText(null);
                                    alert.setContentText(a);

                                    alert.showAndWait();
                                }
                            }
                        });
                    } else {
                        postNewAnswer.setDisable(true);
                    }

                    window.getChildren().add(answerForm);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Helper Method for Points
    private void setPointsText(Post p, Text t) {

        String s = "" + p.getPoints();
        int len = s.length();
        switch (len) {
        case 1:
            t.setText("  " + s + " ");
            break;

        case 2:
            t.setText("  " + s + "");
            break;

        case 3:
            t.setText(" " + s);
            break;
        default:
            t.setText(s);
            break;
        }

    }

    // Loads search results
    @FXML
    public void loadSearchPane(ActionEvent event, String searchText) {
        try {
            Pane SearchPane = FXMLLoader.load(getClass().getResource("/SearchPane.fxml"));

            if (window.getChildren().size() != 0)
                window.getChildren().clear();

            // inner class
            class answerListItem {
                String questionTitle;
                String answerText;

                answerListItem(String questionTitle, String answerText) {
                    this.questionTitle = questionTitle;
                    this.answerText = answerText;
                }

                public String getQuestionTitle() {
                    return questionTitle;
                }

                public String toString() {
                    return answerText;
                }
            }

            // Traversing FXML file & Assigning Nodes
            VBox masterVBox = (VBox) SearchPane.getChildren().get(0);
            TabPane tabPane = (TabPane) masterVBox.getChildren().get(1);

            Tab questionsTab = (Tab) tabPane.getTabs().get(0);
            AnchorPane questionsAnchorPane = (AnchorPane) questionsTab.getContent();
            ListView<String> questionsListView = (ListView<String>) questionsAnchorPane.getChildren().get(0);

            Tab answersTab = (Tab) tabPane.getTabs().get(1);
            AnchorPane answersAnchorPane = (AnchorPane) answersTab.getContent();
            ListView<answerListItem> answersListView = (ListView<answerListItem>) answersAnchorPane.getChildren()
                    .get(0);

            Tab groupsTab = (Tab) tabPane.getTabs().get(2);
            AnchorPane groupsAnchorPane = (AnchorPane) groupsTab.getContent();
            ListView<String> groupsListView = (ListView<String>) groupsAnchorPane.getChildren().get(0);

            Tab membersTab = (Tab) tabPane.getTabs().get(3);
            AnchorPane membersAnchorPane = (AnchorPane) membersTab.getContent();
            ListView<String> membersListView = (ListView<String>) membersAnchorPane.getChildren().get(0);

            // set questions list
            ArrayList<Question> searchQuestions = (ArrayList<Question>) new ArrayList<Question>();
            ArrayList<Group> searchGroups = (ArrayList<Group>) new ArrayList<Group>();
            ArrayList<answerListItem> searchAnswers = (ArrayList<answerListItem>) new ArrayList<answerListItem>();
            ArrayList<Member> searchMembers = (ArrayList<Member>) new ArrayList<Member>();

            for (Group g : sm.getGroups()) {
                if (g.getDescription().toLowerCase().contains(searchText.toLowerCase())
                        || g.getTitle().toLowerCase().contains(searchText.toLowerCase())) {
                    searchGroups.add(g);
                }
                for (Question q : g.getQuestions()) {
                    if (q.getText().toLowerCase().contains(searchText.toLowerCase())
                            || q.getTitle().toLowerCase().contains(searchText.toLowerCase())) {
                        searchQuestions.add(q);
                    }
                    for (Answer a : q.getAnswers()) {
                        if (a.getText().toLowerCase().contains(searchText.toLowerCase())) {
                            searchAnswers.add(new answerListItem(a.getQuestion().getTitle(), a.getText()));
                        }
                    }
                }
            }

            // get members
            for (Member m : sm.getMembers()) {
                String text = searchText.toLowerCase();
                boolean found = false;
                if (m.getScreenName().toLowerCase().contains(text)) {
                	found =true;
                } else if( m.getEmailAddress().toLowerCase().contains(text)) {
                	found =true;
                } else if ( m.getFirstName().toLowerCase().contains(text)) {
                	found =true;
                } else if (m.getLastName().toLowerCase().contains(text)) {
                	found =true;
                }
                
                if(found) {
                    searchMembers.add(m);
                }
            }
            
            // Sort Questions
            Collections.sort(searchQuestions, new PostPointsComparator());
            Collections.reverse(searchQuestions);

            // Sort Groups
            Collections.sort(searchGroups, new GroupOverallActiveComparator());
            Collections.reverse(searchGroups);

            // Sort Members
            Collections.sort(searchMembers, new MemberOverallActiveComparator());
            Collections.reverse(searchMembers);

            // add to ListView
            for (Question q : searchQuestions) {
                questionsListView.getItems().add(q.getTitle());
            }
            
            if (searchMembers.size() == 0) {
            	membersTab.setDisable(true);
            }
            if (searchGroups.size() == 0) {
            	groupsTab.setDisable(true);
            }
            if (searchQuestions.size() == 0) {
            	questionsTab.setDisable(true);
            }
            if (searchAnswers.size() == 0) {
            	answersTab.setDisable(true);
            }
            
            // set the earliest tab that has focus 
            SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
            for (Tab t : tabPane.getTabs()) {
            	if (!t.isDisabled()) {
            		selectionModel.select(t);
            		break;
            	}
            }

            // Event Listeners
            questionsListView.setCellFactory(lv -> {
                ListCell<String> cell = new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(item == null ? "" : item.toString());
                    }
                };
                cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (!cell.isEmpty()) {

                            loadQuestionPane(event, questionsListView.getSelectionModel().getSelectedItem().toString());

                        }
                    }
                });
                return cell;
            });

            // add to answers ListView
            for (answerListItem a : searchAnswers) {
                answersListView.getItems().add(a);
            }

            answersListView.setCellFactory(lv -> {
                ListCell<answerListItem> cell = new ListCell<answerListItem>() {
                    @Override
                    protected void updateItem(answerListItem item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(item == null ? "" : item.toString());
                    }
                };
                cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (!cell.isEmpty()) {
                            for (answerListItem a : searchAnswers) {
                                if (a.getQuestionTitle().compareTo(answersListView.getSelectionModel().getSelectedItem()
                                        .getQuestionTitle()) == 0) {
                                    loadQuestionPane(event, a.getQuestionTitle());
                                }
                            }
                        }
                    }
                });
                return cell;
            });

            // set groups list
            for (Group g : searchGroups) {
                groupsListView.getItems().add(g.getTitle());
            }

            groupsListView.setCellFactory(lv -> {
                ListCell<String> cell = new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(item == null ? "" : item.toString());
                    }
                };
                cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (!cell.isEmpty()) {
                            loadGroupPane(event, groupsListView.getSelectionModel().getSelectedItem().toString());
                        }
                    }
                });
                return cell;
            });

            // set members list
            for (Member m : searchMembers) {
                membersListView.getItems().add(m.getEmailAddress());
            }

            membersListView.setCellFactory(lv -> {
                ListCell<String> cell = new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(item == null ? "" : item.toString());
                    }
                };
                cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (!cell.isEmpty()) {
                            loadMemberPane(event, membersListView.getSelectionModel().getSelectedItem().toString());
                        }
                    }
                });
                return cell;
            });

            // Loads Search Results
            window.getChildren().add(SearchPane);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Set data to the static Nodes for the MainWindow
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        drawerAction();

        groups.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadGroupsPane(event);
            }
        });

        home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadHomePane(event);
            }
        });

        register.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (member == null) {
                    loadNewMemberPane(event);
                }
            }
        });

        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (member == null)
                    loadLoginPane(event);
                else {
                    member = null;
                    login.setText("Login");
                    loadLoginPane(event);
                    register.setVisible(true);
                    VBox tempV = (VBox) drawer.getChildren().get(0);
                    for (Button b : memberGroups) {
                        tempV.getChildren().remove(b);
                    }
                    memberGroups.removeAll(memberGroups);

                }
            }
        });

        profile.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (member != null)
                    loadMemberPane(event, member.getEmailAddress());
                else
                    loadLoginPane(event);
            }
        });

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadSearchPane(event, searchText.getText().toString());
                searchText.setText("");
            }
        });

        searchText.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    ActionEvent e = null;
                    loadSearchPane(e, searchText.getText().toString());
                    searchText.setText("");
                }
                if (keyEvent.getCode() == KeyCode.ESCAPE) {
                    searchText.setText("");
                }

            }
        });
        
        saveData.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	savePM();
            }
        });
        
        loadData.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	loadPM();
            }
        });
        
        rick.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	if(member != null) {
            		login.fire();
            	}
        		member = sm.getMember("rjboles@valdosta.edu");
        		loadLoginPane(event);
            }
        });
        
        tyler.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	if(member != null) {
            		login.fire();
            	}
            	member = sm.getMember("trangelier@valdosta.edu");
            	loadLoginPane(event);
            }
        });
        
        mallory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	if(member != null) {
            		login.fire();
            	}
            	member = sm.getMember("mghelms@valdosta.edu");
            	loadLoginPane(event);
            }
        });
        
        michael.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	if(member != null) {
            		login.fire();
            	}
        		member = sm.getMember("memontgomery@valdosta.edu");
        		loadLoginPane(event);
            }
        });

        // Load Default Center Pane
        ActionEvent e = null;
        try {
        	loadHomePane(e);
        }catch(Exception dle) {
        	System.out.println("Reload Data");
        }
        
    }

    // Drawer Method for opening and closing
    @SuppressWarnings("restriction")
    private void drawerAction() {
        HamburgerSlideCloseTransition transition = new HamburgerSlideCloseTransition(menu);

        menu.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            if (pressed) {
                transition.setRate(transition.getRate() * -1);
                transition.play();
                drawer.setVisible(true);
                pressed = !pressed;
                MainWindow.setLeft(drawerSave);
            } else {
                transition.setRate(transition.getRate() * -1);
                transition.play();
                pressed = !pressed;

                drawerSave = drawer;
                MainWindow.getChildren().remove(drawer);

            }

        });
    }

    // Dynamic method for loading a post/answer
    public void loadAnswer(Answer a, Question q) {
        try {

            VBox Post = FXMLLoader.load(getClass().getResource("/PostVBox.fxml"));

            // Control Buttons

            HBox tempHB2 = (HBox) Post.getChildren().get(1);
            VBox tempVB2 = (VBox) tempHB2.getChildren().get(0);

            Button upvote = (Button) tempVB2.getChildren().get(0);
            Text points = (Text) tempVB2.getChildren().get(1);
            Button downvote = (Button) tempVB2.getChildren().get(2);
            Button correct = (Button) tempVB2.getChildren().get(3);
            Button inapp = (Button) tempVB2.getChildren().get(4);
            Button offTopic = (Button) tempVB2.getChildren().get(5);

            VBox tempVB3 = (VBox) tempHB2.getChildren().get(1);
            TextArea questionTextArea = (TextArea) tempVB3.getChildren().get(0);
            HBox tempHB3 = (HBox) tempVB3.getChildren().get(1);

            VBox tempVB4 = (VBox) tempHB3.getChildren().get(1);
            Label datePosted = (Label) tempVB4.getChildren().get(0);
            HBox tempHB4 = (HBox) tempVB4.getChildren().get(1);

            VBox tempVB5 = (VBox) tempHB4.getChildren().get(1);
            Hyperlink link = (Hyperlink) tempVB5.getChildren().get(0);
            HBox tempHB5 = (HBox) tempVB5.getChildren().get(1);

            Label activity = (Label) tempHB5.getChildren().get(0);
            Label questionsAsked = (Label) tempHB5.getChildren().get(2);

            questionTextArea.setStyle("-jfx-unfocus-color: #d5d5d5;");

            upvote.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    a.upvote();
                    setPointsText(a, points);

                    // save data
                    savePM();
                }
            });

            downvote.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    a.downvote();
                    setPointsText(a, points);

                    // save data
                    savePM();
                }
            });

            String groupTitle = "";
            for (Group g : sm.getGroups()) {
                for (Question qa : g.getQuestions()) {
                    if (qa.getTitle().compareTo(q.getTitle()) == 0) {
                        groupTitle = g.getTitle();
                    }
                }
            }

            if (member == null) {
                correct.setDisable(true);
                inapp.setDisable(true);
                offTopic.setDisable(true);
                upvote.setDisable(true);
                downvote.setDisable(true);
            } else if (member.getEmailAddress().compareTo(q.getAuthor().getEmailAddress()) == 0) {
                correct.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        a.setFlag("correct", !a.isCorrect());

                        // save data
                        savePM();

                        // handle color
                        ColorAdjust ca = (ColorAdjust) correct.getGraphic().getEffect();
                        double value = ca.getSaturation();
                        ColorAdjust grayscale = new ColorAdjust();
                        if (value < 0) {
                            grayscale.setSaturation(0);
                            correct.getGraphic().setEffect(grayscale);
                        } else {
                            grayscale.setSaturation(-1);
                            correct.getGraphic().setEffect(grayscale);
                        }

                    }
                });
            } else {
                upvote.setDisable(true);
                downvote.setDisable(true);
                inapp.setDisable(true);
                offTopic.setDisable(true);
                for (Group g : member.getGroups()) {
                    if (g.getTitle().compareTo(groupTitle) == 0) {
                        upvote.setDisable(false);
                        downvote.setDisable(false);
                        inapp.setDisable(false);
                        offTopic.setDisable(false);
                    }
                }
            }
            inapp.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    a.setFlag("inappropriate", !a.isInappropriate());

                    // save data
                    savePM();

                    // handle color
                    ColorAdjust ca = (ColorAdjust) inapp.getGraphic().getEffect();
                    double value = ca.getSaturation();
                    ColorAdjust grayscale = new ColorAdjust();
                    if (value < 0) {
                        grayscale.setSaturation(0);
                        inapp.getGraphic().setEffect(grayscale);
                    } else {
                        grayscale.setSaturation(-1);
                        inapp.getGraphic().setEffect(grayscale);
                    }

                }
            });

            offTopic.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    a.setFlag("irrelevant", !a.isIrrelevant());

                    // save data
                    savePM();

                    // handle color
                    ColorAdjust ca = (ColorAdjust) offTopic.getGraphic().getEffect();
                    double value = ca.getSaturation();
                    ColorAdjust grayscale = new ColorAdjust();
                    if (value < 0) {
                        grayscale.setSaturation(0);
                        offTopic.getGraphic().setEffect(grayscale);
                    } else {
                        grayscale.setSaturation(-1);
                        offTopic.getGraphic().setEffect(grayscale);
                    }

                }
            });

            // Sets points
            setPointsText(a, points);

            // Set Flags to false
            ColorAdjust grayscale = new ColorAdjust();

            if (a.isInappropriate()) {
                grayscale.setSaturation(0);
                inapp.getGraphic().setEffect(grayscale);
            } else {
                grayscale.setSaturation(-1);
                inapp.getGraphic().setEffect(grayscale);
            }

            grayscale = new ColorAdjust();
            if (a.isIrrelevant()) {
                grayscale.setSaturation(0);
                offTopic.getGraphic().setEffect(grayscale);
            } else {
                grayscale.setSaturation(-1);
                offTopic.getGraphic().setEffect(grayscale);
            }

            grayscale = new ColorAdjust();
            if (a.isCorrect()) {
                grayscale.setSaturation(0);
                correct.getGraphic().setEffect(grayscale);
            } else {
                grayscale.setSaturation(-1);
                correct.getGraphic().setEffect(grayscale);
            }

            // Set Question Text
            questionTextArea.setText(a.getText());

            // Set date Label

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd yyyy - HH:MMa");

            datePosted.setText("Answered: " + a.getDate().format(formatter));

            // Set Link to User

            link.setText(a.getAuthor().getScreenName());
            link.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    loadMemberPane(e, a.getAuthor().getEmailAddress());
                }
            });

            // Stats
            ArrayList<Group> groups = (ArrayList) a.getAuthor().getGroups();
            int questionsCount = 0;
            int activityCount = 0;
            for (Group g : groups) {
                questionsCount += a.getAuthor().getQuestions(g).size();
                activityCount += a.getAuthor().getAnswers(g).size();
                activityCount += a.getAuthor().getQuestions(g).size();

            }

            activity.setText("" + activityCount);
            questionsAsked.setText("" + questionsCount);

            window.getChildren().add(Post);

        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    // Helper method for saving the data
    private void savePM() {
        pm.save(sm, file);
    }
    
    // Helper method for loading the data
    private void loadPM() {
    	pm.read(file);
    }
}
