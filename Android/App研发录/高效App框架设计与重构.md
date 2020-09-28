# 重构，夜未眠

## 为Activity定义新的生命周期

重点：`单一职责`->一个类或者方法，只做一件事情

### 重构一：

+ 原来的代码结构：

```java

public class LoginActivity extends Activity implements View.onClickListener{
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
        String strEmail = bundle.getString(AppConstants.Email);

        etEmail = (EditText) findViewById(R.id.email);
        etEmail.setText(strEmail);
        etPassword = (EditText) findViewById(R.id.password);

        //登录事件
        Button btnLogin = (Button) findViewById(R.id.sign_in_button);
        btnLogin.setOnClickListener(this);

        //获取2个MobileAPI，获取天气数据，获取城市数据
        loadWeatherData();
        loadCityData();
    }
}

```

从上面的Oncreate()方法中，可以看出，需要做的事情太多了，其实可以简化成三块：

+ initVariables

+ initViews

+ loadData