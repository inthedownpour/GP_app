package sdk.chat.gp_app.view;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import sdk.chat.gp_app.R;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ReadContentsVIew extends LinearLayout {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<SimpleExoPlayer> playerArrayList = new ArrayList<>();
    private int moreIndex = -1;

    public ReadContentsVIew(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public ReadContentsVIew(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
        initView();
    }

    private void initView(){
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setOrientation(LinearLayout.VERTICAL);
        layoutInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.view_post, this, true);
    }

    public void setMoreIndex(int moreIndex){
        this.moreIndex = moreIndex;
    }

    public void setPostInfo(PostInfo postInfo){
        TextView createdAtTextView = findViewById(R.id.createAtTextView);

        //수정
        //TextView endDateTextView = findViewsWithText(R.id.endDateTextView);


        createdAtTextView.setText(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(postInfo.getCreatedAt()));

        LinearLayout contentsLayout = findViewById(R.id.contentsLayout);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ArrayList<String> contentsList = postInfo.getContents();
        ArrayList<String> formatList = postInfo.getFormats();

        //수정
        String EndDateList = postInfo.getEndDate();
        String EndDateList2 = postInfo.getPeopleNumber();

        Log.d(EndDateList, "endDataList ReadContentsView76: " + EndDateList);

        for (int i = 0; i < contentsList.size(); i++) {
            if (i == moreIndex) {
                TextView textView = new TextView(context);
                textView.setLayoutParams(layoutParams);
                textView.setText("더보기...");
                contentsLayout.addView(textView);
                break;
            }

            String contents = contentsList.get(i);
            String formats = formatList.get(i);

            //수정
            String endDate = EndDateList;
            String peopleNumber = EndDateList2;
            Log.d(endDate, "endData ReadContentsView92: " + endDate);
            //String endDate =

            if(formats.equals("image")){
                ImageView imageView = (ImageView)layoutInflater.inflate(R.layout.view_contents_image, this, false);
                contentsLayout.addView(imageView);
                Glide.with(this).load(contents).override(1000).thumbnail(0.1f).into(imageView);

                //수정
                //Glide.with(this).load(endDate).override(1000).thumbnail(0.1f).into(imageView);

                TextView textView2 = (TextView) layoutInflater.inflate(R.layout.view_contents_button, this, false);
                //endDate = findViewById(R.id.endDate).toString();
                textView2.setText(endDate);
                Log.d(endDate, "endData ReadContentsView139: " + endDate);
                contentsLayout.addView(textView2);

                TextView textView3 = (TextView) layoutInflater.inflate(R.layout.view_contents_button, this, false);
                //endDate = findViewById(R.id.endDate).toString();
                textView3.setText(peopleNumber);
                contentsLayout.addView(textView3);

            }else if(formats.equals("video")){
                final PlayerView playerView = (PlayerView) layoutInflater.inflate(R.layout.view_contents_player, this, false);

                DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context,
                        Util.getUserAgent(context, getResources().getString(R.string.app_name)));
                MediaSource videoSource = new ProgressiveMediaSource.Factory(dataSourceFactory)
                        .createMediaSource(Uri.parse(contents));

                //수정
//                MediaSource videoSource2 = new ProgressiveMediaSource.Factory(dataSourceFactory)
//                        .createMediaSource(Uri.parse(endDate));


                SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(context);

                player.prepare(videoSource);

                player.addVideoListener(new VideoListener() {
                    @Override
                    public void onVideoSizeChanged(int width, int height, int unappliedRotationDegrees, float pixelWidthHeightRatio) {
                        playerView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));
                    }
                });

                playerArrayList.add(player);

                playerView.setPlayer(player);
                contentsLayout.addView(playerView);
            }else{
                TextView textView = (TextView) layoutInflater.inflate(R.layout.view_contents_text, this, false);
                textView.setText(contents);
                contentsLayout.addView(textView);




                //수정


            }

        }

    }

    public ArrayList<SimpleExoPlayer> getPlayerArrayList() {
        return playerArrayList;
    }
}
