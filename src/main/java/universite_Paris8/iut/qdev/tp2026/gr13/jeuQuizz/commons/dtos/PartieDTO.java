package universite_Paris8.iut.qdev.tp2026.gr13.jeuQuizz.commons.dtos;

import java.util.List;

public class PartieDTO {

    private JoueurInfosDTO joueur;
    private QuestionnaireInfosDTO questionnaire;
    private List<Object> questions;

    public PartieDTO(JoueurInfosDTO joueur, QuestionnaireInfosDTO questionnaire, List<Object> questions) {
        this.joueur = joueur;
        this.questionnaire = questionnaire;
        this.questions = questions;
    }

    public JoueurInfosDTO getJoueur() {
        return joueur;
    }

    public QuestionnaireInfosDTO getQuestionnaire() {
        return questionnaire;
    }

    public List<Object> getQuestions() {
        return questions;
    }
}