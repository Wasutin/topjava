package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 600),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 410)
        );


        List<UserMealWithExceed> userMealWithExceeds=getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);

        userMealWithExceeds.forEach(System.out::println);
//        .toLocalDate();
//        .toLocalTime();

    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field

        Map<LocalDate, Integer> countCaloriesPerDay= mealList.stream()
                .collect(Collectors.groupingBy(s->s.getDateTime().toLocalDate()),
                        Collectors.summingInt(s->s.getCalories()));


        List<UserMealWithExceed> userMealWithExceed = mealList.stream()
                .filter(s -> TimeUtil.isBetween(s.getDateTime().toLocalTime(), startTime, endTime))
                .map(s -> new UserMealWithExceed(s.getDateTime(), s.getDescription(), s.getCalories(),
                        countCaloriesPerDay.get(s.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());
        return userMealWithExceed;


//        Map<LocalDate, Integer> caloriesPerDayMap=new HashMap<>();
//        for (UserMeal userMeal:mealList){
//            Integer caloriesUserMeal=caloriesPerDayMap.getOrDefault(userMeal.getDateTime().toLocalDate(), userMeal.getCalories());
//
//            if (!caloriesPerDayMap.containsKey(userMeal.getDateTime().toLocalDate())) {
//
//                caloriesPerDayMap.put(userMeal.getDateTime().toLocalDate(), caloriesUserMeal);
//            }else
//            caloriesPerDayMap.put(userMeal.getDateTime().toLocalDate(), userMeal.getCalories()+caloriesUserMeal);
//
//        }
//
//
//        List<UserMealWithExceed> userMealWithExceed = new ArrayList<>();
//
//
//
//        for (UserMeal userMeal:mealList){
//            int dayCalories=caloriesPerDayMap.get(userMeal.getDateTime().toLocalDate());
//            if (TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime))
//                userMealWithExceed.add(new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), caloriesPerDay < dayCalories ));
//
//        }
//
//

        
    }


}
