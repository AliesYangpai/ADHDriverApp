package com.adhdriver.work.presenter.driver;


import com.adhdriver.work.constant.ConstError;
import com.adhdriver.work.constant.ConstParse;
import com.adhdriver.work.constant.ConstSp;
import com.adhdriver.work.constant.ConstTag;
import com.adhdriver.work.entity.ErrorEntity;
import com.adhdriver.work.entity.driver.token.TokenInfo;
import com.adhdriver.work.entity.driver.thirdauth.AuthParam;
import com.adhdriver.work.entity.driver.thirdauth.AuthState;
import com.adhdriver.work.entity.driver.thirdauth.QQAuthBack;
import com.adhdriver.work.entity.driver.thirdauth.ValidAuthInApp;
import com.adhdriver.work.http.ParseSerilizable;
import com.adhdriver.work.listener.OnDataBackListener;
import com.adhdriver.work.logic.LogicThirdAuth;
import com.adhdriver.work.method.IThirdAuthAbout;
import com.adhdriver.work.method.impl.IThirdAuthAboutImpl;
import com.adhdriver.work.presenter.BasePresenter;
import com.adhdriver.work.ui.iview.driver.IThirdAuthView;
import com.adhdriver.work.utils.SpUtil;
import com.adhdriver.work.verification.VertifyNotNull;
import com.adhdriver.work.verification.VertifyRule;

import java.util.List;

/**
 * Created by Administrator on 2017/11/29.
 * 类描述
 * 版本
 */

public class PresenterDriverThirdAuth extends BasePresenter<IThirdAuthView> {

    private IThirdAuthView iThirdAuthView;
    private IThirdAuthAbout iThirdAuthAbout;
    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;
    private VertifyRule vertifyRule;

    private LogicThirdAuth logicThirdAuth;


    /**
     * 数据相关
     */
    private List<AuthState> authStates;



    public PresenterDriverThirdAuth(IThirdAuthView iThirdAuthView) {
        this.iThirdAuthView = iThirdAuthView;
        this.iThirdAuthAbout = new IThirdAuthAboutImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
        this.vertifyRule = new VertifyRule();
        this.logicThirdAuth = new LogicThirdAuth();
    }








    /**
     * 从服务器返回获取token
     *
     * @param list
     * @return
     */
    private String getTokenFromList(List<TokenInfo> list) {


        String token = "";

        if (vertifyNotNull.isNotNullListSize(list)) {


            if (isAllVacant(list)) {

                TokenInfo tokenInfo = list.get(0);

                token = tokenInfo.getAccess_token();


            } else {


                token = getAvalibleTokenWhenNotAllVacant(list);

            }

        }

        return token;
    }


    /**
     * 判断是否都是空闲车辆
     *
     * @param list
     * @return
     */
    private boolean isAllVacant(List<TokenInfo> list) {

        boolean result = false;


        int countTemp = 0;
        if (null != list && list.size() > 0) {


            for (TokenInfo tokenInfo : list) {

                int count = tokenInfo.getProcessing_order_counts();
                countTemp += count;
            }

        }


        if (countTemp == 0) {

            result = true;
        }


        return result;


    }

    /**
     * 当所有车辆都不是空闲车辆
     *
     * @param list
     * @return
     */
    private String getAvalibleTokenWhenNotAllVacant(List<TokenInfo> list) {

        String token = "";

        if (null != list && list.size() > 0) {

            for (TokenInfo tokenInfo : list) {

                int count = tokenInfo.getProcessing_order_counts();

                if (count > 0) {

                    token = tokenInfo.getAccess_token();

                    break;
                }
            }
        }

        return token;

    }









    /**
     * 获取授权列表
     *
     * @param url
     */
    public void doGetBinds(String url) {


        iThirdAuthAbout.doGetBinds(url, new OnDataBackListener() {
            @Override
            public void onStart() {
                iThirdAuthView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {

                List<AuthState> list = parseSerilizable.getParseToNoItemsList(data, AuthState.class);
                authStates = list;


                if (vertifyNotNull.isNotNullListSize(list)) {

                   if(logicThirdAuth.isOnlyQQBind(list))  {

                       iThirdAuthView.onDataBackSuccessForQQBind();

                   }else if(logicThirdAuth.isOnlyWxBind(list)) {

                       iThirdAuthView.onDataBackSuccessForWxBind();

                   }else {

                       iThirdAuthView.onDataBackSuccessForAllBind();

                   }

                }else {

                    iThirdAuthView.onDataBackSuccessForNoBind();
                }
            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {

                    iThirdAuthView.onDataBackFail(code, errorEntity.getError_message());
                } else {

                    iThirdAuthView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);

                }

            }

            @Override
            public void onFinish() {
                iThirdAuthView.dismissLoadingDialog();
            }
        });

    }








    /**
     * 验证QQ是否授权
     *
     * @param url
     */
    public void doValidAuthForQQ(String url,
                                 QQAuthBack qqAuthBack) {


        if(vertifyNotNull.isNotNullObj(qqAuthBack)) {

            iThirdAuthAbout.doValidAuthForQQ(url, qqAuthBack.getOpenid(), ConstTag.AuthTag.QQ, qqAuthBack.getOpenid(), qqAuthBack.getOpenid(), qqAuthBack.getAccess_token(), new OnDataBackListener() {
                @Override
                public void onStart() {

                }

                @Override
                public void onSuccess(String data) {

                    ValidAuthInApp validAuthInApp = parseSerilizable.getParseToObj(data, ValidAuthInApp.class);

                    if (vertifyNotNull.isNotNullObj(validAuthInApp)) {

                        iThirdAuthView.onDataBackSuccessForValidAuthQQ(validAuthInApp);


                    }


                }

                @Override
                public void onFail(int code, String data) {


                    ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                    if (vertifyNotNull.isNotNullObj(errorEntity)) {

                        iThirdAuthView.onDataBackFail(code, errorEntity.getError_message());
                    } else {

                        iThirdAuthView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);

                    }
                }

                @Override
                public void onFinish() {

                }
            });


        }









    }



    /**
     * 验证wx是否授权
     *
     * @param url
     * @param code
     * @param auth_type
     */
    public void doValidAuthForWx(String url,
                                 String code,
                                 String auth_type) {

        iThirdAuthAbout.doValidAuthForWx(url, code, auth_type, new OnDataBackListener() {
            @Override
            public void onStart() {
                iThirdAuthView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {


                ValidAuthInApp validAuthInApp = parseSerilizable.getParseToObj(data, ValidAuthInApp.class);

                if (vertifyNotNull.isNotNullObj(validAuthInApp)) {

                    iThirdAuthView.onDataBackSuccessForValidAuthWx(validAuthInApp);
                }


            }

            @Override
            public void onFail(int code, String data) {


                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {

                    iThirdAuthView.onDataBackFail(code, errorEntity.getError_message());
                } else {

                    iThirdAuthView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);

                }
            }

            @Override
            public void onFinish() {
                iThirdAuthView.dismissLoadingDialog();
            }
        });

    }

    /**
     * 执行微信绑定
     *
     * @param url
     * @param validAuthInApp
     */
    public void doBindForWx(String url,
                            ValidAuthInApp validAuthInApp) {

        if(vertifyNotNull.isNotNullObj(validAuthInApp)) {

           AuthParam authParam = validAuthInApp.getParameters();

            if(vertifyNotNull.isNotNullObj(authParam)) {


                iThirdAuthAbout.doBindForWx(url, validAuthInApp.getAuth_type(), authParam, new OnDataBackListener() {
                    @Override
                    public void onStart() {

                        iThirdAuthView.showLoadingDialog();

                    }

                    @Override
                    public void onSuccess(String data) {


                        String targetToken = "";

                        if (vertifyRule.isJsonObj(data)) {

                            String token = parseSerilizable.getParseString(data, ConstParse.Key.TOKEN);
                            TokenInfo tokenInfo = parseSerilizable.getParseToObj(token, TokenInfo.class);

                            if (vertifyNotNull.isNotNullObj(tokenInfo)) {


                                targetToken = tokenInfo.getAccess_token();
                            } else {

                                iThirdAuthView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                            }
                        } else {

                            String token = parseSerilizable.getParseString(data, ConstParse.Key.TOKEN);
                            List<TokenInfo> tokenInfos =parseSerilizable.getParseToList(token, TokenInfo.class);
                            if(vertifyNotNull.isNotNullListSize(tokenInfos)) {
                                targetToken = getTokenFromList(tokenInfos);
                            }else {
                                iThirdAuthView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                            }
                        }
                        if(vertifyNotNull.isNotNullString(targetToken)) {
                            SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_TOKEN, targetToken);
                            iThirdAuthView.onDataBackSuccessForBindAuthWx(data);
                        }else {
                            iThirdAuthView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE_NO_TOKEN,ConstError.PARSE_ERROR_NO_THIRD_TOKEN);
                        }

                    }

                    @Override
                    public void onFail(int code, String data) {

                        ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                        if (vertifyNotNull.isNotNullObj(errorEntity)) {
                            iThirdAuthView.onDataBackFail(code, errorEntity.getError_message());
                        } else {

                            iThirdAuthView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);

                        }
                    }

                    @Override
                    public void onFinish() {


                        iThirdAuthView.dismissLoadingDialog();
                    }
                });

            }

        }



    }


    /**
     * 执行QQ绑定
     *
     * @param url
     * @param  validAuthInApp

     */
    public void doBindForQQ(String url,
                            ValidAuthInApp validAuthInApp) {


        if(vertifyNotNull.isNotNullObj(validAuthInApp)) {

           AuthParam authParam = validAuthInApp.getParameters();

            if(vertifyNotNull.isNotNullObj(authParam)) {



                iThirdAuthAbout.doBindForQQ(url, validAuthInApp.getAuth_type(), authParam, new OnDataBackListener() {
                    @Override
                    public void onStart() {

                        iThirdAuthView.showLoadingDialog();

                    }

                    @Override
                    public void onSuccess(String data) {

                        String targetToken = "";

                        if (vertifyRule.isJsonObj(data)) {
                            String token = parseSerilizable.getParseString(data, ConstParse.Key.TOKEN);

                            TokenInfo tokenInfo = parseSerilizable.getParseToObj(token, TokenInfo.class);

                            if (vertifyNotNull.isNotNullObj(tokenInfo)) {

                                targetToken = tokenInfo.getAccess_token();

                            } else {

                                iThirdAuthView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);

                            }
                        } else {

                            String token = parseSerilizable.getParseString(data, ConstParse.Key.TOKEN);

                            List<TokenInfo> tokenInfos =parseSerilizable.getParseToList(token, TokenInfo.class);

                            if(vertifyNotNull.isNotNullListSize(tokenInfos)) {

                                targetToken = getTokenFromList(tokenInfos);

                            }else {

                                iThirdAuthView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                            }
                        }
                        if(vertifyNotNull.isNotNullString(targetToken)) {

                            SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_TOKEN, targetToken);

                            iThirdAuthView.onDataBackSuccessForBindAuthQQ(data);
                        }else {

                            iThirdAuthView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE_NO_TOKEN,ConstError.PARSE_ERROR_NO_THIRD_TOKEN);
                        }


                    }
                    @Override
                    public void onFail(int code, String data) {
                        ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);

                        if (vertifyNotNull.isNotNullObj(errorEntity)) {

                            iThirdAuthView.onDataBackFail(code, errorEntity.getError_message());
                        } else {

                            iThirdAuthView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);

                        }
                    }

                    @Override
                    public void onFinish() {


                        iThirdAuthView.dismissLoadingDialog();
                    }
                });

            }

        }



    }


    /**
     * 前往进行qq授权
     */
    public void doGetQQAuthorization() {

        if(vertifyNotNull.isNotNullListSize(authStates)) {

            if(!logicThirdAuth.isQQHasBind(authStates)) {

                iThirdAuthView.doGetQQAuthorization();
            }

        }else {

            iThirdAuthView.doGetQQAuthorization();

        }

    }


    /**
     * 进行微信绑定授权
     */
    public void doGetWxAuthorization() {



        if(vertifyNotNull.isNotNullListSize(authStates)) {

            if(!logicThirdAuth.isWxHasBind(authStates)) {

                iThirdAuthView.doGetWxAuthorization();
            }

        }else {

            iThirdAuthView.doGetWxAuthorization();

        }
    }


}
