package universite_Paris8.iut.qdev.tp2026.gr13.jeuQuizz;

import universite_Paris8.iut.qdev.tp2026.gr13.jeuQuizz.commons.dtos.ElementsPourUnePartieDTO;
import universite_Paris8.iut.qdev.tp2026.gr13.jeuQuizz.commons.dtos.JoueurInfosDTO;
import universite_Paris8.iut.qdev.tp2026.gr13.jeuQuizz.commons.dtos.QuestionnaireInfosDTO;
import universite_Paris8.iut.qdev.tp2026.gr13.jeuQuizz.interfaces.IServiceSint;

import java.util.ArrayList;
import java.util.List;

public class SintServiceImpl implements IServiceSint {

    private final IServiceJoueur serviceJoueur;
    private final IServiceQuestionnaire serviceQuestionnaire;

    public SintServiceImpl(IServiceJoueur serviceJoueur, IServiceQuestionnaire serviceQuestionnaire) {
        this.serviceJoueur = serviceJoueur;
        this.serviceQuestionnaire = serviceQuestionnaire;
    }

    @Override
    public ElementsPourUnePartieDTO determinerElementsDispoPourPartie() {
        List<JoueurInfosDTO> joueurs = serviceJoueur.listerJoueurs();
        List<QuestionnaireInfosDTO> questionnaires = serviceQuestionnaire.listerQuestionnaires();
        return new ElementsPourUnePartieDTO(questionnaires, joueurs);
    }

    @Override
    public void traiterUnJoueur(String pseudo) throws IllegalArgumentException {
        if (pseudo == null || pseudo.isBlank())
            throw new IllegalArgumentException("Le pseudo ne peut pas être null ou vide");
        List<JoueurInfosDTO> joueurs = serviceJoueur.listerJoueurs();
        boolean existe = joueurs.stream().anyMatch(j -> pseudo.equals(j.getPseudo()));
        if (!existe)
            throw new IllegalArgumentException("Joueur introuvable : " + pseudo);
    }
}
