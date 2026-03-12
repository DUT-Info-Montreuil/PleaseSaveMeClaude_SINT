package universite_Paris8.iut.qdev.tp2026.gr13.jeuQuizz.commons.dtos;

public class QuestionnaireInfosDTO {
    private String id;
    private String libelle;
    private String langue;
    private String descriptionDetaillee;

    public QuestionnaireInfosDTO(String id, String libelle, String langue, String descriptionDetaillee) {
        this.id = id;
        this.libelle = libelle;
        this.langue = langue;
        this.descriptionDetaillee = descriptionDetaillee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getDescriptionDetaillee() {
        return descriptionDetaillee;
    }

    public void setDescriptionDetaillee(String descriptionDetaillee) {
        this.descriptionDetaillee = descriptionDetaillee;
    }
}
