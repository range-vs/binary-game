package windows;

import database.ConnectionPostgres;
import entity.NameEducation;
import entity.comparators.NameEducationComparator;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableListValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class EducationController {

    @FXML
    private ListView<String> listEducations;
    @FXML
    private WebView currentEducation;
    private WebEngine webEngine;
    private MultipleSelectionModel<String> selectedItems;
    private HashMap<String, String> pages;

    public void initialize(){
        try {
            loadPages();
        } catch (SQLException e) {
            e.printStackTrace();
            Platform.exit();
        }
        initListeners();
    }

    private void loadPages() throws SQLException {
        TreeSet<NameEducation> names = new TreeSet<NameEducation>(new NameEducationComparator());
        pages = new HashMap<>();
        Statement st = ConnectionPostgres.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery("SELECT id, name, education FROM theory;");
        while (rs.next()) {
            StringBuilder content = new StringBuilder(rs.getString(3));
            Statement st_img = ConnectionPostgres.getInstance().getConnection().createStatement();
            int id = rs.getBigDecimal(1).intValue();
            ResultSet rs_img = st_img.executeQuery("SELECT img, img_path, img_name FROM images WHERE id_theory = " + id + ";");
            int index = 1;
            while (rs_img.next()) {
                String path = rs_img.getString(2);
                String name = rs_img.getString(3);
                File catalog = new File(path);
                if (!catalog.exists()) {
                    if (!catalog.mkdirs()) {
                        System.out.println("Аварийная ошибка!");
                        return;
                    }
                }
                File file = new File(path + name);
                System.out.println(file.getAbsolutePath()); // debug
                if (!file.exists()) {
                    Image img = new Image(rs_img.getBinaryStream(1));
                    String format = "PNG";
                    File f = new File(path + name);
                    System.out.println(f.getAbsolutePath()); // debug
                    try {
                        ImageIO.write(SwingFXUtils.fromFXImage(img, null), format, f);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(f.toURI()); // debug
                    file = f;
                }
                String findLine = "<img id=\"img" + index++ + "\" ";
                int pos = content.indexOf(findLine);
                content = content.insert(pos + findLine.length() + 5, file.toURI());
            }
            rs_img.close();
            st_img.close();
            String name = rs.getString(2);
            pages.put(name, content.toString());
            names.add(new NameEducation(rs.getBigDecimal(1).intValue(), name));
        }
        rs.close();
        st.close();
        webEngine = currentEducation.getEngine();
        ArrayList<String> sortedNames = new ArrayList<>();
        for(NameEducation ne: names){
            sortedNames.add(ne.getName());
        }
        listEducations.setItems(FXCollections.observableArrayList(sortedNames));
    }

    private void initListeners(){
        selectedItems = listEducations.getSelectionModel();
        selectedItems.selectedItemProperty().addListener(new ChangeListener<String>(){
            public void changed(ObservableValue<? extends String> changed, String oldValue, String newValue){
                // загружаем новую страницу в WebEngine
                webEngine.loadContent(pages.get(newValue));
            }
        });
        selectedItems.selectFirst();
    }

}
