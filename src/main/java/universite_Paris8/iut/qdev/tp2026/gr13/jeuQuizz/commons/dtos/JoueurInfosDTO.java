package universite_Paris8.iut.qdev.tp2026.gr13.jeuQuizz.commons.dtos;

public class JoueurInfosDTO {
    private String id;
    private String pseudo;
    private String prenom;

    public JoueurInfosDTO(String id, String pseudo, String prenom) {
        this.id = id;
        this.pseudo = pseudo;
        this.prenom = prenom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}