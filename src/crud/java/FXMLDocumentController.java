/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.java;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class FXMLDocumentController implements Initializable {
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtContrasena;
    @FXML
    private TableView<Record> table;
    @FXML
    private TableColumn<Record, String> idColumna;
    @FXML
    private TableColumn<Record, String> nombreColumna;
    @FXML
    private TableColumn<Record, String> correoColumna;
    @FXML
    private TableColumn<Record, String> contrasenaColumna;
    @FXML
    private TableColumn<Record, String> sexoColumna;
    @FXML
    private TableColumn<Record, String> tipoColumna;
    @FXML
    private TextField txtSexo;
    @FXML
    private TextField txtCuenta;
    
    
    Connection con;
    PreparedStatement pst;
    
    public void Connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/codigobits","root","");
        } catch (ClassNotFoundException ex) {
          
        } catch (SQLException ex) {
           
        }
    }
    @FXML
    void Add(ActionEvent event) {
        Connect();
            String nombre = txtNombre.getText();
            String correo = txtCorreo.getText();
            String contrasena = txtContrasena.getText();
            String sexo = txtSexo.getText();
            String cuenta = txtCuenta.getText(); 
       try {
            pst = con.prepareStatement("insert into records(nombre,correo,contrasena,sexo,cuenta)values(?,?,?,?,?)");
            pst.setString(1, nombre);
            pst.setString(2, correo);
            pst.setString(3, contrasena);
            pst.setString(4, sexo);
            pst.setString(5, cuenta);
            int status = pst.executeUpdate();
             
             if(status==1)
             { 
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Alumno");
                alert.setContentText("Se agrego correctamente");
                alert.showAndWait();
                table();
                txtNombre.setText("");
                txtCorreo.setText("");
                txtContrasena.setText("");
                txtSexo.setText("");
                txtCuenta.setText("");
                txtNombre.requestFocus();
             }
             else
             {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fail");
                alert.setHeaderText("Alumno");
                alert.setContentText("No se agrego el alumno");
                alert.showAndWait();
             }
            } 
          catch (SQLException ex)
       {
           Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    public void table()
      {
          ObservableList<Record> records = FXCollections.observableArrayList();
       try 
       {
           pst = con.prepareStatement("select id,nombre,correo,contrasena,sexo,cuenta from records");  
           ResultSet rs = pst.executeQuery();
      {
        while (rs.next())
        {
            Record record = new Record();
            record.setId(rs.getString("id"));
            record.setNombre(rs.getString("nombre"));
            record.setCorreo(rs.getString("correo"));
            record.setContrasena(rs.getString("contrasena"));
            record.setSexo(rs.getString("sexo"));
            record.setCuenta(rs.getString("cuenta"));
            records.add(record);
       }
    } 
                table.setItems(records);
                idColumna.setCellValueFactory(f -> f.getValue().idProperty());
                nombreColumna.setCellValueFactory(f -> f.getValue().nombreProperty());
                correoColumna.setCellValueFactory(f -> f.getValue().correoProperty());
                contrasenaColumna.setCellValueFactory(f -> f.getValue().contrasenaProperty());
                sexoColumna.setCellValueFactory(f -> f.getValue().sexoProperty());
                tipoColumna.setCellValueFactory(f -> f.getValue().cuentaProperty());
       }
       
       catch (SQLException ex) 
       {
           Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
       }
}
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connect();
        table();
    }    
    
}
