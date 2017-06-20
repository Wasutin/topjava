package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.repository.InMemoryUserMealRepository;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

/**
 * User: Wasutin
 * Date: 11.06.2017.
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);

    private UserMealRepository repository;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        repository=new InMemoryUserMealRepository();

    }

//    private List<MealWithExceed> mealWithExceedList= MealsUtil.getFilteredWithExceeded(MealsUtil.getMealsList(), LocalTime.MIN, LocalTime.MAX, 2000);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        String id=request.getParameter("id");
        Meal userMeal=new Meal(id.isEmpty() ? null : Integer.valueOf(id),
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));
        LOG.info(userMeal.isNew() ? "Create {}" : "Update {}", userMeal);
        repository.save(userMeal);
        response.sendRedirect("meals");

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getParameter("action");

        if (action==null){
            LOG.info("getAll");
            req.setAttribute("mealList", MealsUtil.getWithExceeded(repository.getAll(), 2000));
            req.getRequestDispatcher("/meals.jsp").forward(req,resp);
        } else if (action.equals("delete")){
            int id=getId(req);
            LOG.info("Delete {}", id);
            repository.delete(id);
            resp.sendRedirect("meals");
        }else {
            final Meal meal=action.equals("create") ?
                    new Meal(LocalDateTime.now(), "", 1000) :
                    repository.get(getId(req));
            req.setAttribute("meal", meal);
            req.getRequestDispatcher("mealEdit.jsp").forward(req,resp);
        }



    }

    private int getId(HttpServletRequest request){
        String paramId= Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
