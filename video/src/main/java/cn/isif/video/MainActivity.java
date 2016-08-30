package cn.isif.video;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView)this.findViewById(R.id.web);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
//        webView.getSettings().setPluginsEnabled(true);//可以使用插件
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setDefaultTextEncodingName("UTF-8");
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAppCacheMaxSize(1024*1024*8);
        String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();
        webView.getSettings().setAppCachePath(appCachePath);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAppCacheEnabled(true);

        String _html = "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>\n" +
                "\t\t\t<html xmlns='http://www.w3.org/1999/xhtml'>\n" +
                "\t\t\t<head>\n" +
                "\t\t\t<meta name='viewport' content='width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no' />\n" +
                "\t\t\t<meta http-equiv='Content-Type' content='text/html;charset=utf-8' />\n" +
                "\t\t\t<meta name='format-detection' content='telephone=no'/>\n" +
                "\t\t\t<title></title>\n" +
                "\t\t\t<script type='text/javascript' src='http://static.fblife.com/static/js/min.js'></script>\n" +
                "\t\t\t<script type='text/javascript'>\n" +
                "\t\t\t/***$(document).ready(function(){\n" +
                "\t\t\t\tvar lazyLoadImg = $('.lazy');\n" +
                "\t\t\t\tvar len = lazyLoadImg.length;\n" +
                "\t\t\t\tif(len<1){\n" +
                "\t\t\t\t\treturn;\n" +
                "\t\t\t\t}\n" +
                "\t\t\t\tfor(var i=0;i<len;i++)\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\tvar imgId = lazyLoadImg.eq(i);\n" +
                "\t\t\t\t\tvar img = new Image();\n" +
                "\t\t\t\t\timg.index = i;\n" +
                "\t\t\t\t\timg.src = imgId.attr('data-original');\n" +
                "\t\t\t\t\timg.onload = function(){\n" +
                "\t\t\t\t\t\tvar _this = this;\n" +
                "\t\t\t\t\t\tlazyLoadImg.eq(_this.index).attr('src',_this.src);\n" +
                "\t\t\t\t\t\tlazyLoadImg.eq(_this.index).css({'display':'none'});\n" +
                "\t\t\t\t\t\tlazyLoadImg.eq(_this.index).fadeIn(1000);\n" +
                "\t\t\t\t\t}\t\n" +
                "\t\t\t\t}\n" +
                "\t\t\t});****/\n" +
                "\t\t\t</script>\n" +
                "\t\t\t<style>\n" +
                "\t\t\t\thtml,body,h1,h2,h3,h4,h5,h6,p,br,form,input,button,textarea,select,fieldset,blockquote,ul,ol,li,dl,dt,dd,pre{margin:0;padding:0;}\n" +
                "\t\t\t\tbody{ font-size:12px; background-color:#fff; font-family:'Microsoft Yahei','»ªÎÄºÚÌå','Lucida Grande','Helvetica','Arail','Verdana','sans-serif';margin:0;padding:0;}\n" +
                "\t\t\t\timg{ border:0px;}\n" +
                "\t\t\t\thtml,body{-webkit-touch-callout: none; word-break:break-word;}\n" +
                "\t\t\t\t.clear {clear:both;height:0px;overflow:hidden;}\n" +
                "\t\t\t\t.clearfix:after {clear:both;content:;display:block;font-size:0;height:0;overflow:hidden;visibility:hidden;}\n" +
                "\t\t\t\t.clearfix {*zoom:1;}\n" +
                "\t\t\t\tul{ padding:0px; margin:0px;}\n" +
                "\t\t\t\tli{ list-style-type:none;}\n" +
                "\t\t\t\ta{ color:#000; font-size:12px; text-decoration:none;}   \n" +
                "\t\t\t\ta:link{ color:#000;}\n" +
                "\t\t\t\ta:active{ color:#000;}\n" +
                "\t\t\t\ta:visited{ color:#000;}\n" +
                "\t\t\t\ta:hover{ color:#000;}\n" +
                "\t\t\t\tbr {line-height:10px;}\n" +
                "\t\t\t\t.main{ width:100%;}\n" +
                "\t\t\t\t.main h1{ line-height:25px; font-size:18px;font-weight:bold!important; padding:0 15px;font-family:'Microsoft Yahei','»ªÎÄºÚÌå','Lucida Grande','Helvetica','Arail','Verdana','sans-serif'; color:#fff; text-align:justify;}\n" +
                "\t\t\t\t.line_bor4 {padding:0px 15px;}\n" +
                "\t\t\t\t.main p{font-size:10px; line-height:15px;color:#fff;overflow:hidden;}\n" +
                "\t\t\t\t.main p span{ padding-right:13px;height:28px;}\n" +
                "\t\t\t\t.main p .f_date {padding-right:13px;font-style:normal;}\n" +
                "\t\t\t\t.main2 { width:100%;margin-top:27px;padding-bottom:20px;font-size:16px;color:#2b2b2b;padding:0px 10px 0px; -webkit-box-sizing:border-box;-moz-box-sizing:border-box;text-align:justify;text-justify:inter-ideograph;}\n" +
                "\t\t\t\t.elegant {text-align:justify;} \n" +
                "\t\t\t\t.main2 p{ font-size:15px; color:#313131!important;line-height:25px;}\n" +
                "\t\t\t\t.main2 .img{ padding:0px 10px;}\n" +
                "\t\t\t\t.main2 .img img{ padding:0px 10px;height:auto;}\n" +
                "\t\t\t\t.main2 p img,.testdiv img{max-width:100%; padding:10px 0;}\n" +
                "\t\t\t\t.main3 {width:100%;height:auto;padding:5px 0px 5px 0px;margin-top:10px;overflow:hidden;}\n" +
                "\t\t\t\t.main2 .my_video{ width:300px; height:150px; margin:0px auto; margin-top:10px;margin-bottom:10px;}\n" +
                "\t\t\t\t@media screen and (min-width:375px) and(max-width:413px){\n" +
                "\t\t\t\t\t.main2 .my_video{width:355px; height:177px;}\n" +
                "\t\t\t\t}\n" +
                "\t\t\t\t\n" +
                "\t\t\t\t@media screen and(min-width:414px){\n" +
                "\t\t\t\t\t.main2 .my_video{width:394px;height:197px;}\n" +
                "\t\t\t\t}\n" +
                "\t\t\t\t\n" +
                "\t\t\t\t.m3_jcpl {width:294px;}\n" +
                "\t\t\t\t.m3_jcpltbor {width:32px;float:left;}\n" +
                "\t\t\t\t.m3_jword {color:#000;font-size:16px;height:32px;line-height:32px;background-color:#f0f0f0;}\n" +
                "\t\t\t\t.m3_jword img {width:18px;padding:0px 6px 0px 6px;vertical-align:middle;}\n" +
                "\t\t\t\t.m3_con {width:100%;padding-top:3px;position:relative;display:inline;}\n" +
                "\t\t\t\t.m3_con_one {position:relative;padding:0px 0px 12px 44px;margin-top:14px;}\n" +
                "\t\t\t\t.m3_con_one h4 {width:100%;overflow:hidden;line-height:1.0;position:relative;}\n" +
                "\t\t\t\t.m3_con_one h4 a {color:#596a95;font-size:15px;font-weight:bold;}\n" +
                "\t\t\t\t.m3_con_one h4 span {position:absolute;right:0px;top:3px;color:#aaaaaa;font-size:12px;font-weight:normal;}\n" +
                "\t\t\t\t.m3_con_one p {color:#b8b8b8;font-size:10px;-webkit-text-size-adjust:none;}\n" +
                "\t\t\t\t.m3_wz {color:#313131;font-size:13px;line-height:20px;padding-top:5px;width:100%;padding-left:1px;}\n" +
                "\t\t\t\t.m3_wz img {width:12px;vertical-align:-1px;margin-right:7px;}\n" +
                "\t\t\t\t.m3_head {width:35px;height:35px;position:absolute;top:1px;left:0px;float:left;}\n" +
                "\t\t\t\t.m3_head a img {width:35px;height:35px;border-radius:4px;}\n" +
                "\t\t\t\t.m3_teo {padding:0px 11px 1px 11px;overflow:hidden;}\n" +
                "\t\t\t\t.m3_more {width:100%;height:32px;margin-bottom:11px;margin-top:10px;}\n" +
                "\t\t\t\t.m3_more a {margin:0px 11px 0px 11px;height:32px;text-align:center;line-height:32px;background-color:#eeeeee;display:block;color:#7d7d7d;font-size:13px;padding-top:0px;margin-top:0px;}\n" +
                "\n" +
                "\t\t\t\t.g_xgyd {margin:2px 0px 0px 0px;width:100%;overflow:hidden;}\n" +
                "\t\t\t\t.g_xgyd li {width:100%;line-height:19px;float:left;padding:6px 20px 6px 0;}\n" +
                "\t\t\t\t.g_xgyd li,.g_xgyd li:before,.g_xgyd li:after {-moz-box-sizing: border-box;-webkit-box-sizing: border-box;box-sizing: border-box; }\n" +
                "\t\t\t\t.g_xgyd li.g_xgxian {overflow:hidden;padding:0px;line-height:0px;}\n" +
                "\t\t\t\t.g_xgyd li.g_xgxian img {height:1px;max-width:100%;}\n" +
                "\t\t\t\t.g_xgyd li a {padding-top:3px;font-size:14px;display:inline-block;width:100%;}\n" +
                "\t\t\t\t.g_xgyd li.g_xgxian {background:none;}\n" +
                "\n" +
                "\t\t\t\t.color_1{background:#3699dc!important;}\n" +
                "\t\t\t\t.color_2{background:#47b49f!important;}\n" +
                "\t\t\t\t.color_3{background:#55cfdc!important;}\n" +
                "\t\t\t\t.color_4{background:#9b5ab6!important;}\n" +
                "\t\t\t\t.color_5{background:#efc50e!important;}\n" +
                "\t\t\t\t.color_6{background:#ad0101!important;}\n" +
                "\t\t\t\t.color_7{background:#e47f23!important;}\n" +
                "\t\t\t\t.color_8{background:#7e8c8d!important;}\n" +
                "\t\t\t\t.color_9{background:#333333!important;}\n" +
                "\t\t\t\t.color_10{background:#333333!important;}\n" +
                "\n" +
                "\t\t\t\t.n_ms_lt {padding:0px 11px;height:auto;margin-bottom:9px;overflow:hidden;}\n" +
                "\t\t\t\t.n_msco {background-color:#434343;overflow:hidden;}\n" +
                "\t\t\t\t.n_ytj {width:37px;line-height:16px;text-align:center;color:#f3ece1;font-size:12px;float:left;padding-bottom:5px;background-color:#707070;}\n" +
                "\t\t\t\t.n_ytj span {width:24px;float:left;display:inline;margin:5px 0px 0px 7px;}\n" +
                "\t\t\t\t.n_ytx {float:left;width:260px;padding-bottom:5px;}\n" +
                "\t\t\t\t.n_gtx {width:27px;height:26px;float:left;overflow:hidden;margin:8px 7px 0px 8px;display:inline;}\n" +
                "\t\t\t\t.n_gtx a img {width:27px;height:26px;}\n" +
                "\t\t\t\t.n_gnei {float:left;width:218px;}\n" +
                "\t\t\t\t.n_gnei h6 {font-size:12px;height:14px;overflow:hidden;margin-top:6px;}\n" +
                "\t\t\t\t.n_gnei h6 a {color:#fefefe;}\n" +
                "\t\t\t\t.n_btjs {color:#b5b5b5;font-size:8px;-webkit-text-size-adjust:none;margin-top:3px;}\n" +
                "\t\t\t\t.n_btjs span {margin-right:10px;}\n" +
                "\t\t\t\t.m_pll {color:#535353;font-size:14px;line-height:18px;padding-top:5px;}\n" +
                "\t\t\t\t.m_pll img {width:18px;vertical-align:middle;padding-right:6px;}\n" +
                "\n" +
                "\t\t\t\t.new_xgyd {width:100%;}\n" +
                "\t\t\t\t.new_xgyd_tit {height:32px;background-color:#f0f0f0;color:#000000;font-size:16px;line-height:32px;}\n" +
                "\t\t\t\t.new_xgyd_tit img {width:18px;vertical-align:top;padding:8px 6px 0px 6px;}\n" +
                "\t\t\t\t.new_yd_tim {color:#949494;font-size:11px;padding-top:2px;}\n" +
                "\t\t\t\t.new_border {width:100%;float:left;height:1px;overflow:hidden;background-color:red;}\n" +
                "\t\t\t\t.new_border img {height:1px;max-width:100%;}\n" +
                "\t\t\t\t.pl_new_beijing {min-width:100%;position:absolute;top:0px;left:0px;}\n" +
                "\t\t\t\t.pl_new_beijing img {height:32px;max-width:100%;}\n" +
                "\t\t\t\t.elegant p a {color:#1565be;}\n" +
                "\t\t\t\t.msi_pl {padding:4px 11px 0px;}\n" +
                "\t\t\t\t.testdiv a {font-size:16px;}\n" +
                "\t\t\t\t.main_page {padding:14px 14px;color:#323232;font-weight:bold;font-size:17px;}\n" +
                "\t\t\t\t\n" +
                "\t\t\t\t.my_video{ margin:0px 10px;}\n" +
                "\t\t\t\t.my_video video{ width:100%; margin:0px auto;}\n" +
                "\t\t\t\t.main_arti{padding-top:50px; padding-bottom:30px; }\n" +
                "\t\t\t\t.main_abs{ width:100%; left:0; bottom:0; padding-top:15px; padding-bottom:15px; background:rgba(0,0,0,0.5);position:absolute;}\n" +
                "\t\t\t\t\n" +
                "\t\t\t\t.main_abs.color_1{ background:rgba(54,153,220,0.5)!important;}\n" +
                "\t\t\t\t.main_abs.color_2{background:rgba(71,180,159,0.5)!important;}\n" +
                "\t\t\t\t.main_abs.color_3{ background:rgba(85,207,220,0.5)!important;}\n" +
                "\t\t\t\t.main_abs.color_4{ background:rgba(155,90,182,0.5)!important;}\n" +
                "\t\t\t\t.main_abs.color_5{ background:rgba(239,197,14,0.5)!important;}\n" +
                "\t\t\t\t.main_abs.color_6{ background:rgba(173,1,1,0.5)!important;}\n" +
                "\t\t\t\t.main_abs.color_7{ background:rgba(228,127,35,0.5)!important;}\n" +
                "\t\t\t\t.main_abs.color_8{ background:rgba(126,140,121,0.5)!important;}\n" +
                "\t\t\t\t.main_abs.color_9{ background:rgba(51,51,51,0.5)!important;}\n" +
                "\t\t\t\t.main_abs.color_10{ background:rgba(51,51,51,0.5)!important;}\n" +
                "\t\t\t\t\n" +
                "\t\t\t\t.main .aimg{width:100%; display:block; vertical-align:top; height:auto;}\n" +
                "\t\t\t\t.main{position:relative;width:100%;}\n" +
                "\t\t\t\t\n" +
                "\t\t\t\t.s_banner{ margin:0 10px; margin-top:10px;}\n" +
                "\t\t\t\t.s_banner a,.s_banner a img{ width:100%!important;display:block; height:auto!important; vertical-align:top;}\n" +
                "\t\t\t</style>\n" +
                "\t\t\t</head>\n" +
                "\t\t\t<body>\n" +
                "\t\t\t\n" +
                "\t\t\t<div class='main'><img class='aimg' src=http://img10.fblife.com/attachments/20160722/1469177144.jpg.680x0.jpg><div class='main_arti color_10 main_abs'><h1>ÐÔ¸ÐÅ®Ë¾»úÏÝ³µ´ó¼¯½õ</h1>\n" +
                "\t\t\t\t\t<div class='line_bor4'><p><span class='f_date'>07-22</span><span class='author'>Ô½Ò°e×å</span><span class='source'>ÕÔÓîæº</span></p></div>\n" +
                "\t\t\t\t</div>\n" +
                "\t\t\t</div>\n" +
                "\n" +
                "\t\t\t<div class='main2 elegant'>\n" +
                "\t\t\t    <div class='my_video' ><script id='autoJsf2296a4eaf' type='text/javascript'>\n" +
                "\t\t\t\t\t\t\t\tvar letvcloud_player_conf = {'uu': 'c61e45b7fe','vu': 'f2296a4eaf','auto_play': 1,'gpcflag': 1,'dbd':'LETV'};\n" +
                "\t\t\t\t\t\t\t</script>\n" +
                "\t\t\t\t\t\t\t<script type='text/javascript' src='http://yuntv.letv.com/bcloud.js'></script></div><p>ÏÝ³µ£¬Õâ¼þÔÚÔ½Ò°ÖÐÈÃÈËÄÕ»ðµÄÊÂÇéÈç¹û·¢ÉúÔÚÐÔ¸ÐµÄÅ®º¢ÉíÉÏÓÖ»áÔõÑùÄØ£¿ÊÓÆµÎªÎÒÃÇ»ã¼¯ÁËÐí¶àÐÔ¸ÐÅ®Ë¾»úµÄÏÝ³µ»\u00ADÃæ£¬ËýÃÇ»áÈçºÎ»¯½âÄØ£¿<br /></p></div>\n" +
                "\t\t\t<div class='main3'>\t\t\t\t\t\t\n" +
                "\t\t\t\t<!-- ÂÛÌ³ÌáÈ¡ -->\n" +
                "\t\t\t\t\t\n" +
                "\t\t\t\t<!-- ÂÛÌ³ÌáÈ¡ end-->\n" +
                "\t\t    </div>\n" +
                "\t\t\t\n" +
                "\t\t\t<div class='s_banner'>\n" +
                "\t\t\t\t<!-- ÐÂÎÅÔÄ¶ÁÒ³µ×²¿Í¨À¸ -->\n" +
                "<SCRIPT LANGUAGE='JavaScript1.1' SRC='http://cast.aim.yoyi.com.cn/afp/door/;ap=l64537a5127b7a020001;ct=js;pu=n1428243fc09e7230001;/?' charset='utf-8'>\n" +
                "</SCRIPT>\n" +
                "<NOSCRIPT>\n" +
                "<IFRAME SRC=' http://cast.aim.yoyi.com.cn/afp/door/;ap=l64537a5127b7a020001;ct=if;pu=n1428243fc09e7230001;/?' NAME='adFrame_l64537a5127b7a020001' WIDTH='0' HEIGHT='0' FRAMEBORDER='no' BORDER='0' MARGINWIDTH='0' MARGINHEIGHT='0' SCROLLING='no'>\n" +
                "</IFRAME>\n" +
                "</NOSCRIPT>\n" +
                "<!-- ÐÂÎÅÔÄ¶ÁÒ³µ×²¿Í¨À¸/End -->\n" +
                "\t\t\t</div>\n" +
                "\t\t\t<div style='width:0; height:0; overflow:hidden; zoom:1;'>\n" +
                "\t\t\t\t<script type='text/javascript' src='http://tajs.qq.com/stats?sId=53230735' charset='UTF-8'></script>\n" +
                "\t\t\t\t<!----cnzz---->\n" +
                "\t\t\t\t<script src='http://s4.cnzz.com/z_stat.php?id=1256950379&web_id=1256950379' language='JavaScript'></script>\n" +
                "\t\t\t\t<!----cnzz---->\n" +
                "\t\t\t\t<!---googleÍ³¼Æ---->\n" +
                "\t\t\t\t<script>\n" +
                "\t\t\t\t  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){\n" +
                "\t\t\t\t  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),\n" +
                "\t\t\t\t  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)\n" +
                "\t\t\t\t  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');\n" +
                "\n" +
                "\t\t\t\t  ga('create', 'UA-48372285-56', 'auto');\n" +
                "\t\t\t\t  ga('send', 'pageview');\n" +
                "\n" +
                "\t\t\t\t</script>\n" +
                "\t\t\t\t<!---googleÍ³¼Æ---->\n" +
                "\t\t\t</div>\n" +
                "\t\t\t</body>\n" +
                "\t\t\t</html>";

        String video = "<embed src=\"http://player.youku.com/player.php/sid/XODU2MjYyNjY0/v.swf\" allowFullScreen=\"true\" quality=\"high\" width=\"480\" height=\"400\" align=\"middle\" allowScriptAccess=\"always\" type=\"application/x-shockwave-flash\"></embed>";
        webView.loadData(_html,"","utf-8");
//        webView.loadData(video,"","utf-8");
//        webView.loadUrl("http://v.youku.com/v_show/id_XODE1MDMzNTUy.html");
    }
}
