package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

/**
 * User: Wasutin
 * Date: 11.06.2017.
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);

    private List<MealWithExceed> mealWithExceedList= MealsUtil.getFilteredWithExceeded(MealsUtil.getMealsList(), LocalTime.MIN, LocalTime.MAX, 2000);



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("forward to meals");

        req.setAttribute("mealList", mealWithExceedList);

        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }
}
