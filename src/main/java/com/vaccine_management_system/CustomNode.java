package com.vaccine_management_system;

public class CustomNode<N> {
    public CustomNode<N> next = null;
    private N data;

    public CustomNode(N data) {
        this.data=data;
    }

    public N getData() {return data;}
    public void setData(N d) {data=d;}
}
