package DomainObjects;

import DataObjects.WordLengthDataObject;

public class WordLengthDomainObject {
    public int wordLengthId;
    public String description;
    public int wordLength;

    public WordLengthDomainObject(WordLengthDataObject wordLengthDataObject) {
        this.wordLengthId = wordLengthDataObject.wordLengthId;
        this.description = wordLengthDataObject.description;
        this.wordLength = wordLengthDataObject.wordLength;
    }

    public int getWordLengthId() {
        return this.wordLengthId;
    }

    public String getDescription() {
        return this.description;
    }

    public int getWordLength() {
        return this.wordLength;
    }
}
