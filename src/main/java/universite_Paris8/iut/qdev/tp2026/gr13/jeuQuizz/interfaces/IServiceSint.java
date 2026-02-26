package universite_Paris8.iut.qdev.tp2026.gr13.jeuQuizz.interfaces;

public interface IServiceSint {
    /**
     * Vérifie l'existence d'un pseudo ou valide la création d'un profil.
     * Le pseudo doit être unique et ne pas commencer par un chiffre[cite: 45].
     */
    void traiterUnJoueur(String pseudo) throws IllegalArgumentException;

    /**
     * Prépare les éléments nécessaires au lancement d'une partie[cite: 51].
     */
    Partie preparerLesElementsDeLaPartie(String pseudoJoueur, List<Question> questionnaireChoisi)
            throws IllegalArgumentException, NotEnoughQuestionsException, JoueurInexistantException;

    /**
     * Pilote l'enchaînement des questions et calcule le score[cite: 35].
     */
    void realiserUnePartie(Partie partie);

    /**
     * Transmet le score et le temps pour mettre à jour les statistiques[cite: 40, 41].
     */
    void mettreAJourLesStats(Partie partieTerminee);
}
