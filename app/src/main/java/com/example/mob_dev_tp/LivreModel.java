package com.example.mob_dev_tp;

public class LivreModel {
    //les attributs de la table livre
    private int id;
    private int isbn;
    private String nom;

    //Constructeur par défaut - nécessaire pour certaines opérations ORM
    public LivreModel(){}

    //Constructeur avec paramètres - permet de créer un objet Livre avec toutes ses propriétés
    public LivreModel(int id, int isbn, String nom){
        this.id = id;
        this.isbn = isbn;
        this.nom = nom;
    }

    //// Getters et Setters - méthodes d'accès aux attributs privés selon le principe d'encapsulation
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    /**
     * Redéfinition de la méthode toString pour afficher les informations du livre de manière lisible
     * Utile pour le débogage et l'affichage des informations
     */
    @Override
    public String toString() {
        return "Livre [id=" + id + ", isbn=" + isbn + ", nom=" + nom + "]";
    }
}
