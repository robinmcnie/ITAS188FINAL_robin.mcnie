package edu.itas.itas188finalproject;

import java.util.List;

import java.util.List;

public class OpenAIResponse {
    private List<Choice> choices;

    public OpenAIResponse() {
    }

    public OpenAIResponse(List<Choice> choices) {
        this.choices = choices;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public static class Choice {
        private String text;

        public Choice() {
        }

        public Choice(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}


