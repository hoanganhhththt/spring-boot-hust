package com.example.project.response;

import java.util.ArrayList;
import java.util.List;

public class ListTransaction {
    private List<ListTransactionRes> data;
    private int totalRows;

    public ListTransaction() {
    }

    public ListTransaction(List<ListTransactionRes> data, int totalRows) {
        this.data = data;
        this.totalRows = totalRows;
    }

    public List<ListTransactionRes> getData() {
        return data;
    }

    public void setData(List<ListTransactionRes> data) {
        this.data = data;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public void addTransaction(ListTransactionRes theTran) {
        if(data == null) {
            data = new ArrayList<>();
        }
        data.add(theTran);
    }
}
