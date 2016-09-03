package cn.isif.videolist;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cn.isif.videolist.video.MediaHandler;
import cn.isif.videolist.video.VideoPlayerView;

public class SingleVideoActivity extends AppCompatActivity {
    String videoUrl = "http://123.125.39.155/246/19/10/bcloud/101596/ver_00_22-1061839139-avc-417943-aac-32003-180000-10324685-87ad05f393453d903e33f60536e899db-1472118004452.mp4?crypt=23aa7f2e515&b=458&nlh=4096&nlt=60&bf=90&p2p=1&video_type=mp4&termid=2&tss=no&platid=2&splatid=206&its=0&qos=5&fcheck=0&amltag=59888&mltag=59888&proxy=2099711171,2099711171,467476980&uid=2071069400.rp&keyitem=GOw_33YJAAbXYE-cnQwpfLlv_b2zAkYctFVqe5bsXQpaGNn3T1-vhw..&ntm=1472803200&nkey=927bc02468c541a01003b299e1de4a79&nkey2=185d3222b473f956e75c759a8ff358a8&geo=CN-1-12-2&mmsid=205090303&tm=1472784661475&key=c33719bc83ff48f9656ba0aa982a2104&payff=0&cuid=101596&vtype=13&dur=180&p1=3&p2=31&p3=310&cf=h5-android&p=101&playid=0&tag=mobile&sign=bcloud_101596&pay=0&ostype=android&hwtype=un&errc=0&gn=1302&vrtmcd=102&buss=59888&cips=123.114.2.216";
    VideoPlayerView playerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_video);
        playerView = (VideoPlayerView)this.findViewById(R.id.video);
        MediaHandler.release();
        playerView.setVisibility(View.VISIBLE);
        playerView.loadAndPlay(MediaHandler.getInstance(), videoUrl, 0,
                false);
        playerView.setVideoPlayCallback(new VideoPlayerView.VideoPlayCallbackImpl() {
            @Override
            public void onCloseVideo() {

            }

            @Override
            public void onSwitchPageType() {

            }

            @Override
            public void onPlayFinish() {

            }
        });
    }
}
