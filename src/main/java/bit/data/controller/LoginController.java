package bit.data.controller;

import bit.data.dto.UserDto;
import bit.data.service.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    UserServiceInter userService;

    @GetMapping("/find_id")
    public String userform() {
        return "/bit/login/login_find";
    }

    @GetMapping("/logout")
    @ResponseBody
    public void logout(HttpSession session) {
        //로그아웃시 제거되어야할 세션
        session.removeAttribute("login_ok");
        session.removeAttribute("login_id");
        session.removeAttribute("login_nick");
    }

    //[임시] 세션 저장
    @GetMapping("/call_session")
    @ResponseBody
    public void callSession(HttpSession session) {
        session.setMaxInactiveInterval(60 * 60 * 12);

        UserDto dto = userService.selectDataById(4);
//        System.out.println(dto.getUr_id());
//        System.out.println(dto.getEmail_id());
//        System.out.println(dto.getUr_nk());

        session.setAttribute("login_ok", "yes");
        session.setAttribute("login_id", dto.getUr_id());
        session.setAttribute("login_nick", dto.getUr_nk());
        session.setAttribute("login_ur_img", dto.getUr_img());
    }


    //[임시] 세션 제거
    @GetMapping("/del_session")
    @ResponseBody
    public void delSession(HttpSession session) {

        session.removeAttribute("login_ok");
        session.removeAttribute("login_id");
        session.removeAttribute("login_nick");
        session.removeAttribute("cfCmtCnt");
        session.removeAttribute("cfLkCnt'");
    }
}

