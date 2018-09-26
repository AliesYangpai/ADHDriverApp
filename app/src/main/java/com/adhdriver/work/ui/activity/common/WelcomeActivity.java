package com.adhdriver.work.ui.activity.common;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstSp;
import com.adhdriver.work.entity.driver.token.TokenInfo;
import com.adhdriver.work.presenter.common.PresenterWelcome;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.activity.driver.MainDriverActivity;
import com.adhdriver.work.ui.iview.IWelcomView;
import com.adhdriver.work.utils.SpUtil;
import com.adhdriver.work.utils.ToastUtil;
import com.adhdriver.work.ui.widget.bottomSpot.CirclePageIndicator;
import com.adhdriver.work.ui.widget.bottomSpot.WelViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * 欢迎页，viewpager
 */
public class WelcomeActivity extends BaseActivity<IWelcomView, PresenterWelcome> implements IWelcomView {


    private PresenterWelcome presenterWelcome;


    private ViewPager viewPager;
    private CirclePageIndicator circlePageIndicator;
    private WelViewPagerAdapter mAdapter;


    @Override
    protected PresenterWelcome creatPresenter() {
        if (null == presenterWelcome) {

            presenterWelcome = new PresenterWelcome(this);
        }
        return presenterWelcome;
    }

    @Override
    protected void initViews() {

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        circlePageIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        View pager01 = getLayoutInflater().inflate(R.layout.view_single_page, null);
        View pager02 = getLayoutInflater().inflate(R.layout.view_single_page, null);
        View pager03 = getLayoutInflater().inflate(R.layout.view_single_page_go, null);

        ImageView pageImage01 = (ImageView) pager01.findViewById(R.id.wizard_image);
        ImageView pageImage02 = (ImageView) pager02.findViewById(R.id.wizard_image);
        RelativeLayout rl_page = (RelativeLayout) pager03.findViewById(R.id.rl_page);

        TextView tv_go = (TextView) pager03.findViewById(R.id.tv_go);

        tv_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                presenterWelcome.doGoToMain();

            }
        });


        try {

            pageImage01.setBackgroundResource(R.drawable.welcome_page1);
            pageImage02.setBackgroundResource(R.drawable.welcome_page2);
            rl_page.setBackgroundResource(R.drawable.welcome_page3);

        } catch (Exception e) {

        }

        List<View> views = new ArrayList<View>();

        views.add(pager01);
        views.add(pager02);
        views.add(pager03);


        mAdapter = new WelViewPagerAdapter(views);

        viewPager.setAdapter(mAdapter);
        circlePageIndicator.setViewPager(viewPager, 0);
        circlePageIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

    }

    @Override
    protected void initListener() {


    }

    @Override
    protected void processIntent() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

    }


    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public void doGoToMain() {



//        SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_TOKEN, TestContent.TEST_TOKEN);


        SpUtil.getInstance().saveBooleanTosp(ConstSp.SP_KEY_LOAD_OR_NOT, true);

        openActivityAndFinishItself(MainDriverActivity.class,null);
    }

    @Override
    public void onDataBackFail(int code, String errorMsg) {
        ToastUtil.showMsg(getApplicationContext(),errorMsg);
    }

    @Override
    public void onDataBackSuccessForVisitorLogon(TokenInfo tokenInfo) {

        SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_TOKEN,tokenInfo.getAccess_token()); //这个是 visitor的token
        SpUtil.getInstance().saveBooleanTosp(ConstSp.SP_KEY_LOAD_OR_NOT, true);
        SpUtil.getInstance().saveBooleanTosp(ConstSp.SP_KEY_VISITOR,true);
        presenterWelcome.doGoToMain();
    }
}
