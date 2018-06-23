
package com.onedev.englishlearning.data.db;

import com.onedev.englishlearning.data.db.model.DaoMaster;
import com.onedev.englishlearning.data.db.model.DaoSession;
import com.onedev.englishlearning.data.db.model.Option;
import com.onedev.englishlearning.data.db.model.Question;
import com.onedev.englishlearning.data.db.model.User;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;


/**
 * Created by Nhat on 12/13/17.
 */


@Singleton
public class AppDbHelper implements DbHelper {

    private final DaoSession mDaoSession;

    @Inject
    public AppDbHelper(DbOpenHelper dbOpenHelper) {
        mDaoSession = new DaoMaster(dbOpenHelper.getWritableDb()).newSession();
    }

    @Override
    public Observable<Long> insertUser(final User user) {
        return Observable.fromCallable(() -> mDaoSession.getUserDao().insert(user));
    }

    @Override
    public Observable<List<User>> getAllUsers() {
        return Observable.fromCallable(() -> mDaoSession.getUserDao().loadAll());
    }

    @Override
    public Observable<List<Question>> getAllQuestions() {
        return Observable.fromCallable(() -> mDaoSession.getQuestionDao().loadAll());
    }

    @Override
    public Observable<Boolean> isQuestionEmpty() {
        return Observable.fromCallable(() -> !(mDaoSession.getQuestionDao().count() > 0));
    }

    @Override
    public Observable<Boolean> isOptionEmpty() {
        return Observable.fromCallable(() -> !(mDaoSession.getOptionDao().count() > 0));
    }

    @Override
    public Observable<Boolean> saveQuestion(final Question question) {
        return Observable.fromCallable(() -> {
            mDaoSession.getQuestionDao().insert(question);
            return true;
        });
    }

    @Override
    public Observable<Boolean> saveOption(final Option option) {
        return Observable.fromCallable(() -> {
            mDaoSession.getOptionDao().insertInTx(option);
            return true;
        });
    }

    @Override
    public Observable<Boolean> saveQuestionList(final List<Question> questionList) {
        return Observable.fromCallable(() -> {
            mDaoSession.getQuestionDao().insertInTx(questionList);
            return true;
        });
    }

    @Override
    public Observable<Boolean> saveOptionList(final List<Option> optionList) {
        return Observable.fromCallable(() -> {
            mDaoSession.getOptionDao().insertInTx(optionList);
            return true;
        });
    }
}
