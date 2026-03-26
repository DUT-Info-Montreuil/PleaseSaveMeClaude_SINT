package universite_Paris8.iut.qdev.tp2026.gr13.jeuQuizz;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

// Imports de vos DTOs
import universite_Paris8.iut.qdev.tp2026.gr13.jeuQuizz.commons.dtos.ElementsPourUnePartieDTO;


import universite_Paris8.iut.qdev.tp2026.gr13.jeuQuizz.equipeQuestionnaire.IServiveQuestionnaire;
public class SintServiceImplTest {
    @Mock
    private IserviceJoueur serviceJoueurMock;

    @Mock
    private IServiceQuestionnaire serviceQuestionnaireMock;

    // 2. Mockito va injecter automatiquement les mocks ci-dessus dans l'instance de notre service
    @InjectMocks
    private SintServiceImpl sintService;

    private ArrayList<JoueurDTO> fausseListeJoueurs;

    @BeforeEach
    public void setUp() {
        // 3. On prépare nos fausses données de test
        fausseListeJoueurs = new ArrayList<>();

        // On crée des faux joueurs de l'équipe 14
        // (Adaptez selon comment s'instancie leur JoueurDTO, ici on suppose des setters ou un constructeur)
        JoueurDTO joueur1 = new JoueurDTO();
        joueur1.setPseudo("Dayzix1248");
        joueur1.setPrenom("Gabin");

        JoueurDTO joueur2 = new JoueurDTO();
        joueur2.setPseudo("wael11111");
        joueur2.setPrenom("Wael");

        fausseListeJoueurs.add(joueur1);
        fausseListeJoueurs.add(joueur2);
    }

    @Test
    public void testDeterminerElementsDispoPourPartie() {
        // ETAPE A : Configuration du Mock (Le comportement simulé)
        // Quand notre service va appeler listerJoueurs(), le mock retournera notre fausseListeJoueurs
        when(serviceJoueurMock.listerJoueurs()).thenReturn(fausseListeJoueurs);

        // (Optionnel) Vous pouvez faire la même chose pour les questionnaires si vous en avez besoin
        // when(serviceQuestionnaireMock.listerQuestionnaires()).thenReturn(new ArrayList<>());

        // ETAPE B : Exécution de la méthode à tester
        ElementsPourUnePartieDTO resultat = sintService.determinerElementsDispoPourPartie();

        // ETAPE C : Vérifications (Assertions)
        // On vérifie que le résultat n'est pas nul
        assertNotNull(resultat, "Le DTO retourné ne doit pas être null");

        // On vérifie que la liste des joueurs a bien été convertie et contient 2 éléments
        assertNotNull(resultat.getJoueursDisponibles(), "La liste des joueurs convertis ne doit pas être null");
        assertEquals(2, resultat.getJoueursDisponibles().size(), "Il doit y avoir 2 joueurs dans la liste IHM");

        // On vérifie que le mapping des données (de l'équipe 14 vers votre DTO) s'est bien passé
        assertEquals("Dayzix1248", resultat.getJoueursDisponibles().get(0).getPseudo());
        assertEquals("Gabin", resultat.getJoueursDisponibles().get(0).getPrenom());

        assertEquals("wael11111", resultat.getJoueursDisponibles().get(1).getPseudo());
        assertEquals("Wael", resultat.getJoueursDisponibles().get(1).getPrenom());

        System.out.println("Test validé ! Le mock a bien fonctionné et les données ont été converties.");
    }
}
