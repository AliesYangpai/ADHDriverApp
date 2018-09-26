package com.adhdriver.work.ui.activity.driver;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstLocalData;
import com.adhdriver.work.presenter.common.PresenterUserAgreement;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.iview.IUserAgreementView;
import com.adhdriver.work.ui.widget.webview.WebClientPicDescrib;

public class UserProtocolActivity extends BaseActivity<IUserAgreementView, PresenterUserAgreement>
        implements
        IUserAgreementView,
        View.OnClickListener{



    /**
     * titile
     */
    private ImageView iv_common_back;
    private TextView tv_common_title;
    private ImageView iv_common_search;

    /**
     * webview
     * @param savedInstanceState
     */

    private WebView wv_picAndDescription;




    private PresenterUserAgreement presenterUserAgreement;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_protocol);


        presenterUserAgreement.doLoadAgreementUrl(ConstLocalData.AGRREMENT_URL);
    }

    @Override
    protected PresenterUserAgreement creatPresenter() {
        if(null == presenterUserAgreement) {

            presenterUserAgreement = new PresenterUserAgreement(this);
        }
        return presenterUserAgreement;
    }

    @Override
    protected void initViews() {

        /**
         * title
         */
        iv_common_back = findADHViewById(R.id.iv_common_back);
        tv_common_title = findADHViewById(R.id.tv_common_title);
        iv_common_search = findADHViewById(R.id.iv_common_search);
        iv_common_search.setVisibility(View.GONE);

        tv_common_title.setText(this.getString(R.string.user_protocol));


        wv_picAndDescription = (WebView) findViewById(R.id.wv_picAndDescription); //图文详情
        wv_picAndDescription.getSettings().setJavaScriptEnabled(true);
        wv_picAndDescription.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        wv_picAndDescription.getSettings().setDomStorageEnabled(true);
        wv_picAndDescription.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wv_picAndDescription.setBackgroundColor(0x00000000);

        wv_picAndDescription.getSettings().setLoadWithOverviewMode(true);

        wv_picAndDescription.setWebViewClient(new WebClientPicDescrib(this));


    }

    @Override
    protected void initListener() {
        iv_common_back.setOnClickListener(this);
    }

    @Override
    protected void processIntent() {

    }

    @Override
    public void doLoadUserAgreementUrl(String url) {


        wv_picAndDescription.loadUrl(url);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.iv_common_back:

                dofinishItself();

                break;
        }
    }
}
