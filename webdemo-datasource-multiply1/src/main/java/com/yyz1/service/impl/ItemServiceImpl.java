package com.yyz1.service.impl;

import com.yyz1.dao.source1.ItemsMapper;
import com.yyz1.domain.Items;
import com.yyz1.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemsMapper itemsMapper;

    public List<Items> getItemList() {
        List<Items> list = itemsMapper.selectAll();

        return list;
    }

}