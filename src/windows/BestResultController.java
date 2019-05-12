package windows;

import database.ConnectionPostgres;
import entity.BestResult;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BestResultController {

    @FXML
    private TableView<BestResult> tableResults;
    @FXML
    private TableColumn<BestResult, Integer> columnNumber;
    @FXML
    private TableColumn<BestResult, String> columnLogin;
    @FXML
    private TableColumn<BestResult, String> columnLevel;
    @FXML
    private TableColumn<BestResult, Integer> columnCount;
    @FXML
    private TableColumn<BestResult, String> columnTime;

    public void initialize(){
        try {
            loadDataResults();
        } catch (SQLException e) {
            e.printStackTrace();
            Platform.exit();
            return;
        }
    }

    private void loadDataResults() throws SQLException {
        Statement st = ConnectionPostgres.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery("SELECT accounts.name, desc_levels.name, best_results.count, best_results.time " +
                "FROM best_results, desc_levels, accounts WHERE best_results.id_accounts = accounts.id AND best_results.id_level = desc_levels.id " +
                "ORDER BY desc_levels.level DESC, best_results.time ASC, best_results.count DESC " +
                "LIMIT 20;");
        ObservableList<BestResult> results = FXCollections.observableArrayList();
        int number = 1;
        while (rs.next()) {
            results.add(new BestResult(number++, rs.getString(1), rs.getString(2), rs.getBigDecimal(3).intValue(),
                    rs.getString(4)));
        }
        rs.close();
        st.close();
        columnNumber.setCellValueFactory(new PropertyValueFactory<BestResult, Integer>("number"));
        columnLogin.setCellValueFactory(new PropertyValueFactory<BestResult, String>("login"));
        columnLevel.setCellValueFactory(new PropertyValueFactory<BestResult, String>("level"));
        columnCount.setCellValueFactory(new PropertyValueFactory<BestResult, Integer>("count"));
        columnTime.setCellValueFactory(new PropertyValueFactory<BestResult, String>("time"));
        tableResults.setItems(results);
    }

}
