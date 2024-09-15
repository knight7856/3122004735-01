public class TextPreprocessor {
    public String preprocess(String text) {
        return text.replaceAll("[\\p{Punct}\\s]+", " ").toLowerCase().trim();
    }
}