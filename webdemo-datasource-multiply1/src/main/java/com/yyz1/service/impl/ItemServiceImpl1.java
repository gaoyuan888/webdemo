package com.yyz1.service.impl;

import com.yyz1.dao.source1.ItemsMapper;
import com.yyz1.dao.source2.ItemsMapper2;
import com.yyz1.domain.Items;
import com.yyz1.service.ItemService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl1 implements ItemService1 {

    @Autowired
    private ItemsMapper2 itemsMapper2;

    public List<Items> getItemList() {
        List<Items> list = itemsMapper2.selectAll();

        return list;
    }

}