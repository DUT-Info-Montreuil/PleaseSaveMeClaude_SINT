package universite_Paris8.iut.qdev.tp2026.gr13.jeuQuizz;

import universite_Paris8.iut.qdev.tp2026.gr13.jeuQuizz.commons.dtos.ElementsPourUnePartieDTO;
import universite_Paris8.iut.qdev.tp2026.gr13.jeuQuizz.commons.dtos.JoueurInfosDTO;
import universite_Paris8.iut.qdev.tp2026.gr13.jeuQuizz.commons.dtos.PartieDTO;
import universite_Paris8.iut.qdev.tp2026.gr13.jeuQuizz.commons.dtos.QuestionnaireInfosDTO;
import universite_Paris8.iut.qdev.tp2026.gr13.jeuQuizz.interfaces.IServiceSint;

import java.util.*;
import java.util.stream.Collectors;

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

        if (joueurs == null) joueurs = new ArrayList<>();
        if (questionnaires == null) questionnaires = new ArrayList<>();

        return new ElementsPourUnePartieDTO(questionnaires, joueurs);
    }

    @Override
    public void traiterUnJoueur(String pseudo) throws IllegalArgumentException {
        if (pseudo == null || pseudo.isBlank()) {
            throw new IllegalArgumentException("Pseudo invalide");
        }

        List<JoueurInfosDTO> joueurs = serviceJoueur.listerJoueurs();

        boolean existe = joueurs.stream()
                .anyMatch(j -> pseudo.equals(j.getPseudo()));

        if (!existe) {
            throw new IllegalArgumentException("Joueur introuvable");
        }
    }

    public PartieDTO preparerLesElementsDeLaPartie(String pseudo, String idQuestionnaire) {

        List<JoueurInfosDTO> joueurs = serviceJoueur.listerJoueurs();
        List<QuestionnaireInfosDTO> questionnaires = serviceQuestionnaire.listerQuestionnaires();

        // --- Vérifications ---
        if (joueurs == null || joueurs.isEmpty()) {
            throw new IllegalArgumentException("Aucun joueur disponible");
        }

        if (questionnaires == null || questionnaires.isEmpty()) {
            throw new IllegalArgumentException("Aucun questionnaire disponible");
        }

        // --- Tri des joueurs ---
        joueurs.sort(Comparator.comparing(JoueurInfosDTO::getPseudo));

        // --- Trouver joueur ---
        JoueurInfosDTO joueur = joueurs.stream()
                .filter(j -> j.getPseudo().equals(pseudo))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Joueur introuvable"));

        // --- Trouver questionnaire ---
        QuestionnaireInfosDTO questionnaire = questionnaires.stream()
                .filter(q -> q.getId().equals(idQuestionnaire))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Questionnaire introuvable"));

        List<QuestionDTO> toutesQuestions = serviceQuestionnaire.getToutesLesQuestions(questionnaire);

        if (toutesQuestions == null || toutesQuestions.size() < 10) {
            throw new IllegalArgumentException("Pas assez de questions");
        }

        // FILTRER PAR DIFFICULTÉ
        List<QuestionDTO> simples = toutesQuestions.stream()
                .filter(q -> q.getDifficulte() == 1)
                .collect(Collectors.toList());

        List<QuestionDTO> intermediaires = toutesQuestions.stream()
                .filter(q -> q.getDifficulte() == 2)
                .collect(Collectors.toList());

        List<QuestionDTO> expertes = toutesQuestions.stream()
                .filter(q -> q.getDifficulte() == 3)
                .collect(Collectors.toList());

        Collections.shuffle(simples);
        Collections.shuffle(intermediaires);
        Collections.shuffle(expertes);

        List<QuestionDTO> selection = new ArrayList<>();

        selection.addAll(simples.stream().limit(3).collect(Collectors.toList()));
        selection.addAll(intermediaires.stream().limit(3).collect(Collectors.toList()));
        selection.addAll(expertes.stream().limit(4).collect(Collectors.toList()));

        Collections.shuffle(selection);

        return new PartieDTO(joueur, questionnaire, selection);
    }
}