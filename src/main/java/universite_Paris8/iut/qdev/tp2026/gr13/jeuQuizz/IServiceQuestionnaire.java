package universite_Paris8.iut.qdev.tp2026.gr13.jeuQuizz;

import universite_Paris8.iut.qdev.tp2026.gr13.jeuQuizz.commons.dtos.QuestionnaireInfosDTO;
import java.util.List;

public interface IServiceQuestionnaire {
    List<QuestionnaireInfosDTO> listerQuestionnaires();
}
