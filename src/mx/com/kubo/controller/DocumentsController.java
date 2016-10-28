package mx.com.kubo.controller;


import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import mx.com.kubo.bean.Document;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;


@ManagedBean
@RequestScoped
  
public class DocumentsController implements Serializable {  
  
    private TreeNode root;  
  
    public DocumentsController() {  
        root = new DefaultTreeNode("root", null);  
  
        TreeNode general = new DefaultTreeNode(new Document("General", "-", "Folder"), root);  
        /*TreeNode pictures = new DefaultTreeNode(new Document("Pictures", "-", "Folder"), root);  
        TreeNode movies = new DefaultTreeNode(new Document("Movies", "-", "Folder"), root); */ 
  
        TreeNode about = new DefaultTreeNode(new Document("Acerca de kubo", "-", "Folder"), general);  
        TreeNode privacity = new DefaultTreeNode(new Document("Privacidad y Seguridad", "-", "Folder"), general);  
  
        //Documents  
        TreeNode expenses = new DefaultTreeNode("document", new Document("¿Qué es kubo?", "30 KB", "Word Document"), about);  
        TreeNode resume = new DefaultTreeNode("document", new Document("¿Por qué ofrecemos las mejores tasas de crédito a nuetros clientes?", "10 KB", "Word Document"), about);  
        TreeNode refdoc = new DefaultTreeNode("document", new Document("¿Qué  sucede si kubo por alguna razón deja de formar parte del proyecto de  alguna inversión?", "40 KB", "Pages Document"), about);  
  
        //Pictures  
        /*TreeNode barca = new DefaultTreeNode("picture", new Document("barcelona.jpg", "30 KB", "JPEG Image"), pictures);  
        TreeNode primelogo = new DefaultTreeNode("picture", new Document("logo.jpg", "45 KB", "JPEG Image"), pictures);  
        TreeNode optimus = new DefaultTreeNode("picture", new Document("optimusprime.png", "96 KB", "PNG Image"), pictures);  
  
        //Movies  
        TreeNode pacino = new DefaultTreeNode(new Document("Al Pacino", "-", "Folder"), movies);  
        TreeNode deniro = new DefaultTreeNode(new Document("Robert De Niro", "-", "Folder"), movies);  
  
        TreeNode scarface = new DefaultTreeNode("mp3", new Document("Scarface", "15 GB", "Movie File"), pacino);  
        TreeNode carlitosWay = new DefaultTreeNode("mp3", new Document("Carlitos' Way", "24 GB", "Movie File"), pacino);  
  
        TreeNode goodfellas = new DefaultTreeNode("mp3", new Document("Goodfellas", "23 GB", "Movie File"), deniro);  
        TreeNode untouchables = new DefaultTreeNode("mp3", new Document("Untouchables", "17 GB", "Movie File"), deniro);  */
    }  
  
    public TreeNode getRoot() {  
        return root;  
    }  
}  