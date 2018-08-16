package es624.service.impl;

import es624.dao.source2.ItemsMapper2;
import es624.domain.Items;
import es624.service.ItemService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl1 implements ItemService1 {

    @Autowired
    private ItemsMapper2 itemsMapper2;

    @Override
    public List<Items> getItemList() {
        List<Items> list = itemsMapper2.selectAll();

        return list;
    }

}