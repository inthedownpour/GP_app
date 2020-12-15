package sdk.chat.gp_app.view;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PostInfo implements Serializable {

    private String title;
    private ArrayList<String> contents;
    private ArrayList<String> formats;
    private String publisher;
    private Date createdAt;
    private String id;

    //수정 - endDate
    private String endDate;
    private String peopleNumber;

    public PostInfo(String title, ArrayList<String> contents, ArrayList<String> formats, String publisher, Date createdAt, String id, String endDate, String peopleNumber){
        this.title = title;
        this.contents = contents;
        this.formats = formats;
        this.publisher = publisher;
        this.createdAt = createdAt;
        this.id = id;
        this.endDate = endDate;
        this.peopleNumber = peopleNumber;
    }

    public PostInfo(String title, ArrayList<String> contents, ArrayList<String> formats, String publisher, Date createdAt, String endDate, String peopleNumber){
        this.title = title;
        this.contents = contents;
        this.formats = formats;
        this.publisher = publisher;
        this.createdAt = createdAt;
        this.endDate = endDate;
        this.peopleNumber = peopleNumber;

    }

    public Map<String, Object> getPostInfo(){
        Map<String, Object> docData = new HashMap<>();
        docData.put("title",title);
        docData.put("contents",contents);
        docData.put("formats",formats);
        docData.put("publisher",publisher);
        docData.put("createdAt",createdAt);
        docData.put("endDate", endDate);
        docData.put("peopleNumber", peopleNumber);
        return  docData;
    }

    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public ArrayList<String> getContents(){
        Log.d(String.valueOf(contents), "contents PostInfo60:" + contents);
        return this.contents;
    }
    public void setContents(ArrayList<String> contents){
        this.contents = contents;
    }

    public ArrayList<String> getFormats(){
        return this.formats;
    }
    public void setFormats(ArrayList<String> formats){
        this.formats = formats;
    }

    public String getPublisher(){
        return this.publisher;
    }
    public void setPublisher(String publisher){
        this.publisher = publisher;
    }

    public Date getCreatedAt(){
        return this.createdAt;
    }
    public void setCreatedAt(Date createdAt){
        this.createdAt = createdAt;
    }

    public String getId(){
        return this.id;
    }
    public void setId(String id){
        this.id = id;
    }

    public String getEndDate() {
        Log.d(endDate, "endData PostInfo95:" + endDate);
        return this.endDate;
    }
    public void setEndDate (String endDate){
        this.endDate = endDate;
    }

    public String getPeopleNumber(){
        return this.peopleNumber;
    }
    public void setPeopleNumber (String peopleNumber) {
        this.peopleNumber = peopleNumber;
    }
}
