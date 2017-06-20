package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

/**
 * Created by комп on 13.06.2017.
 */
public interface UserMealRepository {

    Meal save(Meal userMeal);

    void delete(int id);

    Meal get(int id);

    Collection<Meal> getAll();

}
