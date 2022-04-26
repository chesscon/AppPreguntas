package fciencias.pdm20222.apppreguntas;

public class Question {

    private String text;
    private boolean answerTrue;
    private Category category;

    public Question(String text, boolean answerTrue, Category category) {
        this.text = text;
        this.answerTrue = answerTrue;
        this.category = category;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
