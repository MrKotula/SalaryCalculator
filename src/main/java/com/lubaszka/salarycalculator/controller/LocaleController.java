package com.lubaszka.salarycalculator.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import java.util.Locale;

@Controller
@AllArgsConstructor
public class LocaleController {

    private static final String SWITCH_LANG_URL = "/changeLanguage";

    private final LocaleResolver localeResolver;

    @RequestMapping(SWITCH_LANG_URL)
    public String changeLanguage(@RequestParam("lang") String lang, HttpServletRequest request) {
        Locale newLocale = new Locale(lang);
        localeResolver.setLocale(request, null, newLocale);

        return "redirect:" + request.getHeader("Referer");
    }
}
