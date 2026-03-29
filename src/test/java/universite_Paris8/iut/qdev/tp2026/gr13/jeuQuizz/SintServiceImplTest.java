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

@ExtendWith(MockitoExtension.class)
public class SintServiceImplTest {

    @Mock
    private IServiceJoueur serviceJoueurMock;

    @Mock
    private IServiceQuestionnaire serviceQuestionnaireMock;

    @InjectMocks
    private SintServiceImpl sintService;

    // --- Liste avec plusieurs joueurs ---

    @Test
    public void testDeterminerElementsDispoPourPartie_listeNonNulle() {
        when(serviceJoueurMock.listerJoueurs()).thenReturn(listePlusieursJoueurs());

        ElementsPourUnePartieDTO resultat = sintService.determinerElementsDispoPourPartie();

        assertNotNull(resultat);
        assertNotNull(resultat.getJoueursDisponibles());
    }

    @Test
    public void testDeterminerElementsDispoPourPartie_tailleListe() {
        when(serviceJoueurMock.listerJoueurs()).thenReturn(listePlusieursJoueurs());

        ElementsPourUnePartieDTO resultat = sintService.determinerElementsDispoPourPartie();

        assertEquals(2, resultat.getJoueursDisponibles().size());
    }

    @Test
    public void testDeterminerElementsDispoPourPartie_donneesJoueur1() {
        when(serviceJoueurMock.listerJoueurs()).thenReturn(listePlusieursJoueurs());

        ElementsPourUnePartieDTO resultat = sintService.determinerElementsDispoPourPartie();

        assertEquals("Dayzix1248", resultat.getJoueursDisponibles().get(0).getPseudo());
        assertEquals("Gabin", resultat.getJoueursDisponibles().get(0).getPrenom());
    }

    @Test
    public void testDeterminerElementsDispoPourPartie_donneesJoueur2() {
        when(serviceJoueurMock.listerJoueurs()).thenReturn(listePlusieursJoueurs());

        ElementsPourUnePartieDTO resultat = sintService.determinerElementsDispoPourPartie();

        assertEquals("wael11111", resultat.getJoueursDisponibles().get(1).getPseudo());
        assertEquals("Wael", resultat.getJoueursDisponibles().get(1).getPrenom());
    }

    // --- Liste avec un seul joueur ---

    @Test
    public void testDeterminerElementsDispoPourPartie_unSeulJoueur_taille() {
        when(serviceJoueurMock.listerJoueurs()).thenReturn(listeUnJoueur());

        ElementsPourUnePartieDTO resultat = sintService.determinerElementsDispoPourPartie();

        assertEquals(1, resultat.getJoueursDisponibles().size());
    }

    @Test
    public void testDeterminerElementsDispoPourPartie_unSeulJoueur_donnees() {
        when(serviceJoueurMock.listerJoueurs()).thenReturn(listeUnJoueur());

        ElementsPourUnePartieDTO resultat = sintService.determinerElementsDispoPourPartie();

        assertEquals("Dayzix1248", resultat.getJoueursDisponibles().get(0).getPseudo());
        assertEquals("Gabin", resultat.getJoueursDisponibles().get(0).getPrenom());
    }

    // --- Liste vide ---

    @Test
    public void testDeterminerElementsDispoPourPartie_listeVide_nonNulle() {
        when(serviceJoueurMock.listerJoueurs()).thenReturn(Collections.emptyList());

        ElementsPourUnePartieDTO resultat = sintService.determinerElementsDispoPourPartie();

        assertNotNull(resultat.getJoueursDisponibles());
    }

    @Test
    public void testDeterminerElementsDispoPourPartie_listeVide_taille() {
        when(serviceJoueurMock.listerJoueurs()).thenReturn(Collections.emptyList());

        ElementsPourUnePartieDTO resultat = sintService.determinerElementsDispoPourPartie();

        assertEquals(0, resultat.getJoueursDisponibles().size());
    }

    // --- Joueur avec des champs null ---

    @Test
    public void testDeterminerElementsDispoPourPartie_joueurChampsNull() {
        List<JoueurInfosDTO> liste = new ArrayList<>();
        liste.add(new JoueurInfosDTO(null, null, null));
        when(serviceJoueurMock.listerJoueurs()).thenReturn(liste);

        ElementsPourUnePartieDTO resultat = sintService.determinerElementsDispoPourPartie();

        assertNull(resultat.getJoueursDisponibles().get(0).getPseudo());
        assertNull(resultat.getJoueursDisponibles().get(0).getPrenom());
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
}
