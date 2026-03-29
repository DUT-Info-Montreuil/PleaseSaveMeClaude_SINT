package universite_Paris8.iut.qdev.tp2026.gr13.jeuQuizz;

import universite_Paris8.iut.qdev.tp2026.gr13.jeuQuizz.commons.dtos.JoueurInfosDTO;
import java.util.List;

public interface IServiceJoueur {
    List<JoueurInfosDTO> listerJoueurs();
}
