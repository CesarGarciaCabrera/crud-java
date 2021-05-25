/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.java;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;

/**
 *
 * @author cesar
 */
public class Record {
    
    private final StringProperty id;
    private final StringProperty nombre;
    private final StringProperty correo;
    private final StringProperty contrasena;
    private final StringProperty sexo;
    private final StringProperty cuenta;


public Record(){
    id = new SimpleStringProperty(this, "id");
    nombre = new SimpleStringProperty(this, "nombre");
    correo = new SimpleStringProperty(this, "correo");
    contrasena = new SimpleStringProperty(this, "contrasena");
    sexo = new SimpleStringProperty(this, "sexo");
    cuenta = new SimpleStringProperty(this, "cuenta");
}

public StringProperty idProperty() {return id;}
public String getId() {return id.get();}
public void setId(String newId) { id.setValue(newId); }

public StringProperty nombreProperty() {return nombre;}
public String getNombre () {return nombre.get();}
public void setNombre(String newNombre) { nombre.setValue(newNombre);}

public StringProperty correoProperty() { return correo; }
public String getCorreo() { return correo.get(); }
public void setCorreo(String newCorreo) { correo.setValue(newCorreo);}

public StringProperty contrasenaProperty() { return contrasena; }
public String getContrasena() {return contrasena.get();}
public void setContrasena(String newContrasena) {contrasena.setValue(newContrasena);}

public StringProperty sexoProperty() { return sexo; }
public String getSexo() {return sexo.get();}
public void setSexo(String newSexo) {sexo.setValue(newSexo);}

public StringProperty cuentaProperty() { return cuenta; }
public String getCuenta() {return cuenta.get();}
public void setCuenta(String newCuenta) {cuenta.setValue(newCuenta);}




@Override
public String toString(){
	return String.format("%s[id=%s, name=%s]",
	getId(), getNombre(), getCorreo(), getContrasena(), getSexo(), getCuenta());
    }
}



