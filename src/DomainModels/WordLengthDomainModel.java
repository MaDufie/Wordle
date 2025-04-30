package DomainModels;

import DataAccess.WordLengthDataAccess;
import DataObjects.WordLengthDataObject;
import DomainObjects.WordLengthDomainObject;

public class WordLengthDomainModel {

    public static boolean validateWordLengthId(int wordLengthId) {
        return WordLengthDataAccess.GetWordLengthById(wordLengthId) != null;
    }

    public static WordLengthDomainObject GetWordLengthById(int wordLengthId) {
        WordLengthDataObject wordLengthData = WordLengthDataAccess.GetWordLengthById(wordLengthId);
        return new WordLengthDomainObject(wordLengthData);
    }
}
