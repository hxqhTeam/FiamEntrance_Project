package cn.hqxh.fiam.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hqxh.fiamproperty.constant.GlobalConfig;
import com.hqxh.fiamproperty.model.R_PERSONS;
import com.hqxh.fiamproperty.ui.activity.HomeActivity;
import com.hqxh.fiamproperty.unit.AccountUtils;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    Toolbar mToolbar;
    /**
     * 标题
     **/
    TextView titleTextView;

    /**
     * FIAM
     **/
    private TextView FiamText;
    /**
     * 工作日志
     **/
    private TextView gzrzText;
    /**
     * 任务清版
     **/
    private TextView rwqbText;

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = getIntent().getExtras().getString("username");
        Log.e("USERNAME", "username=" + username);
        mToolbar = (Toolbar) findViewById(com.hqxh.fiamproperty.R.id.toolbar);
        titleTextView = (TextView) findViewById(com.hqxh.fiamproperty.R.id.title_text);
        initToolbar();

        FiamText = (TextView) findViewById(R.id.fiam_text_id);
        gzrzText = (TextView) findViewById(R.id.zzrz_text_id);
        rwqbText = (TextView) findViewById(R.id.rwqb_text_id);
        FiamText.setOnClickListener(onClickListener);
    }


    private void initToolbar() {
        if (mToolbar != null) {
            mToolbar.setTitle("");
            titleTextView.setText("技术中心");
            setSupportActionBar(mToolbar);
        }
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = null;
            int i = view.getId();
            if (i == R.id.fiam_text_id) {
//                intent = new Intent(MainActivity.this, HomeActivity.class);
//                startActivityForResult(intent, 0);

                Login();

            }
        }
    };


    private void Login() {

        String imei = ((TelephonyManager) getSystemService(TELEPHONY_SERVICE))
                .getDeviceId();

        Log.e("TAG", "HTTP_URL_LOGIN=" + GlobalConfig.HTTP_URL_LOGIN);

        Rx2AndroidNetworking.post(GlobalConfig.HTTP_URL_LOGIN)
                .addBodyParameter("username", username)
                .addBodyParameter("imei", imei)
                .build()
                .getObjectObservable(R_PERSONS.class) // 发起获取数据列表的请求，并解析到R_Person.class
                .subscribeOn(Schedulers.io())        // 在io线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread()) // 在主线程处理获取数据列表的请求结果
                .doOnNext(new Consumer<R_PERSONS>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull R_PERSONS r_PERSON) throws Exception {
                        if (r_PERSON.getErrcode().equals(GlobalConfig.LOGINSUCCESS)) {//登录成功

                        } else if (r_PERSON.getErrcode().equals(GlobalConfig.CHANGEIMEI)) {//登录成功,检测到用户更换手机登录

                        } else if (r_PERSON.getErrcode().equals(GlobalConfig.USERNAMEERROR)) {//用户名密码错误
                            return;
                        } else {
                            return;
                        }
                    }
                })

                .map(new Function<R_PERSONS, String>() {
                    @Override
                    public String apply(@io.reactivex.annotations.NonNull R_PERSONS r_PERSON) throws Exception {
                        return r_PERSON.getResult();
                    }

                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull String persion) throws Exception {
                        Log.e("TAG", "persion" + persion);
                        if (null != persion) {
                            JSONObject object = new JSONObject(persion);
                            R_PERSONS.PERSION persion1 = new R_PERSONS.PERSION();
                            persion1.setDEFSITE(object.getString("DEFSITE"));
                            persion1.setDISPLAYNAME(object.getString("DISPLAYNAME"));
                            persion1.setEMAILADDRESS(object.getString("EMAILADDRESS"));
                            persion1.setMYAPPS(object.getString("MYAPPS"));
                            persion1.setPERSONID(object.getString("PERSONID"));
                            AccountUtils.setLoginDetails(MainActivity.this, persion1);
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            startActivityForResult(intent, 0);
                        }


                    }


                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                        Log.e("TAG", "throwable=" + throwable.getMessage());
                        Toast.makeText(MainActivity.this, "失败", Toast.LENGTH_SHORT).show();
                    }
                });


    }


}
