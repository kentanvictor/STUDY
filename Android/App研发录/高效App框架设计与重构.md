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

代码如下：

```java

public abstract class BaseActivity extends Activity{
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(savedInstanceState);

        initVariables();
        initViews(savedInstanceState);
        loadData();
    }

    protected abstract void initVariables();
    protected abstract void initViews(Bundle savedInstanceState);
    protected abstract void loadData();
}

```

### 重构二：

一般的，对点击事件的监听方式可以有以下两种：

+ 第一种：

```java

public class LoginActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        //省略代码

        Button btnLogin = (Button)findViewById(R.id.sign_in_button);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.sign_in_button:
                Intent intent = new Intent(LoginActivity.this,PersonCenterActivity.class);
                startActivity(intent);
        }
    }
}

```

+ 第二种：

```java

//登录事件
btnLogin = (Button) findViewById(R.id.sign_in_button);
btnLogin.setOnClickListener(new View.OnClickListener{
    @Override
    public void onClick(View v){
        getoLoginActivity();
    }
});

```

书中更推荐使用`第二种`方式进行实现，其给出的原因是：

第二种相比较于第一种：
+ 直接在btnLogin这个按钮对象上增加点击事件，是面向对象的写法。
+ 将onClick方面的实现，封装成一个gotoLoginActivity方法。

## 实体化编程

下面是一段糟糕的代码：

```java

try{
    JSONObject jsonResponse = new JSONObject(result);
    JSONObject weatherInfo = jsonResponse.getJSONObject("weatherInfo");

    String city = weatherInfo.getString("city");
    int cityId = weatherInfo.getInt("cityid");
}catch(JSONException e){
    e.printStackTrace();
}

```

有两个问题：

+ 根据key值取value，其可以看成是一个字典，容易产生bug。
+ 每次都要手动的从JSONObject或者JSONArray中取值，很烦琐。