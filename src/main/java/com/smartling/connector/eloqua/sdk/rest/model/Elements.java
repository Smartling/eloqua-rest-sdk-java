package com.smartling.connector.eloqua.sdk.rest.model;

import java.util.ArrayList;
import java.util.List;

public class Elements<T>
{
    public List<T> elements = new ArrayList<T>();
    public Integer page;
    public Integer pageSize;
    public Integer total;
}
