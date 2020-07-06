package ru.easium.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Controller
public class ErrorController {
    @Autowired
    private MessageSource messageSourсe;
    @GetMapping(value = "errors")
    public ModelAndView errorPage(HttpServletRequest httpRequest, Locale locale) {

        ModelAndView errorPage = new ModelAndView("error");
        String errorMsg = "";
        int httpErrorCode = (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");

        switch (httpErrorCode) {
            case 403: {
//                errorMsg = "У Вас нет доступа в этот раздел сайта!";
                errorMsg = messageSourсe.getMessage("error.403",new Object[]{}, locale);
                break;
            }
            case 404: {
//                errorMsg = "Страница не найдена!";
                errorMsg = messageSourсe.getMessage("error.404",new Object[]{}, locale);
                break;
            }
            case 500: {
//                errorMsg = "Ошибка на сервере! Обратитесь к администратору.";
                errorMsg = messageSourсe.getMessage("error.500",new Object[]{}, locale);
                break;
            }
        }
        errorPage.addObject("errorMsg", errorMsg);
        return errorPage;
    }
}
