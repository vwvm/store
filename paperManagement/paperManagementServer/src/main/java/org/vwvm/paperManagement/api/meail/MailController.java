package org.vwvm.paperManagement.api.meail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MailController {
    @Autowired
    private MailService mailService;

    @GetMapping("/mail/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("mail/sendMail");
        mv.addObject("from", mailService.getMailSendFrom());
        return mv;
    }

    @PostMapping("/mail/send")
    public MailVo sendMail(MailVo mailVo) {
        return mailService.sendMail(mailVo);
    }

}