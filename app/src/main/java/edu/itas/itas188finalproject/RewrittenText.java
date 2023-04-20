package edu.itas.itas188finalproject;

public class RewrittenText {
    private String originalText;
    private String rephrasedText;
    private String tone;

    public RewrittenText() {
        // Default constructor required for calls to DataSnapshot.getValue(RewrittenText.class)
    }

    public RewrittenText(String originalText, String rephrasedText, String selectedTone) {
        this.originalText = originalText;
        this.rephrasedText = rephrasedText;
        this.tone = selectedTone;
    }
    public String getTone() {
        return tone;
    }
    public void setTone(String tone) {
        this.tone = tone;
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


