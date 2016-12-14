package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDAO;
import ru.javawebinar.topjava.dao.MealDAOList;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;


/**
 * Created by Mykhailo on 12.12.2016.
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("redirect to meals");
        MealDAO mealDAO = new MealDAOList();
        List<Meal> meals = mealDAO.getAll();
        List<MealWithExceed> mealsWithExceed = MealsUtil.getFilteredWithExceeded(meals, null, null, 2000);
        request.setAttribute("meals", mealsWithExceed);

        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
