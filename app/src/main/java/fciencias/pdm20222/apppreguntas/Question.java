package fciencias.pdm20222.apppreguntas;

public class Question {
    private String text;
    private boolean answerTrue;

    public Question(String text, boolean answerTrue) {
        this.text = text;
        this.answerTrue = answerTrue;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isAnswerTrue() {
        return answerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        this.answerTrue = answerTrue;
    }
}
