package yyz.elastic.service.impl;

import yyz.elastic.dao.source1.ItemsMapper;
import yyz.elastic.domain.Items;
import yyz.elastic.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemsMapper itemsMapper;

    @Override
    public List<Items> getItemList() {
        List<Items> list = itemsMapper.selectAll();

        return list;
    }

}