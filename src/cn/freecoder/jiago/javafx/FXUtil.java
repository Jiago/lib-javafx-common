/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.freecoder.jiago.javafx;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import cn.freecoder.lib.util.Util;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

/**
 *
 * @author admin
 */
public class FXUtil {

    public static void showAboutDialog(Window stage, String fxmlpath,
            String title, Class cls) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(cls.getResource(fxmlpath));

        try {
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger("jiago").log(Level.SEVERE, null, ex);
        }

    }

    public static void openFXML(String fxml, Class cls, String title) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(cls.getResource(fxml));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger("jiago").log(Level.SEVERE, null, ex);
        }
    }

    public static Popup createPopup(final String message) {
        final Popup popup = new Popup();
        popup.setAutoFix(true);
        popup.setAutoHide(true);
        popup.setHideOnEscape(true);
        Label label = new Label(message);
        label.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                popup.hide();
            }
        });
        label.getStylesheets().add("/styles/popup.css");
        label.getStyleClass().add("popup");
        popup.getContent().add(label);
        return popup;
    }

    public static void toast(final String message, final Stage stage) {
        final Popup popup = createPopup(message);
        popup.setOnShown(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                popup.setX(stage.getX() + stage.getWidth() / 2
                        - popup.getWidth() / 2);
                popup.setY(stage.getY() + stage.getHeight() / 2
                        - popup.getHeight() / 2);
            }
        });
        popup.show(stage);
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                Util.sleep(3000);
                popup.hide();
            }
        });
    }

    public static File getJiagoHomeDir() {
        String home = System.getProperty("user.home");
        if (home == null) {
            home = "/";
        }
        File f = new File(home, ".jiago.so.dict");
        if (!f.exists()) {
            f.mkdirs();
        }
        return f;
    }

    public static void showPopupMessage(final String message, final Stage stage) {
        final Popup popup = createPopup(message);
        popup.setOnShown(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                popup.setX(stage.getX() + stage.getWidth() / 2
                        - popup.getWidth() / 2);
                popup.setY(stage.getY() + stage.getHeight() / 2
                        - popup.getHeight() / 2);
            }
        });
        popup.show(stage);
    }

}
