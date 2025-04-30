package DataObjects;

public class WordLengthDataObject {
    public int wordLengthId;
    public String description;
    public int wordLength;

    public WordLengthDataObject(int wordLengthId, String description, int wordLength) {
        this.wordLengthId = wordLengthId;
        this.description = description;
        this.wordLength = wordLength;
    }

    public WordLengthDataObject(WordLengthDataObject copy) {
        this.wordLengthId = copy.wordLengthId;
        this.description = copy.description;
        this.wordLength = copy.wordLength;
    }
}
