package com.smartling.connector.eloqua.sdk.rest.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Elements<T>
{
    private List<T> elements = new ArrayList<>();
    private Integer page;
    private Integer pageSize;
    private Integer total;

    public Elements()
    {
        this.elements = Collections.emptyList();
        this.page = 1;
        this.pageSize = 1000;
        this.total = 0;
    }

    public Elements(T element)
    {
        this.elements = Collections.singletonList(element);
        this.page = 1;
        this.pageSize = 1000;
        this.total = 1;
    }

    public List<T> getElements()
    {
        return elements;
    }

    public void setElements(final List<T> elements)
    {
        this.elements = elements;
    }

    public Integer getPage()
    {
        return page;
    }

    public void setPage(final Integer page)
    {
        this.page = page;
    }

    public Integer getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(final Integer pageSize)
    {
        this.pageSize = pageSize;
    }

    public Integer getTotal()
    {
        return total;
    }

    public void setTotal(final Integer total)
    {
        this.total = total;
    }
}
