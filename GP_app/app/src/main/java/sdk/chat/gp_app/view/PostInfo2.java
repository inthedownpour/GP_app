package sdk.chat.gp_app.view;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PostInfo2 implements Serializable {
    private ArrayList<String> contents;
    private String id;


    public PostInfo2(ArrayList<String> contents, String id){
        this.contents = contents;
        this.id = id;
    }

    public PostInfo2(ArrayList<String> contents){
        this.contents = contents;
    }

    public Map<String, Object> getPostInfo(){
        Map<String, Object> docData = new HashMap<>();
        docData.put("contents",contents);
        return  docData;
    }

    public ArrayList<String> getContents(){
        Log.d(String.valueOf(contents), "contents PostInfo60:" + contents);
        return this.contents;
    }
    public void setContents(ArrayList<String> contents){
        this.contents = contents;
    }

    public String getId(){
        return this.id;
    }
    public void setId(String id){
        this.id = id;
    }

}
