package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.service.HibernateLab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DatabaseController {

    @Autowired
    HibernateLab lab;

    @GetMapping(value = "initdb")
    public String indexInitdbHandler(Model model) {
        lab.dbinit();
        return "index";
    }

    @GetMapping(value = "dbexperiment")
    public String runExperiment() {
        lab.hibernateExperiment();
        return "index";
    }
}