package com.fivium.pon1.controller;

import com.fivium.pon1.model.PersonForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/form")
public class PersonFormController {

  @GetMapping("/{id}")
  public ModelAndView renderForm(@PathVariable("id") String id) {
    //TODO ID must be not null
    PersonForm personForm = new PersonForm();
    personForm.setName("default");

    ModelAndView modelAndView = new ModelAndView("personForm");
    modelAndView.addObject("id", id);
    modelAndView.addObject("personForm", personForm);
    return modelAndView;
  }

  @PostMapping("/{id}")
  public ModelAndView handleSubmit(@PathVariable("id") String id, @Valid PersonForm personForm, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      ModelAndView modelAndView = new ModelAndView("personForm");
      modelAndView.addObject("id", id);
      modelAndView.addObject("personForm", personForm);
      return modelAndView;
    } else {
      return new ModelAndView("redirect:/greeting");
    }
  }

}