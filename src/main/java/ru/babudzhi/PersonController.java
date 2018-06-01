package ru.babudzhi;

import org.h2.engine.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.babudzhi.person.Person;
import javax.servlet.ServletException;

import java.io.IOException;

import java.util.Collections;
import java.util.Map;

@Controller
public class PersonController {

    @Autowired
    ServiceBD ser1;

    @RequestMapping(value = ("/bd"), method = RequestMethod.POST)
    public ModelAndView doPost(@RequestParam String firstName, @RequestParam String middleName,
                               @RequestParam String lastName, @RequestParam boolean dropTable,
                               @RequestParam String userId, @RequestParam Session session1)
            throws ServletException, IOException {

        //public ModelAndView doPost(@RequestParam Person pers1) throws ServletException, IOException {
        //public ModelAndView doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Person pers1 = new Person();//context.getBean("pers1", Person.class);
        pers1.setName1(new String(firstName.getBytes("iso-8859-1"), "utf8"));
        pers1.setName2(new String(middleName.getBytes("iso-8859-1"), "utf8"));
        pers1.setName3(new String(lastName.getBytes("iso-8859-1"), "utf8"));

        if(userId.equals(session1.getId()))
            ser1.controller(dropTable, pers1);

        return new ModelAndView("index.jsp");
    }
    @RequestMapping(value=("/res"), method = RequestMethod.GET)
    public ModelAndView doGet(){
        Model model = (Model) Collections.singletonMap("list", ser1.getListResults());

        return new ModelAndView("results.jsp", (Map<String, ?>) model);
    }
}
