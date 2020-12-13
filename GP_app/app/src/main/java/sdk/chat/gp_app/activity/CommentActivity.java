package sdk.chat.gp_app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import sdk.chat.gp_app.R;
import sdk.chat.gp_app.view.ContentsItemView;
import sdk.chat.gp_app.view.PostInfo;
import sdk.chat.gp_app.view.PostInfo2;

import static sdk.chat.gp_app.view.Util.INTENT_PATH;

public class CommentActivity extends BasicActivity{
    private EditText comment;
    private StorageReference storageRef;
    private PostInfo2 postInfo2;
    private ArrayList<String> pathList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        comment = findViewById(R.id.EditTextComment);
        Log.d(String.valueOf(comment), "comment" + String.valueOf(comment));

        final ArrayList<String> arrayList = new ArrayList<String>();
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList) ;
        final ListView listview = (ListView) findViewById(R.id.commentListView);
        listview.setAdapter(adapter);

        Button commentButton = (Button)findViewById(R.id.commentButton);
        commentButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                String inputstr = comment.getText().toString();
                Log.d(inputstr, "inputstr: " + inputstr);
                arrayList.add(inputstr);
                adapter.notifyDataSetChanged();

            }
        });

        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        postInfo2 = (PostInfo2) getIntent().getSerializableExtra("postInfo2");
        //postInit();
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case 0:
//                if (resultCode == Activity.RESULT_OK) {
//                    String path = data.getStringExtra(INTENT_PATH);
//                    pathList.add(path);
//
//                    //ContentsItemView contentsItemView = new ContentsItemView(this);
//
//                    if (comment == null) {
//                        parent.addView(contentsItemView);
//                    } else {
//                        for (int i = 0; i < parent.getChildCount(); i++) {
//                            if (parent.getChildAt(i) == selectedEditText.getParent()) {
//                                parent.addView(contentsItemView, i + 1);
//                                break;
//                            }
//                        }
//                    }
//
//                    contentsItemView.setImage(path);
//                    contentsItemView.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            buttonsBackgroundLayout.setVisibility(View.VISIBLE);
//                            selectedImageVIew = (ImageView) v;
//                        }
//                    });
//
//                    contentsItemView.setOnFocusChangeListener(onFocusChangeListener);
//                }
//                break;
//            case 1:
//                if (resultCode == Activity.RESULT_OK) {
//                    String path = data.getStringExtra(INTENT_PATH);
//                    pathList.set(parent.indexOfChild((View) selectedImageVIew.getParent()) - 1, path);
//                    Glide.with(this).load(path).override(1000).into(selectedImageVIew);
//                }
//                break;
//        }
//    }
//    private void postInit() {
//    }
}