package com.bwie.lifangyin1507c20170830;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import view.xlistview.XListView;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener {
    @ViewInject(R.id.xlv)
    XListView xlv;
    private String url = "http://v.juhe.cn/toutiao/index?key=22a108244dbb8d1f49967cd74a0c144d";
    private List<NewsBean> list = new ArrayList<>();
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        xlv.setPullLoadEnable(true);
        xlv.setXListViewListener(this);

        initData();
        ImageLoader();

    }

    private void ImageLoader() {
        DisplayImageOptions opt = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .build();
        ImageLoaderConfiguration con = new ImageLoaderConfiguration.Builder(this)
                .threadPoolSize(10)
                .threadPriority(5)
                .defaultDisplayImageOptions(opt)
                .build();
        ImageLoader.getInstance().init(con);
    }

    private void initData() {
        RequestParams rp = new RequestParams(url);
        rp.addBodyParameter("name", "name");
        rp.addHeader("hand", "hand");
        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject json = new JSONObject(result);
                    JSONObject ob = json.getJSONObject("result");
                    JSONArray arrayy = ob.getJSONArray("data");
                    if (arrayy != null && arrayy.length() > 0) {
                        for (int i = 0; i < arrayy.length(); i++) {
                            JSONObject jo = (JSONObject) arrayy.get(i);
                            NewsBean nb = new NewsBean();
                            nb.author_name = jo.getString("author_name");
                            nb.data = jo.getString("date");
                            nb.title = jo.getString("title");
                            nb.img = jo.getString("thumbnail_pic_s");

                            list.add(nb);
                        }
                        setData();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void setData() {

        if (adapter == null) {
            adapter = new Adapter(this, list);
            xlv.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRefresh() {
        adapter.notifyDataSetChanged();
        xlv.stopRefresh();
    }

    @Override
    public void onLoadMore() {
        xlv.stopLoadMore();
    }
}
