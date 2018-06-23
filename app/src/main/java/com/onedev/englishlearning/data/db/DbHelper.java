
package com.onedev.englishlearning.data.db;

import com.onedev.englishlearning.data.db.model.Option;
import com.onedev.englishlearning.data.db.model.Question;
import com.onedev.englishlearning.data.db.model.User;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by Nhat on 12/13/17.
 */


public interface DbHelper {

    Observable<Long> insertUser(final User user);

    Observable<List<User>> getAllUsers();

    Observable<List<Question>> getAllQuestions();

    Observable<Boolean> isQuestionEmpty();

    Observable<Boolean> isOptionEmpty();

    Observable<Boolean> saveQuestion(Question question);

    Observable<Boolean> saveOption(Option option);

    Observable<Boolean> saveQuestionList(List<Question> questionList);

    Observable<Boolean> saveOptionList(List<Option> optionList);
}
