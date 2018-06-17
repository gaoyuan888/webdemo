package com.yyz.service.impl;

import com.yyz.domain.Items;
import com.yyz.service.ItemService1;
import com.yyz.dao.ItemsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl1 implements ItemService1 {

    @Autowired
    private ItemsMapper itemsMapper;

    public List<Items> getItemList() {
        List<Items> list = itemsMapper.selectAll();

        return list;
    }

}