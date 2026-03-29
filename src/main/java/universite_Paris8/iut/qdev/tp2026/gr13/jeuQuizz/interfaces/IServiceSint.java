package universite_Paris8.iut.qdev.tp2026.gr13.jeuQuizz.interfaces;

import universite_Paris8.iut.qdev.tp2026.gr13.jeuQuizz.commons.dtos.ElementsPourUnePartieDTO;
import universite_Paris8.iut.qdev.tp2026.gr13.jeuQuizz.commons.dtos.PartieDTO;
//import universite_Paris8.iut.qdev.tp2026.gr13.jeuQuizz.models.Partie;
//import universite_Paris8.iut.qdev.tp2026.gr13.jeuQuizz.models.Question;
//import universite_Paris8.iut.qdev.tp2026.gr13.jeuQuizz.exceptions.JoueurInexistantException;
//import universite_Paris8.iut.qdev.tp2026.gr13.jeuQuizz.exceptions.NotEnoughQuestionsException;

import java.util.ArrayList;

public interface IServiceSint {

    public ElementsPourUnePartieDTO determinerElementsDispoPourPartie();


    public void traiterUnJoueur(String pseudo) throws IllegalArgumentException;

    PartieDTO preparerLesElementsDeLaPartie(String pseudo, String idQuestionnaire);

  //  public void realiserUnePartie(Partie partie);

    // public void mettreAJourLesStats(Partie partieTerminee);

}