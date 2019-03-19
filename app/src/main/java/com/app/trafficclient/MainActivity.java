package com.app.trafficclient;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.app.trafficclient.fragment.DesignFragment;
import com.app.trafficclient.fragment.Fragment_item_1;
import com.app.trafficclient.fragment.Fragment_item_10;
import com.app.trafficclient.fragment.Fragment_item_11;
import com.app.trafficclient.fragment.Fragment_item_12;
import com.app.trafficclient.fragment.Fragment_item_13;
import com.app.trafficclient.fragment.Fragment_item_14;
import com.app.trafficclient.fragment.Fragment_item_15;
import com.app.trafficclient.fragment.Fragment_item_16;
import com.app.trafficclient.fragment.Fragment_item_17;
import com.app.trafficclient.fragment.Fragment_item_18;
import com.app.trafficclient.fragment.Fragment_item_19;
import com.app.trafficclient.fragment.Fragment_item_2;
import com.app.trafficclient.fragment.Fragment_item_20;
import com.app.trafficclient.fragment.Fragment_item_21;
import com.app.trafficclient.fragment.Fragment_item_3;
import com.app.trafficclient.fragment.Fragment_item_4;
import com.app.trafficclient.fragment.Fragment_item_5;
import com.app.trafficclient.fragment.Fragment_item_6;
import com.app.trafficclient.fragment.Fragment_item_7;
import com.app.trafficclient.fragment.Fragment_item_8;
import com.app.trafficclient.fragment.Fragment_item_9;
import com.app.trafficclient.fragment.MainContentFragment;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private SQLiteDatabase db;

    private SlidingPaneLayout slidepanel;
    private ViewPager mViewPager;
    BottomNavigationView navigation;

    private ListView listView;
    SimpleAdapter simpleAdapter;

    ArrayList<HashMap<String, Object>> actionItems;
    SimpleAdapter actionAdapter;

    TextView tV_title;
    ImageView iVSliding;
    ImageView ivHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mySQLiteOpenHelper = new MySQLiteOpenHelper(MainActivity.this, MySQLiteOpenHelper.DBNAME, null, 1);
        db = mySQLiteOpenHelper.getWritableDatabase();

        slidepanel = (SlidingPaneLayout) findViewById(R.id.slidingPL);

        listView = (ListView) findViewById(R.id.listView1);
        ivHome = (ImageView) findViewById(R.id.imageView_home);

        iVSliding = (ImageView) findViewById(R.id.imageView_Sliding);
        tV_title = (TextView) findViewById(R.id.tv_title);
        tV_title.setText("智能交通");

        iVSliding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (slidepanel.isOpen()) {
                    slidepanel.closePane();
                } else {
                    slidepanel.openPane();
                }
            }
        });

        final String[] actionTexts = new String[]{
                "模块代码1_个人车辆ETC账户管理",
                "模块代码2_红绿灯管理1",
                "模块代码3_充值历史记录",
                "模块代码4_车辆违章浏览",
                "模块代码5_环境指标实时显示",
                "模块代码6_传感器实时数据显示",
                "模块代码7_阈值设置",
                "模块代码8_公司交通单双号管制",
                "模块代码9_车管局车辆账户管理功能",
                "模块代码10_公交查询模块",
                "模块代码11_红绿灯管理2",
                "模块代码12_车辆违章查看",
                "模块代码13_路况查询",
                "模块代码14_生活助手",
                "模块代码15_数据分析1",
                "模块代码16_个人中心1",
                "模块代码17_生活指数",
                "模块代码18_我的消息",
                "模块代码19_数据分析2",
                "模块代码20_个人中心2",
                "模块代码21_红绿灯管理3",
                "用户退出"
        };

        int[] actionImages = new int[]{
                R.drawable.btn_l_grid,
                R.drawable.btn_l_grid,
                R.drawable.btn_l_grid,
                R.drawable.btn_l_grid,
                R.drawable.btn_l_grid,
                R.drawable.btn_l_grid,
                R.drawable.btn_l_grid,
                R.drawable.btn_l_grid,
                R.drawable.btn_l_grid,
                R.drawable.btn_l_grid,
                R.drawable.btn_l_grid,
                R.drawable.btn_l_grid,
                R.drawable.btn_l_grid,
                R.drawable.btn_l_grid,
                R.drawable.btn_l_grid,
                R.drawable.btn_l_grid,
                R.drawable.btn_l_grid,
                R.drawable.btn_l_grid,
                R.drawable.btn_l_grid,
                R.drawable.btn_l_grid,
                R.drawable.btn_l_grid,
                R.drawable.btn_l_grid,
        };

        actionItems = new ArrayList<>();
        actionAdapter = new SimpleAdapter(getApplicationContext(), actionItems, R.layout.main_left_list_fragment_item,
                new String[]{"action_icon", "action_name"},
                new int[]{R.id.recharge_method_icon, R.id.recharge_method_name});

        for (int i = 0; i < actionImages.length; ++i) {
            HashMap<String, Object> item1 = new HashMap<>();
            item1.put("action_icon", actionImages[i]);
            item1.put("action_name", actionTexts[i]);
            actionItems.add(item1);
        }
        listView.setAdapter(actionAdapter);
        //侧滑栏点击入口
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                switch (arg2) {
                    case 0:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fraghome,new Fragment_item_1()).commit();
                        break;
                    case 1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fraghome,new Fragment_item_2()).commit();
                        break;
                    case 2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fraghome,new Fragment_item_3()).commit();
                        break;
                    case 3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fraghome,new Fragment_item_4()).commit();
                        break;
                    case 4:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fraghome,new Fragment_item_5()).commit();
                        break;
                    case 5:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fraghome,new Fragment_item_6()).commit();
                        break;
                    case 6:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fraghome,new Fragment_item_7()).commit();
                        break;
                    case 7:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fraghome,new Fragment_item_8()).commit();
                        break;
                    case 8:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fraghome,new Fragment_item_9()).commit();
                        break;
                    case 9:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fraghome,new Fragment_item_10()).commit();
                        break;
                    case 10:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fraghome,new Fragment_item_11()).commit();
                        break;
                    case 11:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fraghome,new Fragment_item_12()).commit();
                        break;
                    case 12:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fraghome,new Fragment_item_13()).commit();
                        break;
                    case 13:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fraghome,new Fragment_item_14()).commit();
                        break;
                    case 14:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fraghome,new Fragment_item_15()).commit();
                        break;
                    case 15:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fraghome,new Fragment_item_16()).commit();
                        break;
                    case 16:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fraghome,new Fragment_item_17()).commit();
                        break;
                    case 17:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fraghome,new Fragment_item_18()).commit();
                        break;
                    case 18:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fraghome,new Fragment_item_19()).commit();
                        break;
                    case 19:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fraghome,new Fragment_item_20()).commit();
                        break;
                    case 20:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fraghome,new Fragment_item_21()).commit();
                        break;
                    case 21:    //退出
                        exitAppDialog();
                        break;
                    default:
                        break;
                }

            }
        });

        initView();

        getSupportFragmentManager().beginTransaction().replace(R.id.main_fraghome,new Fragment_item_1()).commit();
    }


    private void initView() {
        /*初始化显示内容*/
        mViewPager = (ViewPager) findViewById(R.id.pager);
        final ArrayList<Fragment> fgLists = new ArrayList<>(3);
        fgLists.add(new MainContentFragment());     //主页内容
        fgLists.add(new DesignFragment());          //创意设计
        FragmentPagerAdapter mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fgLists.get(position);
            }

            @Override
            public int getCount() {
                return fgLists.size();
            }
        };
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(2); //预加载剩下两页

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        /*给底部导航栏菜单项添加点击事件*/
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        navigation.getMenu().findItem(R.id.navigation_1).setChecked(true);
                        break;
                    case 1:
                        navigation.getMenu().findItem(R.id.navigation_3).setChecked(true);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_1:
                    mViewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_3:
                    mViewPager.setCurrentItem(1);
                    return true;
            }
            return false;
        }

    };

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 按下键盘上返回按钮
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            exitAppDialog();

            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }

    public void exitAppDialog() {
        new AlertDialog.Builder(this)
                // .setIcon(android.R.drawable.ic_menu_info_detailsp)
                .setTitle("提示").setMessage("你确定要退出吗").setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener()

        {
            public void onClick(DialogInterface dialog, int whichButton) {
                finish();
            }
        }).show();

    }
}

