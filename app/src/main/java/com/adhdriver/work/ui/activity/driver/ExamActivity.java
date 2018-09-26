package com.adhdriver.work.ui.activity.driver;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.callback.ExamDialogClickCallBack;
import com.adhdriver.work.constant.ConstIntent;
import com.adhdriver.work.constant.ConstLocalData;
import com.adhdriver.work.constant.ConstSign;
import com.adhdriver.work.constant.ConstSp;
import com.adhdriver.work.constant.ConstTag;
import com.adhdriver.work.entity.driver.study.Answer;
import com.adhdriver.work.entity.driver.study.AnswerPackage;
import com.adhdriver.work.entity.driver.study.CorrectAnswer;
import com.adhdriver.work.entity.driver.study.DriverAnswer;
import com.adhdriver.work.entity.driver.study.ExamResult;
import com.adhdriver.work.entity.driver.study.Examine;
import com.adhdriver.work.http.HttpConst;
import com.adhdriver.work.presenter.driver.PresenterDriverExam;
import com.adhdriver.work.ui.activity.BaseActivity;
import com.adhdriver.work.ui.iview.driver.IExamView;
import com.adhdriver.work.utils.IntegerUtil;
import com.adhdriver.work.utils.SpUtil;
import com.adhdriver.work.utils.ToastUtil;
import com.adhdriver.work.ui.widget.dialog.ExamResultDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExamActivity extends BaseActivity<IExamView, PresenterDriverExam> implements
        IExamView,
        View.OnClickListener,
        CompoundButton.OnCheckedChangeListener,
        ExamDialogClickCallBack {

    private PresenterDriverExam presenterDriverExam;


    /**
     * titile
     */
    private ImageView iv_common_back;
    private TextView tv_common_title;
    private ImageView iv_common_search;


    /**
     * 中间
     */
    private TextView tv_exam_title;
    private TextView tv_exam_title_no;
    private RadioGroup rg_all_selector;
    private RadioButton rb_A;
    private RadioButton rb_B;
    private RadioButton rb_C;
    private RadioButton rb_D;


    /**
     * 底部
     */
    private LinearLayout ll_common_exam_finish;
    private TextView tv_previous;
    private TextView tv_next;

    /**
     * 数据相关
     *
     * @param savedInstanceState
     */
//    private int currentTag;
    private ExamResultDialog examResultDialog;

    private Examine currentExamine;  //当前考试题


    private int currentExamNo = 0;//当前第几个
    private int allTheQuestionNum = 0;  //全部题号
    private List<Examine> examineList;//当前考试列表

    private AnswerPackage currentAnswerPackage; //获取当前的答案包装信息
    private List<Answer> currentAnswerList;//当前答案信息 (每个题一共有多个答案)
    private List<AnswerPackage> answerPackageList;//获取答案列表
    private List<CorrectAnswer> correctAnswerList;//正确答案集合
    private Map<String, DriverAnswer> mapDriver = new HashMap<>();//司机的答案；


    private Map<Integer, Boolean> mapAccuracy;//正确率


    private String currentRegToken;


    /**
     * 初始化map的正确率
     */
    private void initMapAccuracy() {


        mapAccuracy = new HashMap<>();
        for (int i = 0; i < allTheQuestionNum; i++) {

            mapAccuracy.put(i, false);

        }


    }


    /**
     * 非空验证
     *
     * @param object
     * @return
     */
    private boolean checkObject(Object object) {

        boolean result = false;

        if (null != object) {

            result = true;

        }

        return result;


    }

    /**
     * 保存正确答案
     */
    private void setCorrectAnswerToList() {


        correctAnswerList = new ArrayList<>();

        if (checkObject(examineList)) {

            for (int i = 0; i < examineList.size(); i++) {

                Examine examine = examineList.get(i);

                CorrectAnswer correctAnswer = new CorrectAnswer();

                correctAnswer.setQuestionNo(examine.getTest_questions_no());

                correctAnswer.setAnswerId(examine.getDaturn_answer_id());

                correctAnswerList.add(correctAnswer);
            }

        }


    }


    /**
     * 根据当前题目数变更下一题
     */
    private void upDateBottomUi() {


        /**
         * 1.第一题
         * 2.最后一题
         * 3.中间题
         */


        /**
         * 第一题
         */
        if (currentExamNo == 0) {

            ll_common_exam_finish.setVisibility(View.GONE);
            tv_previous.setVisibility(View.GONE);
            tv_next.setVisibility(View.VISIBLE);

        } else if (currentExamNo == allTheQuestionNum - 1) {


            /**
             * 最后一题，仅仅显示 上一题和提交
             */

            ll_common_exam_finish.setVisibility(View.VISIBLE);
            tv_previous.setVisibility(View.VISIBLE);
            tv_next.setVisibility(View.GONE);

        } else {

            ll_common_exam_finish.setVisibility(View.GONE);
            tv_previous.setVisibility(View.VISIBLE);
            tv_next.setVisibility(View.VISIBLE);

        }


    }

    /**
     * 将题目设置到界面
     */
    private void setExamListDataToUi() {

        if (null != examineList && examineList.size() > 0) {

            currentExamine = examineList.get(currentExamNo);

            if (checkObject(currentExamine)) {


                tv_exam_title_no.setText(currentExamNo + 1 + ConstSign.POINT);


                tv_exam_title.setText(currentExamine.getDaturn_topic());

                upDateBottomUi();

            }
        }
    }


    /**
     * 重新排列答案的顺序
     */
    private void reSortTheAnswers() {


        if (null != answerPackageList && answerPackageList.size() > 0) {


            for (int i = 0; i < answerPackageList.size(); i++) {


                AnswerPackage answerPackage = answerPackageList.get(i);

                if (checkObject(answerPackage)) {


                    List<Answer> answers = answerPackage.getAnswers();

                    if (null != answers && answers.size() > 0) {


                        Collections.shuffle(answers);

                        answerPackage.setAnswers(answers);

                    }

                }

            }


        }


    }


    private void setAnswerDataToUi() {


        if (null != answerPackageList && answerPackageList.size() > 0) {


            for (int i = 0; i < answerPackageList.size(); i++) {

                AnswerPackage answerPackage = answerPackageList.get(i);

                if (checkObject(currentExamine)) {

                    String questionNo = currentExamine.getTest_questions_no();


                    if (questionNo.equals(answerPackage.getQuestions_no())) {


                        currentAnswerPackage = answerPackage;


                        currentAnswerList = currentAnswerPackage.getAnswers();

                        if (checkObject(currentAnswerList)) {

                            setAnswerUiBySize(currentAnswerList.size());

                            break;

                        }


                    }

                }

            }
        }

    }

    /**
     * 根据答案的返回列表 来动态显示答案个数 以及答案描述
     *
     * @param size
     */
    private void setAnswerUiBySize(int size) {


        if (size == 2) {

            rb_A.setVisibility(View.VISIBLE);
            rb_B.setVisibility(View.VISIBLE);
            rb_C.setVisibility(View.GONE);
            rb_D.setVisibility(View.GONE);


            Answer answerA = currentAnswerList.get(0);
            Answer answerB = currentAnswerList.get(1);

            if (null != answerA && null != answerB) {
                rb_A.setText(answerA.getAnswer_content());
                rb_B.setText(answerB.getAnswer_content());
            }


        } else if (size == 3) {


            rb_A.setVisibility(View.VISIBLE);
            rb_B.setVisibility(View.VISIBLE);
            rb_C.setVisibility(View.VISIBLE);
            rb_D.setVisibility(View.GONE);

            Answer answerA = currentAnswerList.get(0);
            Answer answerB = currentAnswerList.get(1);
            Answer answerC = currentAnswerList.get(2);
            if (null != answerA && null != answerB && null != answerC) {
                rb_A.setText(answerA.getAnswer_content());
                rb_B.setText(answerB.getAnswer_content());
                rb_C.setText(answerC.getAnswer_content());
            }


        } else {

            rb_A.setVisibility(View.VISIBLE);
            rb_B.setVisibility(View.VISIBLE);
            rb_C.setVisibility(View.VISIBLE);
            rb_D.setVisibility(View.VISIBLE);


            Answer answerA = currentAnswerList.get(0);
            Answer answerB = currentAnswerList.get(1);
            Answer answerC = currentAnswerList.get(2);
            Answer answerD = currentAnswerList.get(3);
            if (null != answerA && null != answerB && null != answerC && null != answerD) {
                rb_A.setText(answerA.getAnswer_content());
                rb_B.setText(answerB.getAnswer_content());
                rb_C.setText(answerC.getAnswer_content());
                rb_D.setText(answerD.getAnswer_content());
            }


        }


    }


    /**
     * 点击下一步 或者上一步时候，设置考试题与答案
     */
    private void setExamAndAnswerDataToUi() {


        setExamListDataToUi();

        setAnswerDataToUi();
    }


    /**
     * 设置司机已经选择的选项到界面上
     */
    private void setSelectedToUi() {

        if (null != mapDriver && mapDriver.size() > 0) {

            for (String key : mapDriver.keySet()) {


                if (key.equals(currentExamine.getTest_questions_no())) {


                    DriverAnswer driverAnswer = mapDriver.get(key);


                    if (checkObject(driverAnswer)) {


                        int rbId = driverAnswer.getRb_id();


                        setRbCheckBySelectId(rbId);
                        break;
                    }


                } else {

                    continue;
                }

            }

        }


    }


    /**
     * 用来进行点击前一步或者后一步时，对之前的选项进行按钮设置
     *
     * @param rbId
     */
    private void setRbCheckBySelectId(int rbId) {

        switch (rbId) {

            case R.id.rb_A:

                rb_A.setChecked(true);
                break;

            case R.id.rb_B:
                rb_B.setChecked(true);
                break;

            case R.id.rb_C:
                rb_C.setChecked(true);
                break;

            case R.id.rb_D:
                rb_D.setChecked(true);
                break;

        }

    }


    /**
     * 设置选择的答案到当前界面
     */
    private void setCurrentAnswerToDriverSelect(int index, int rbId) {


        Answer answer = currentAnswerList.get(index);

        if (checkObject(answer)) {


//            answer.getAnswer_id()

            mapDriver.put(currentExamine.getTest_questions_no(), new DriverAnswer(index, rbId, answer.getAnswer_id()));

        }


    }


    /**
     * 取出所有先等到正确率
     *
     * @return
     */
    private void getAccuracy() {


        if (null != correctAnswerList && correctAnswerList.size() > 0) {

            for (int i = 0; i < correctAnswerList.size(); i++) {

                CorrectAnswer correctAnswer = correctAnswerList.get(i);


                String questionNo = correctAnswer.getQuestionNo();
                int correctAnswerId = correctAnswer.getAnswerId();
                /**
                 * 迭代mapkey
                 */


                for (String key : mapDriver.keySet()) {

                    if (key.equals(questionNo)) {


                        DriverAnswer driverAnswer = mapDriver.get(key);


                        if (checkObject(driverAnswer)) {


                            if (correctAnswerId == driverAnswer.getSelfAnswerId()) {

                                /**
                                 * 答对了
                                 */
                                mapAccuracy.put(i, true);


                            } else {

                                /**
                                 * 答错了
                                 */
                                mapAccuracy.put(i, false);

                            }

                        }

                    } else {

                        continue;

                    }

                }


            }

        }

        for (int j = 0; j < mapAccuracy.size(); j++) {

            Log.i("driver_score", mapAccuracy.get(j) + " ");

        }

    }


    private int getCorrectCount() {

        int correct = 0;

        if (null != mapAccuracy && mapAccuracy.size() > 0) {


            for (int i = 0; i < mapAccuracy.size(); i++) {

                if (mapAccuracy.get(i)) {

                    correct++;

                }

            }


        }

        return correct;

    }


    private void setExamResultDataToUi(ExamResult examResult) {


        if (checkObject(examResult)) {

            int markBack = examResult.getMark();


            if (markBack >= ConstLocalData.EXAM_PASS_SCORE) {






                /**
                 * 这里应该提示dialog
                 */
                initDialogExamPass("您已成功通过考试，请静候佳音", "我知道了");


            } else {
                /**
                 * 这里应该提示dialog
                 */
                initDialogExamFail("您的考试未通过，请重新考试", "我知道了");


            }
        }
    }


    /**
     * 考试通过
     */
    private void initDialogExamPass(String str, String bottomText) {

        if (examResultDialog == null) {

            examResultDialog = new ExamResultDialog(this, ConstTag.DialogTag.START_TO_SHOW_MAP_AFTER_EXAM, str, bottomText);
            examResultDialog.setTag(ConstTag.DialogTag.START_TO_SHOW_MAP_AFTER_EXAM);
            examResultDialog.setMessageStr(str);
            examResultDialog.setBottomText(bottomText);
            examResultDialog.setDialogClickCallBack(this);
            examResultDialog.setCancelable(false);
            examResultDialog.show();

        } else {
            examResultDialog.setTag(ConstTag.DialogTag.START_TO_SHOW_MAP_AFTER_EXAM);
            examResultDialog.setMessageStr(str);
            examResultDialog.setBottomText(bottomText);
            examResultDialog.setDialogClickCallBack(this);
            examResultDialog.show();

        }

    }


    /**
     * 考试未通过
     *
     * @param str
     */

    private void initDialogExamFail(String str, String bottomText) {
        if (examResultDialog == null) {

            examResultDialog = new ExamResultDialog(this, ConstTag.DialogTag.START_TO_EXAM_FAIL, str, bottomText);
            examResultDialog.setTag(ConstTag.DialogTag.START_TO_EXAM_FAIL);
            examResultDialog.setDialogClickCallBack(this);
            examResultDialog.setMessageStr(str);
            examResultDialog.setCancelable(false);
            examResultDialog.show();

        } else {
            examResultDialog.setDialogClickCallBack(this);
            examResultDialog.setTag(ConstTag.DialogTag.START_TO_EXAM_FAIL);
            examResultDialog.setMessageStr(str);
            examResultDialog.setBottomText(bottomText);
            examResultDialog.show();

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam2);

        presenterDriverExam.doGetExamQuestions(HttpConst.URL.EXAMINE_TEST_QESTIONS, currentRegToken, ConstLocalData.EXAM_COUNT, 1);
    }

    @Override
    protected PresenterDriverExam creatPresenter() {
        if (null == presenterDriverExam) {
            presenterDriverExam = new PresenterDriverExam(this);
        }
        return presenterDriverExam;
    }

    @Override
    protected void initViews() {

        /**
         * titile
         * @param
         */
        iv_common_back = findADHViewById(R.id.iv_common_back);
        tv_common_title = findADHViewById(R.id.tv_common_title);
        iv_common_search = findADHViewById(R.id.iv_common_search);
        iv_common_search.setVisibility(View.GONE);
        tv_common_title.setText(this.getString(R.string.online_exam));


        /**
         * 中间
         */
        tv_exam_title_no = findADHViewById(R.id.tv_exam_title_no);
        tv_exam_title = findADHViewById(R.id.tv_exam_title);
        rg_all_selector = findADHViewById(R.id.rg_all_selector);
        rb_A = findADHViewById(R.id.rb_A);
        rb_B = findADHViewById(R.id.rb_B);

        rb_C = findADHViewById(R.id.rb_C);
        rb_D = findADHViewById(R.id.rb_D);


        /**
         * 底部
         */
        ll_common_exam_finish = findADHViewById(R.id.ll_common_exam_finish);

        tv_previous = findADHViewById(R.id.tv_previous);
        tv_next = findADHViewById(R.id.tv_next);

    }

    @Override
    protected void initListener() {

        iv_common_back.setOnClickListener(this);


        /**
         * 中间
         */
        rb_A.setOnCheckedChangeListener(this);
        rb_B.setOnCheckedChangeListener(this);

        rb_C.setOnCheckedChangeListener(this);
        rb_D.setOnCheckedChangeListener(this);


        /**
         * 底部
         */
        ll_common_exam_finish.setOnClickListener(this);

        tv_previous.setOnClickListener(this);
        tv_next.setOnClickListener(this);

    }

    @Override
    protected void processIntent() {
        Intent intent = this.getIntent();

        if (null != intent) {


            Bundle bundle = intent.getExtras();

            if (null != bundle) {


                currentRegToken = bundle.getString(ConstIntent.BundleKEY.DELIVERY_ACCESS_TOKEN,ConstIntent.BundleValue.DEFAULT_STRING);

            }
        }
    }

    @Override
    public void showLoadingDialog() {
        showLoadDialog();
    }

    @Override
    public void dismissLoadingDialog() {
        dismissLoadDialog();
    }

    @Override
    public void onDataBackFail(int code, String errorMsg) {
        ToastUtil.showMsg(getApplicationContext(), errorMsg);

    }

    @Override
    public void onDataBackSuccessForExamineQuestions(List<Examine> examineList) {

        this.examineList = examineList;

        allTheQuestionNum = examineList.size();  //将所有的题目数量设置到界面上


        Collections.shuffle(examineList);  //打乱题目顺序


        Log.i("exam", "当前currentExamNo：" + currentExamNo);

        Log.i("exam", "总共题目数量：" + allTheQuestionNum);


        initMapAccuracy();

        setCorrectAnswerToList();

        setExamListDataToUi();


        if (!checkObject(currentExamine)) {

            return;

        }

        int paper_id = currentExamine.getPaper_id();


        presenterDriverExam.doGetAnswers(HttpConst.URL.QUESTION_ANSWERS + HttpConst.SEPARATOR + paper_id, currentRegToken);

    }

    @Override
    public void onDataBackSuccessForAnswerPackages(List<AnswerPackage> answerPackageList) {

        this.answerPackageList = answerPackageList;

        reSortTheAnswers();

        setAnswerDataToUi();
    }

    @Override
    public void onDataBackSuccessForSendExamToGetResult(ExamResult examResult) {


        setExamResultDataToUi(examResult);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_common_back:
                dofinishItself();
                break;

            case R.id.ll_common_exam_finish:


                getAccuracy();

                int correctNum = getCorrectCount();
                int errorNum = examineList.size() - correctNum;
                int mark = IntegerUtil.getExamMak(correctNum, examineList.size());
                int paper_id = currentExamine.getPaper_id();


                String driverId = SpUtil.getInstance().getStringValue(ConstSp.SP_KEY_DRIVER_ID, ConstSp.SP_VALUE.DEFAULT_STRING);


                presenterDriverExam.doGetExamMark(HttpConst.URL.CREAT_EXAMINE_MARK,
                        currentRegToken,
                        driverId,
                        mark,
                        paper_id,
                        correctNum,
                        errorNum);


                break;

            case R.id.tv_previous:


                currentExamNo--;
                Log.i("exam", "点击上一题：当前currentExamNo：" + currentExamNo);

                rg_all_selector.clearCheck();

                setExamAndAnswerDataToUi();


                setSelectedToUi();


                break;

            case R.id.tv_next:


                currentExamNo++;

                Log.i("exam", "点击下一题：当前currentExamNo：" + currentExamNo);


                rg_all_selector.clearCheck();


                setExamAndAnswerDataToUi();

                setSelectedToUi();


                break;

        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            switch (buttonView.getId()) {

                case R.id.rb_A:


                    setCurrentAnswerToDriverSelect(0, buttonView.getId());

                    break;

                case R.id.rb_B:


                    setCurrentAnswerToDriverSelect(1, buttonView.getId());

                    break;


                case R.id.rb_C:


                    setCurrentAnswerToDriverSelect(2, buttonView.getId());
                    break;


                case R.id.rb_D:

                    setCurrentAnswerToDriverSelect(3, buttonView.getId());
                    break;

            }
        }
    }

    @Override
    public void dialogClick(int tag) {
        switch (tag) {

            case ConstTag.DialogTag.START_TO_SHOW_MAP_AFTER_EXAM: //考试通过

                openActivityAndFinishItself(MainDriverActivity.class, null);

                break;


            case ConstTag.DialogTag.START_TO_EXAM_FAIL: //考试没过

                dofinishItself();
                break;

        }
    }


}
