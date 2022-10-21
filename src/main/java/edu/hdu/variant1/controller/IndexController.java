package edu.hdu.variant1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * IndexController is just used for reminding user of the separation of the project.
 * We use Vue-cli to set the routers for the pages.
 */
@Controller
@Deprecated
public class IndexController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

}
