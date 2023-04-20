package edu.itas.itas188finalproject;

public class RewrittenText {
    private String originalText;
    private String rephrasedText;

    public RewrittenText() {
        // Default constructor required for calls to DataSnapshot.getValue(RewrittenText.class)
    }

    public RewrittenText(String originalText, String rephrasedText) {
        this.originalText = originalText;
        this.rephrasedText = rephrasedText;
    }

    public String getOriginalText() {
        return originalText;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    public String getRephrasedText() {
        return rephrasedText;
    }

    public void setRephrasedText(String rephrasedText) {
        this.rephrasedText = rephrasedText;
    }
}


