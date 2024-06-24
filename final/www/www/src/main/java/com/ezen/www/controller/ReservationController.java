package com.ezen.www.controller;

import com.ezen.www.domain.MenuVO;
import com.ezen.www.domain.ReservationVO;
import com.ezen.www.repository.MenuMapper;
import com.ezen.www.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/reservation/*")
@RequiredArgsConstructor
@Slf4j
public class ReservationController {
    private final ReservationService rsv;
    private final MenuMapper menuMapper;

    //서비스 선택 페이지로 가기
    @GetMapping("/select-services")
    public void selectServices(){};

    //옵션 선택 페이지로 가기
    @GetMapping("/select-options")
    public String selectOptions(@RequestParam("idx") int idx, Model m){
        List<MenuVO> list = menuMapper.getMenuList();
        List<MenuVO> list2 = new ArrayList<>();
        if (idx == 1) {
            // idx가 1인 경우에는 meCode가 1, 2, 3에 해당하는 옵션을 선택
            for (MenuVO menu : list) {
                if (menu.getMeCode() >= 1 && menu.getMeCode() <= 3) {
                    list2.add(menu);
                }
            }
        } else if (idx == 2) {
            // idx가 2인 경우에는 meCode가 4, 5, 6에 해당하는 옵션을 선택
            for (MenuVO menu : list) {
                if (menu.getMeCode() >= 4 && menu.getMeCode() <= 6) {
                    list2.add(menu);
                }
            }
        }
        m.addAttribute("options", list2);
        return "/reservation/select-options";
    };

    //결제 및 예약 페이지로 가기
    @GetMapping("/booking")
    public String selectPayment(){
        return "/reservation/reservation-form";
    }

    @GetMapping("/reservations")
    public String listReservations(Model m) {
        List<ReservationVO> list = rsv.getList();
        m.addAttribute("reservations", list);
        return "/reservation/reservations";
    }

    //예약작성 양식 페이지로 가기
    @GetMapping("/new")
    public String createReservationForm(Model m) {
        m.addAttribute("reservation", new ReservationVO());
        return "/reservation/reservation-form";
    }

    //작성된 예약정보 인서트
    @PostMapping("/save")
    public String saveReservation(@ModelAttribute("reservation") ReservationVO rvo) {
        rsv.insert(rvo);
        return "redirect:/reservation/reservations";
    }

    //예약 수정 페이지로 code들고 가기
    @GetMapping("/edit/{id}")
    public String editReservationForm(@PathVariable long reCode, Model model) {
        ReservationVO rvo = rsv.getByReCode(reCode);
        model.addAttribute("reservation", rvo);
        return "/reservation/reservation-form";
    }

    //예약 수정하기
    @PostMapping("/edit/{id}")
    public String updateReservation(@PathVariable long reCode, @ModelAttribute("reservation") ReservationVO rvo) {
        rvo.setReCode(reCode);
        rsv.update(rvo);
        return "redirect:/reservation/reservations";
    }

    //예약 삭제하기
    @GetMapping("/delete/{id}")
    public String deleteReservation(@PathVariable long reCode) {
        rsv.delete(reCode);
        return "redirect:/reservation/reservations";
    }
}
