/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafxhibernate;

import dbml.Amigo;
import dbml.Demo;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 *
 * @author Usuario
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private TableView<Amigo> table;
    
    @FXML
    private TableColumn<Amigo, Integer> id;
    @FXML
    private TableColumn<Amigo, String> nombre;
    @FXML
    private TableColumn<Amigo, String> correo;
    @FXML
    private TableColumn<Amigo, String> telefono;
    
    public ObservableList<Amigo> data;

    
    @FXML
    private void handleButtonAction(ActionEvent event) {
      
        Session session = null;
        
        try{
                // INSERTAR REGISTRO
            SessionFactory sessionFactory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
            session =sessionFactory.openSession();
            session.beginTransaction();

            System.out.println("Instertando database !");
            Amigo customer = new Amigo();
            customer.setNombres("HIBERNATE MAN");
            customer.setCorreo("@HIBERNATE");
            customer.setTelf("111111111");
            session.save(customer);
            session.getTransaction().commit();

            System.out.println("OK!");

            // CARGAR GRID HQL

            id.setCellValueFactory(new PropertyValueFactory<Amigo,Integer>("idAmigo")); 
            nombre.setCellValueFactory(new PropertyValueFactory<Amigo,String>("nombres"));
            correo.setCellValueFactory(new PropertyValueFactory<Amigo,String>("correo"));
            telefono.setCellValueFactory(new PropertyValueFactory<Amigo,String>("telf"));

            data  =  FXCollections.observableArrayList();        
            SessionFactory sf = new Configuration().configure().buildSessionFactory();
            Session sess  =sf.openSession();        
            Query qee = sess.createQuery("from Amigo");
            Iterator ite  =qee.iterate();
            while(ite.hasNext())
            {
                Amigo obj = (Amigo)ite.next();    
                data.add(obj);           
            }        
            table.setItems(data);

        
        }catch (Exception e) {
        
        }
        
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
