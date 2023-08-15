package com.example.project.response;

import com.example.project.entity.Transaction;
import com.example.project.entity.User;

import java.util.ArrayList;
import java.util.List;

public class ListUserRes {
    private List<DetailUser> data;
    private int totalRows;

    public ListUserRes(List<DetailUser> data, int totalRows) {
        this.data = data;
        this.totalRows = totalRows;
    }

    public ListUserRes() {
    }

    public List<DetailUser> getData() {
        return data;
    }

    public void setData(List<DetailUser> data) {
        this.data = data;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public void addUser(DetailUser theUser) {
        if(data == null) {
            data = new ArrayList<>();
        }
        data.add(theUser);
    }
}
