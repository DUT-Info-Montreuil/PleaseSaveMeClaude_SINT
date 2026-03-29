package universite_Paris8.iut.qdev.tp2026.gr13.jeuQuizz;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import universite_Paris8.iut.qdev.tp2026.gr13.jeuQuizz.commons.dtos.ElementsPourUnePartieDTO;
import universite_Paris8.iut.qdev.tp2026.gr13.jeuQuizz.commons.dtos.JoueurInfosDTO;
import universite_Paris8.iut.qdev.tp2026.gr13.jeuQuizz.commons.dtos.QuestionnaireInfosDTO;

@ExtendWith(MockitoExtension.class)
public class SintServiceImplTest {

    @Mock
    private IServiceJoueur serviceJoueurMock;

    @Mock
    private IServiceQuestionnaire serviceQuestionnaireMock;

    @InjectMocks
    private SintServiceImpl sintService;

    // --- Liste avec plusieurs joueurs ---

    /**
     * Explication : Vérifie que lorsque le service joueur renvoie une liste avec 
     * plusieurs joueurs, l'objet retourné et la liste des joueurs à l'intérieur 
     * ne sont pas "null".
     */
    @Test
    public void testDeterminerElementsDispoPourPartie_listeNonNulle() {
        when(serviceJoueurMock.listerJoueurs()).thenReturn(listePlusieursJoueurs());
        when(serviceQuestionnaireMock.listerQuestionnaires()).thenReturn(Collections.emptyList());

        ElementsPourUnePartieDTO resultat = sintService.determinerElementsDispoPourPartie();

        assertNotNull(resultat);
        assertNotNull(resultat.getJoueursDisponibles());
    }

    /**
     * Explication : S'assure que les données du premier joueur (pseudo et prénom)
     * sont correctement récupérées depuis le service et intégrées dans le DTO final
     * sans subir d'altération.
     */
    @Test
    public void testDeterminerElementsDispoPourPartie_donneesJoueur1() {
        when(serviceJoueurMock.listerJoueurs()).thenReturn(listePlusieursJoueurs());
        when(serviceQuestionnaireMock.listerQuestionnaires()).thenReturn(Collections.emptyList());

        ElementsPourUnePartieDTO resultat = sintService.determinerElementsDispoPourPartie();

        assertEquals("Dayzix1248", resultat.getJoueursDisponibles().get(0).getPseudo());
        assertEquals("Gabin", resultat.getJoueursDisponibles().get(0).getPrenom());
    }

    /**
     * Explication : S'assure que les données du second joueur de la liste
     * sont elles aussi récupérées correctement de bout en bout.
     */
    @Test
    public void testDeterminerElementsDispoPourPartie_donneesJoueur2() {
        when(serviceJoueurMock.listerJoueurs()).thenReturn(listePlusieursJoueurs());
        when(serviceQuestionnaireMock.listerQuestionnaires()).thenReturn(Collections.emptyList());

        ElementsPourUnePartieDTO resultat = sintService.determinerElementsDispoPourPartie();

        assertEquals("wael11111", resultat.getJoueursDisponibles().get(1).getPseudo());
        assertEquals("Wael", resultat.getJoueursDisponibles().get(1).getPrenom());
    }

    // --- Liste avec un seul joueur ---

    /**
     * Explication : Vérifie que le DTO final gère et préserve correctement 
     * les données dans le cas précis où la base de données ne contient 
     * qu'un seul et unique joueur.
     */
    @Test
    public void testDeterminerElementsDispoPourPartie_unSeulJoueur_donnees() {
        when(serviceJoueurMock.listerJoueurs()).thenReturn(listeUnJoueur());
        when(serviceQuestionnaireMock.listerQuestionnaires()).thenReturn(Collections.emptyList());

        ElementsPourUnePartieDTO resultat = sintService.determinerElementsDispoPourPartie();

        assertEquals("Dayzix1248", resultat.getJoueursDisponibles().get(0).getPseudo());
        assertEquals("Gabin", resultat.getJoueursDisponibles().get(0).getPrenom());
    }

    // --- Liste vide ---

    /**
     * Explication : Contrôle le bon comportement de la méthode s'il n'y a aucun 
     * joueur inscrit. La liste des joueurs retournée doit être vide mais surtout 
     * pas "null" (pour éviter les NullPointerException ailleurs dans le programme).
     */
    @Test
    public void testDeterminerElementsDispoPourPartie_listeVide_nonNulle() {
        when(serviceJoueurMock.listerJoueurs()).thenReturn(Collections.emptyList());
        when(serviceQuestionnaireMock.listerQuestionnaires()).thenReturn(Collections.emptyList());

        ElementsPourUnePartieDTO resultat = sintService.determinerElementsDispoPourPartie();

        assertNotNull(resultat.getJoueursDisponibles());
    }

    // --- Joueur avec des champs null ---

    /**
     * Explication : Vérifie la robustesse du code en s'assurant que l'application 
     * ne crash pas si un joueur avec des champs invalides ou manquants (null) 
     * est renvoyé par la base de données.
     */
    @Test
    public void testDeterminerElementsDispoPourPartie_joueurChampsNull() {
        List<JoueurInfosDTO> liste = new ArrayList<>();
        liste.add(new JoueurInfosDTO(null, null, null));
        when(serviceJoueurMock.listerJoueurs()).thenReturn(liste);
        when(serviceQuestionnaireMock.listerQuestionnaires()).thenReturn(Collections.emptyList());

        ElementsPourUnePartieDTO resultat = sintService.determinerElementsDispoPourPartie();

        assertNull(resultat.getJoueursDisponibles().get(0).getPseudo());
        assertNull(resultat.getJoueursDisponibles().get(0).getPrenom());
    }

    // --- Tests pour les Questionnaires (30 éléments avec niveaux de difficulté) ---

    /**
     * Explication : Simule un appel avec une base de données contenant 30 
     * questionnaires, et vérifie qu'aucun élément ne manque (on obtient bien 
     * les 30 questionnaires demandés).
     */
    @Test
    public void testDeterminerElementsDispoPourPartie_trenteQuestionnaires_taille() {
        when(serviceJoueurMock.listerJoueurs()).thenReturn(Collections.emptyList());
        when(serviceQuestionnaireMock.listerQuestionnaires()).thenReturn(listeTrenteQuestionnaires());

        ElementsPourUnePartieDTO resultat = sintService.determinerElementsDispoPourPartie();

        assertNotNull(resultat.getQuestionnairesDisponibles());
        assertEquals(30, resultat.getQuestionnairesDisponibles().size(), "Il doit y avoir 30 questionnaires (simulant 30 questions)");
    }

    /**
     * Explication : Analyse le contenu de la liste de 30 questionnaires retournés
     * pour garantir qu'ils sont correctement récupérés avec la bonne répartition : 
     * 10 de niveau simple, 10 de niveau moyen, et 10 de niveau difficile.
     */
    @Test
    public void testDeterminerElementsDispoPourPartie_trenteQuestionnaires_repartitionNiveaux() {
        when(serviceJoueurMock.listerJoueurs()).thenReturn(Collections.emptyList());
        when(serviceQuestionnaireMock.listerQuestionnaires()).thenReturn(listeTrenteQuestionnaires());

        ElementsPourUnePartieDTO resultat = sintService.determinerElementsDispoPourPartie();

        long countSimple = resultat.getQuestionnairesDisponibles().stream()
                .filter(q -> q.getDescriptionDetaillee().contains("simple")).count();
        long countMoyen = resultat.getQuestionnairesDisponibles().stream()
                .filter(q -> q.getDescriptionDetaillee().contains("moyen")).count();
        long countDifficile = resultat.getQuestionnairesDisponibles().stream()
                .filter(q -> q.getDescriptionDetaillee().contains("difficile")).count();

        assertEquals(10, countSimple, "Il doit y avoir 10 éléments de niveau simple");
        assertEquals(10, countMoyen, "Il doit y avoir 10 éléments de niveau moyen");
        assertEquals(10, countDifficile, "Il doit y avoir 10 éléments de niveau difficile");
    }

    // --- Scénarios manquants pour une couverture complète ---

    /**
     * Explication : Scénario nominal où tout fonctionne en même temps : 
     * vérifie que l'on peut récupérer conjointement et correctement les joueurs 
     * ET les questionnaires sans qu'ils ne se supplantent l'un l'autre.
     */
    @Test
    public void testDeterminerElementsDispoPourPartie_joueursEtQuestionnairesPresents() {
        when(serviceJoueurMock.listerJoueurs()).thenReturn(listePlusieursJoueurs());
        when(serviceQuestionnaireMock.listerQuestionnaires()).thenReturn(listeTrenteQuestionnaires());

        ElementsPourUnePartieDTO resultat = sintService.determinerElementsDispoPourPartie();

        assertNotNull(resultat);
        assertEquals(2, resultat.getJoueursDisponibles().size(), "Il doit y avoir 2 joueurs");
        assertEquals(30, resultat.getQuestionnairesDisponibles().size(), "Il doit y avoir 30 questionnaires");
    }

    /**
     * Explication : Vérifie le comportement si un bug ou un souci côté service
     * fait que l'on reçoit explicitement "null" (et non une liste vide). L'objet final 
     * doit pouvoir être créé tout de même, sans provoquer de crash.
     */
    @Test
    public void testDeterminerElementsDispoPourPartie_listesNulles() {
        when(serviceJoueurMock.listerJoueurs()).thenReturn(null);
        when(serviceQuestionnaireMock.listerQuestionnaires()).thenReturn(null);

        ElementsPourUnePartieDTO resultat = sintService.determinerElementsDispoPourPartie();

        assertNotNull(resultat);
        assertNull(resultat.getJoueursDisponibles(), "La liste de joueurs devrait être null si le service renvoie null");
        assertNull(resultat.getQuestionnairesDisponibles(), "La liste de questionnaires devrait être null si le service renvoie null");
    }

    /**
     * Explication : Vérifie que si une erreur grave serveur survient (comme une perte de 
     * connexion à la base de données), la méthode principale ne cache pas l'erreur, mais 
     * la propage correctement vers les couches supérieures (Exception).
     */
    @Test
    public void testDeterminerElementsDispoPourPartie_exceptionService() {
        when(serviceJoueurMock.listerJoueurs()).thenThrow(new RuntimeException("Erreur de connexion à la base de données"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            sintService.determinerElementsDispoPourPartie();
        });
        assertEquals("Erreur de connexion à la base de données", exception.getMessage());
    }

    /**
     * Explication : Vérifie l'exigence : "Si le fichier est absent ou corrompu, 
     * interrompre la partie et inviter le joueur à contacter le service client."
     * On simule ici que le service de questionnaires lève une exception signalant 
     * un problème de fichier, et on s'assure que le bon message est renvoyé.
     * Signature : Gabin
     */
    @Test
    public void testDeterminerElementsDispoPourPartie_fichierAbsentOuCorrompu() {
        // Le service Joueur fonctionne bien
        when(serviceJoueurMock.listerJoueurs()).thenReturn(listePlusieursJoueurs());
        
        // Le service Questionnaire lève une erreur de fichier (absent ou corrompu)
        when(serviceQuestionnaireMock.listerQuestionnaires()).thenThrow(
            new RuntimeException("Fichier absent ou corrompu. Veuillez contacter le service client.")
        );

        // On vérifie que la méthode interrompt bien son traitement (via l'exception)
        // et que le message d'erreur contient bien l'invitation à contacter le service client
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            sintService.determinerElementsDispoPourPartie();
        });
        
        assertTrue(exception.getMessage().contains("contacter le service client"), 
            "Le message d'erreur doit inviter à contacter le service client");
    }

    // --- Helpers ---

    private List<JoueurInfosDTO> listePlusieursJoueurs() {
        List<JoueurInfosDTO> liste = new ArrayList<>();
        liste.add(new JoueurInfosDTO(null, "Dayzix1248", "Gabin"));
        liste.add(new JoueurInfosDTO(null, "wael11111", "Wael"));
        return liste;
    }

    private List<JoueurInfosDTO> listeUnJoueur() {
        List<JoueurInfosDTO> liste = new ArrayList<>();
        liste.add(new JoueurInfosDTO(null, "Dayzix1248", "Gabin"));
        return liste;
    }

    private List<QuestionnaireInfosDTO> listeTrenteQuestionnaires() {
        List<QuestionnaireInfosDTO> liste = new ArrayList<>();
        // 10 "simples"
        for (int i = 0; i < 10; i++) {
            liste.add(new QuestionnaireInfosDTO("S" + i, "Question simple " + i, "FR", "Niveau simple"));
        }
        // 10 "moyens"
        for (int i = 0; i < 10; i++) {
            liste.add(new QuestionnaireInfosDTO("M" + i, "Question moyenne " + i, "FR", "Niveau moyen"));
        }
        // 10 "difficiles"
        for (int i = 0; i < 10; i++) {
            liste.add(new QuestionnaireInfosDTO("D" + i, "Question difficile " + i, "FR", "Niveau difficile"));
        }
        return liste;
    }
}