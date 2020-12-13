package sdk.chat.gp_app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.DatabaseMetaData;

import sdk.chat.gp_app.R;
import sdk.chat.gp_app.listener.OnPostListener;
import sdk.chat.gp_app.view.FirebaseHelper;
import sdk.chat.gp_app.view.PostInfo;
import sdk.chat.gp_app.view.ReadContentsVIew;

public class PostActivity extends BasicActivity {
    private PostInfo postInfo;
    private FirebaseHelper firebaseHelper;
    private ReadContentsVIew readContentsVIew;
    private LinearLayout contentsLayout;
    private Button btnAddComment;

    //댓글
    FirebaseFirestore firebaseFirestore;

    //수정
    private TextView endDateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        postInfo = (PostInfo) getIntent().getSerializableExtra("postInfo");

        contentsLayout = findViewById(R.id.contentsLayout);
        readContentsVIew = findViewById(R.id.readContentsView);

        //수정
        endDateTextView = findViewById(R.id.endDateTextView);

        firebaseHelper = new FirebaseHelper(this); //firebase 연결
        firebaseHelper.setOnPostListener(onPostListener);
        uiUpdate(); //업데이트


        //댓글
        btnAddComment = findViewById(R.id.commentButton);
        firebaseFirestore = firebaseFirestore.getInstance();
        btnAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CommentActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                if (resultCode == Activity.RESULT_OK) {
                    postInfo = (PostInfo)data.getSerializableExtra("postinfo");
                    contentsLayout.removeAllViews();
                    uiUpdate();
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.post, menu);
        //getMenuInflater: MenuInflater와 content 리턴?
        //inflate: xml로 미리 정의된 view(또는 menu 등)을 실제 객체화 시키는 용도
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                firebaseHelper.storageDelete(postInfo);
                return true;
            case R.id.modify:
                myStartActivity(WritePostActivity.class, postInfo);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    OnPostListener onPostListener = new OnPostListener() {
        @Override
        public void onDelete(PostInfo postInfo) {
            Log.e("로그 ","삭제 성공");
        }

        @Override
        public void onModify() {
            Log.e("로그 ","수정 성공");
        }
    };

    private void uiUpdate(){
        setToolbarTitle(postInfo.getTitle());
        readContentsVIew.setPostInfo(postInfo);

    }

    private void myStartActivity(Class c, PostInfo postInfo) {
        Intent intent = new Intent(this, c);
        intent.putExtra("postInfo", postInfo);
        startActivityForResult(intent, 0);
    }


}
