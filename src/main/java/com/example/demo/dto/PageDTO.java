package com.example.demo.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageDTO<T> {
    private List<T> data;
    private boolean showFirstPage;
    private boolean showEndPage;
    private boolean showPrevious;
    private boolean showNext;
    private Integer page;
    private List<Integer> pages=new ArrayList<>();
    private Integer totalPage;

    public void setPageParam(Integer totalCount, Integer page, Integer size) {
        this.page=page;
        //总页数
        if(totalCount%size==0){
            totalPage=totalCount/size;
        }else{
            totalPage=totalCount/size+1;
        }

        if(page<1){
            page=1;
        }

        if(page>totalPage){
            page=totalPage;
        }

        pages.add(page);
        for(int i=1;i<=3;i++){
            if(page-i>0){
                pages.add(0,page-i);
            }

            if(page+i<=totalPage){
                pages.add(page+i);
            }
        }

        //是否展示第一页
        if(pages.contains(1)){
            showFirstPage=false;
        }else{
            showFirstPage=true;
        }

        //是否展示最后一页
        if(pages.contains(totalPage)){
            showEndPage=false;
        }else{
            showEndPage=true;
        }

        //是否展示前一页
        if(page==1){
            showPrevious=false;
        }else{
            showPrevious=true;
        }

        //是否展示后一页
        if(page==totalPage){
            showNext=false;
        }else{
            showNext=true;
        }

    }
}
