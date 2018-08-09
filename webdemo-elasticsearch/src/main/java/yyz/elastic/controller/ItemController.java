package yyz.elastic.controller;
import yyz.elastic.domain.Items;
import yyz.elastic.service.ItemService;
import yyz.elastic.service.ItemService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemService1 itemService1;

    @RequestMapping("/itemList")
    public ModelAndView getItemsList() {
        // 查询商品列表
        List<Items> itemList = itemService.getItemList();
        // 把查询结果传递给页面
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("itemList", itemList); // addObject方法相当于放到request域上
        // 设置逻辑视图
        modelAndView.setViewName("itemList");
        // 返回结果
        return modelAndView;
    }

    @RequestMapping("/itemList1")
    public ModelAndView getItemsList1() {
        // 查询商品列表
        List<Items> itemList = itemService1.getItemList();
        // 把查询结果传递给页面
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("itemList", itemList); // addObject方法相当于放到request域上
        // 设置逻辑视图
        modelAndView.setViewName("itemList");
        // 返回结果
        return modelAndView;
    }

    @RequestMapping("/zoomify")
    public ModelAndView getImg() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("zoomify");
        return modelAndView;
    }
}