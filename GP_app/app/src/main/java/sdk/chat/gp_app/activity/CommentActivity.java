package sdk.chat.gp_app.activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.StorageReference;
import java.util.ArrayList;
import sdk.chat.gp_app.R;
import sdk.chat.gp_app.adapter.CommentListAdapter;
import sdk.chat.gp_app.view.CommentListView;
import sdk.chat.gp_app.view.PostInfo;


public class CommentActivity extends BasicActivity {
    private EditText comment;
    private StorageReference storageRef;
    private PostInfo postInfo;
    private ArrayList<String> pathList = new ArrayList<>();
    private static final String TAG = "CommentActivity";

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
       /* list.add(new CommentListView(R.drawable.world, "dd", "dd!"));
        list.add(new CommentListView(R.drawable.world, "dd", "dd"));*/

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



