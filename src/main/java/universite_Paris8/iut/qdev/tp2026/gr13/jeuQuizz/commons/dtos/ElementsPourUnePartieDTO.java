package universite_Paris8.iut.qdev.tp2026.gr13.jeuQuizz.commons.dtos;

import java.util.List;

public class ElementsPourUnePartieDTO {
    private List<QuestionnaireInfosDTO> questionnairesDisponibles;
    private List<JoueurInfosDTO> joueursDisponibles;

    public ElementsPourUnePartieDTO(List<QuestionnaireInfosDTO> questionnaires, List<JoueurInfosDTO> joueurs) {
        this.questionnairesDisponibles = questionnaires;
        this.joueursDisponibles = joueurs;
    }

    public List<QuestionnaireInfosDTO> getQuestionnairesDisponibles() {
        return questionnairesDisponibles;
    }

    public void setQuestionnairesDisponibles(List<QuestionnaireInfosDTO> questionnairesDisponibles) {
        this.questionnairesDisponibles = questionnairesDisponibles;
    }

    public List<JoueurInfosDTO> getJoueursDisponibles() {
        return joueursDisponibles;
    }

    public void setJoueursDisponibles(List<JoueurInfosDTO> joueursDisponibles) {
        this.joueursDisponibles = joueursDisponibles;
    }


}


