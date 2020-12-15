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

        /*final ArrayList<String> arrayList = new ArrayList<String>();
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        final ListView listview = (ListView) findViewById(R.id.commentListView);
        listview.setAdapter(adapter);

        Button commentButton = (Button) findViewById(R.id.commentButton);
        commentButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputstr = comment.getText().toString();
                Log.d(inputstr, "inputstr: " + inputstr);
                arrayList.add(inputstr);
                adapter.notifyDataSetChanged();

            }
        });
        Log.d(TAG, "onCreate: userID" + postInfo);*/

        ArrayList<CommentListView> list = new ArrayList<>();
        list.add(new CommentListView(R.drawable.world, "damin", "youoyuoo"));
        list.add(new CommentListView(R.drawable.world, "da", "dadadad"));

        ListView listView = findViewById(R.id.listView);
        CommentListAdapter adapter = new CommentListAdapter(CommentActivity.this, list);
        listView.setAdapter(adapter);

        /*Button commentButton = (Button) findViewById(R.id.commentButton);
        commentButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputstr = comment.getText().toString();
                Log.d(inputstr, "inputstr: " + inputstr);
                list.add(inputstr);
                adapter.notifyDataSetChanged();

            }
        });*/




        //MemberInitActivity참고
/*

        loaderLayout = findViewById(R.id.loaderLyaout);
        buttonBackgroundLayout = findViewById(R.id.buttonsBackgroundLayout);

        buttonBackgroundLayout.setOnClickListener(onClickListener);

        findViewById(R.id.checkButton).setOnClickListener(onClickListener);
*/

    }

/*    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0: {
                if (resultCode == Activity.RESULT_OK) {
                    profilePath = data.getStringExtra(INTENT_PATH);
                    buttonBackgroundLayout.setVisibility(View.GONE);
                }
                break;
            }
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.checkButton:
                    storageUploader();
                    break;
                case R.id.buttonsBackgroundLayout:
                    buttonBackgroundLayout.setVisibility(View.GONE);
                    break;
            }
        }
    };

    private void storageUploader() {
        final String name = ((EditText) findViewById(R.id.nameEditText)).getText().toString();
        final String phoneNumber = ((EditText) findViewById(R.id.phoneNumberEditText)).getText().toString();
        final String birthDay = ((EditText) findViewById(R.id.birthDayEditText)).getText().toString();
        final String address = ((EditText) findViewById(R.id.addressEditText)).getText().toString();

        final String comment =((EditText) findViewById(R.id.EditTextComment)).getText().toString();

        if (name.length() > 0 && phoneNumber.length() > 9 && birthDay.length() > 5 && address.length() > 0) {
            loaderLayout.setVisibility(View.VISIBLE);
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();
            user = FirebaseAuth.getInstance().getCurrentUser();
            final StorageReference mountainImagesRef = storageRef.child("comment/" + user.getUid() + "/profileImage.jpg");

            if (profilePath == null) {
                UserInfo userInfo = new UserInfo(name, phoneNumber, birthDay, address);
                storeUploader(userInfo);
            } else {
                try {
                    InputStream stream = new FileInputStream(new File(profilePath));
                    UploadTask uploadTask = mountainImagesRef.putStream(stream);
                    uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            if (!task.isSuccessful()) {
                                throw task.getException();
                            }
                            return mountainImagesRef.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) {
                                Uri downloadUri = task.getResult();

                                UserInfo userInfo = new UserInfo(name, phoneNumber, birthDay, address, downloadUri.toString());
                                storeUploader(userInfo);
                            } else {
                                showToast(CommentActivity.this, "회원정보를 보내는데 실패하였습니다.");
                            }
                        }
                    });
                } catch (FileNotFoundException e) {
                    Log.e("로그", "에러: " + e.toString());
                }
            }
        } else {
            showToast(CommentActivity.this, "회원정보를 입력해주세요.");
        }
    }

    private void storeUploader(UserInfo userInfo) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users").document(user.getUid()).set(userInfo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        showToast(CommentActivity.this, "회원정보 등록을 성공하였습니다.");
                        loaderLayout.setVisibility(View.GONE);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showToast(CommentActivity.this, "회원정보 등록에 실패하였습니다.");
                        loaderLayout.setVisibility(View.GONE);
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }

    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        startActivityForResult(intent, 0);
    }*/
}
