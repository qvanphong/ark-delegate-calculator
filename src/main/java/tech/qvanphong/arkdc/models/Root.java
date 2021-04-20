package tech.qvanphong.arkdc.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

//Converted by https://json2csharp.com/json-to-pojo
public class Root {
    @JsonProperty("current_page")
    public int getCurrent_page() {
        return this.current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    int current_page;

    @JsonProperty("data")
    public List<Datum> getData() {
        return this.data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    List<Datum> data;

    @JsonProperty("first_page_url")
    public String getFirst_page_url() {
        return this.first_page_url;
    }

    public void setFirst_page_url(String first_page_url) {
        this.first_page_url = first_page_url;
    }

    String first_page_url;

    @JsonProperty("from")
    public int getFrom() {
        return this.from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    int from;

    @JsonProperty("last_page")
    public int getLast_page() {
        return this.last_page;
    }

    public void setLast_page(int last_page) {
        this.last_page = last_page;
    }

    int last_page;

    @JsonProperty("last_page_url")
    public String getLast_page_url() {
        return this.last_page_url;
    }

    public void setLast_page_url(String last_page_url) {
        this.last_page_url = last_page_url;
    }

    String last_page_url;

    @JsonProperty("next_page_url")
    public String getNext_page_url() {
        return this.next_page_url;
    }

    public void setNext_page_url(String next_page_url) {
        this.next_page_url = next_page_url;
    }

    String next_page_url;

    @JsonProperty("path")
    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    String path;

    @JsonProperty("per_page")
    public String getPer_page() {
        return this.per_page;
    }

    public void setPer_page(String per_page) {
        this.per_page = per_page;
    }

    String per_page;

    @JsonProperty("prev_page_url")
    public Object getPrev_page_url() {
        return this.prev_page_url;
    }

    public void setPrev_page_url(Object prev_page_url) {
        this.prev_page_url = prev_page_url;
    }

    Object prev_page_url;

    @JsonProperty("to")
    public int getTo() {
        return this.to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    int to;

    @JsonProperty("total")
    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    int total;
}
