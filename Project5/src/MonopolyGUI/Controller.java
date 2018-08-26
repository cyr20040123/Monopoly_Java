package MonopolyGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * @see Controller controller class
 */
public class Controller {
    /**
     * @see for yes button
     */
    @FXML
    protected Button
            yesButton,
    /**
     * @see for no button
     */
    noButton,
    /**
     * @see for save button
     */
    saveButton,
    /**
     * @see for load button
     */
    loadButton,
    /**
     * @see for retire button
     */
    retireButton;
    /**
     * @see for yesbuttonAvailiable
     */

    protected boolean yesButtonAvailable,
    /**
     * @see for checking whether nobuttonAvailiable
     */
    noButtonAvailable,
    /**
     * @see for checking whether retirebuttonAvailiable
     */
    retireButtonAvailable;
    /**
     * @see for checking whether yes Availiable
     */
    protected boolean yes,
    /**
     * @see for checking whether no Availiable
     */
    no,
    /**
     * @see for checking whether save Availiable
     */
    save,
    /**
     * @see for checking whether load Availiable
     */
    load,
    /**
     * @see for checking whether retire Availiable
     */
    retire;

    /**
     * @see for messageArea
     */
    @FXML
    protected TextArea messageArea;
    /**
     * @see for diceImage
     */
    @FXML
    protected ImageView dice1,
    /**
     * @see for diceImage
     */
    dice2;

    private Stage primaryStage;
    private Scene scene;
    private MonopolyStage monopoly;

    /**
     * @see for clearing all button
     */
    public void clearAllButton(){
        yes=false;
        no=false;
        save=false;
        load=false;
        retire=false;
    }

    /**
     *
     * @param event event for yesClick
     */
    @FXML
    public void yesClick(ActionEvent event){
        clearAllButton();
        if(yesButtonAvailable){
            yes=true;
            yesButton.setText("####");
        }
        else yes=false;
        monopoly.checkRequests();
    }

    /**
     *
     * @param event event for no click
     */
    @FXML
    public void noClick(ActionEvent event){
        clearAllButton();
        if(noButtonAvailable){
            no=true;
            yesButton.setText("####");
        }
        else no=false;
        monopoly.checkRequests();
    }

    /**
     *
     * @param event event for retire click
     */
    @FXML
    public void retireClick(ActionEvent event){
        clearAllButton();
        if(retireButtonAvailable){
            retire=true;
            retireButton.setText("####");
        }
        else retire=false;
        monopoly.checkRequests();
    }

    /**
     *
     * @param event event for saveclick
     */
    @FXML
    public void saveClick(ActionEvent event){
        clearAllButton();
        save = true;
        monopoly.simpleNoticeWindow("Game saving will begin in next turn.");
        monopoly.checkRequests();
    }

    /**
     *
     * @param event event for loadclick
     */
    public void loadClick(ActionEvent event){
        clearAllButton();
        load = true;
        monopoly.simpleNoticeWindow("Game loading will begin in next turn.");
        monopoly.checkRequests();
    }

    /**
     *
     * @param primaryStage primaryStage
     */
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     *
     * @param scene scence
     */

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    /**
     *
     * @return primary stage
     */

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     *
     * @param monopoly MonopolyStage
     */

    public void setMonopoly(MonopolyStage monopoly) {
        this.monopoly = monopoly;
    }

    /**
     *
     * @return TextArea for message
     */

    public TextArea getMessageArea() {
        return messageArea;
    }

    /**
     *
     * @return yesbutton
     */

    public Button getYesButton() {
        return yesButton;
    }

    /**
     *
     * @return no button
     */

    public Button getNoButton() {
        return noButton;
    }

    /**
     *
     * @param yesButtonAvailable yes button available
     */

    public void setYesButtonAvailable(boolean yesButtonAvailable) {
        this.yesButtonAvailable = yesButtonAvailable;
    }

    /**
     *
     * @param noButtonAvailable no button available
     */

    public void setNoButtonAvailable(boolean noButtonAvailable) {
        this.noButtonAvailable = noButtonAvailable;
    }

    /**
     *
     * @param retireButtonAvailable retirebutton available
     */

    public void setRetireButtonAvailable(boolean retireButtonAvailable) {
        this.retireButtonAvailable = retireButtonAvailable;
    }

    /**
     *
     * @return true: save successfully
     */
    public boolean getSave() {
        return save;
    }
    /**
     *
     * @return true: load successfully
     */
    public boolean getLoad() {
        return load;
    }
    /**
     *
     * @return true: retire successfully
     */
    public boolean getRetire() {
        return retire;
    }
    /**
     *
     * @return true: get no successfully
     */
    public boolean getNo() {
        return no;
    }
    /**
     *
     * @return true: get yes successfully
     */
    public boolean getYes() {
        return yes;
    }

    /**
     *
     * @param no no
     */

    public void setNo(boolean no) {
        this.no = no;
    }

    /**
     *
     * @param yes yes
     */

    public void setYes(boolean yes) {
        this.yes = yes;
    }

    /**
     *
     * @param retire retire
     */

    public void setRetire(boolean retire) {
        this.retire = retire;
    }

    /**
     *
     * @return Dice image
     */

    public ImageView getDice1() {
        return dice1;
    }
    /**
     *
     * @return Dice image
     */

    public ImageView getDice2() {
        return dice2;
    }

    /**
     *
     * @return retirebutton
     */

    public Button getRetireButton() {
        return retireButton;
    }
}
