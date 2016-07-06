package com.smartling.connector.eloqua.sdk.rest.model;

import java.util.ArrayList;
import java.util.List;

public class Elements<T>
{
    private List<T> elements = new ArrayList<T>();
    private Integer page;
    private Integer pageSize;
    public Integer total;
}
