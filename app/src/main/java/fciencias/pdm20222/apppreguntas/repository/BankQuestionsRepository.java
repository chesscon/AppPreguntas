package fciencias.pdm20222.apppreguntas.repository;

import fciencias.pdm20222.apppreguntas.Question;

public interface BankQuestionsRepository {

    public Question[] getQuestionBank(int categoryId);

}
