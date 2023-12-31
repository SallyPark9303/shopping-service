package shoppingproject.shop.web.item;

import jakarta.persistence.PreUpdate;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shoppingproject.shop.domain.Member;
import shoppingproject.shop.domain.common.FileUtils;
import shoppingproject.shop.domain.item.Item;
import shoppingproject.shop.repository.ItemRepository;
import shoppingproject.shop.service.ItemService;
import shoppingproject.shop.web.CommonConst;

import java.net.MalformedURLException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;
    private final FileUtils fileutils;

    @ModelAttribute("member")
    public String checkMember(HttpServletRequest request, Model model){
        log.info("home");
        HttpSession session = request.getSession(true);
        Member loginMember = (Member)  session.getAttribute(CommonConst.LOGIN_MEMBER);
        //세션에 회원 데이터가 없으면 home
        if (loginMember == null) {
            model.addAttribute("member", null);
            return "/home";
        }
        //세션이 유지되면 로그인으로 이동
        model.addAttribute("member", loginMember);

        return "/home";
    }


    @GetMapping("/detail")
    public String detailPage(@RequestParam("itemId") long itemid, Model model){
        Item findItem = itemRepository.findOne(itemid);
        model.addAttribute("item",findItem);
        return "/item/detail";

    }
    @GetMapping("/listPage/{no}")
    public String listPage(@PathVariable("no") long no, Model model){
        List<Item> items = itemRepository.findByCategory(no);
        model.addAttribute("items",items);
        return "/item/list";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        log.info("filename ="+filename);
        return  new UrlResource("file:" + fileutils.getFullPath(filename));
    }

}
