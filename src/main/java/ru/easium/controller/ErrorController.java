package ru.easium.controller;

import com.sun.deploy.net.HttpResponse;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

public class ErrorController {
    public ModelAndView errorPage(HttpServletRequest httpRequest) {
        ModelAndView errorPage = new ModelAndView("error");
        String errorMsg = "";
        int httpErrorCode = (Integer) httpRequest.getAttribute("javax.servlet.error_code");

        switch (httpErrorCode) {
            case 403: {
                errorMsg = "У Вас нет доступа в этот раздел сайта!";
                break;
            }
            case 404: {
                errorMsg = "Страница не найдена!";
                break;
            }
            case 500: {
                errorMsg = "Ошибка на сервере! Обратитесь к администратору.";
                break;
            }
        }
        errorPage.addObject("errorMsg", errorMsg);
        return errorPage;
    }
}
