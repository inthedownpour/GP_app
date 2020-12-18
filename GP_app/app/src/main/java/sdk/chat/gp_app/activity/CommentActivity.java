package sdk.chat.gp_app.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import sdk.chat.gp_app.R;
import sdk.chat.gp_app.adapter.CommentListAdapter;
import sdk.chat.gp_app.view.CommentListView;
import sdk.chat.gp_app.view.ContentsItemView;
import sdk.chat.gp_app.view.PostInfo;
import sdk.chat.gp_app.view.PostInfo2;
import sdk.chat.gp_app.view.UserInfo;

import static sdk.chat.gp_app.view.Util.INTENT_PATH;
import static sdk.chat.gp_app.view.Util.showToast;

public class CommentActivity extends BasicActivity {
    private EditText comment;
    private StorageReference storageRef;
    private PostInfo postInfo;
    private ArrayList<String> pathList = new ArrayList<>();
    private static final String TAG = "CommentActivity";
    //private String userId = postInfo.getId().toString();

    //
    //private ImageView profileImageVIew;
    private RelativeLayout loaderLayout;
    private RelativeLayout buttonBackgroundLayout;
    private String profilePath;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        comment = findViewById(R.id.EditTextComment);
        Log.d(String.valueOf(comment), "comment" + String.valueOf(comment));


        final ArrayList<CommentListView> list = new ArrayList<>();
        list.add(new CommentListView(R.drawable.world, "1212", "사고싶어요!"));
        list.add(new CommentListView(R.drawable.world, "AAA", "저도 사고싶어요"));

        final ListView listView = findViewById(R.id.listView);
        final CommentListAdapter adapter = new CommentListAdapter(CommentActivity.this, list);
        listView.setAdapter(adapter);

        Button commentButton = (Button) findViewById(R.id.commentButton);
        commentButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputstr = comment.getText().toString();
                Log.d(inputstr, "inputstr: " + inputstr);
                list.add(new CommentListView(R.drawable.world, "damin", inputstr));
                //adapter.notifyDataSetChanged();

                CommentListAdapter adapter = new CommentListAdapter(CommentActivity.this, list);
                listView.setAdapter(adapter);

            }
        });
    }
}



