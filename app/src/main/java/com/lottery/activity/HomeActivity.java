package com.lottery.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.jpeng.jptabbar.JPTabBar;
import com.jpeng.jptabbar.OnTabSelectListener;
import com.lottery.R;
import com.lottery.fragment.HomeFragment;

public class HomeActivity extends AppCompatActivity {

    private JPTabBar mTabbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mTabbar = (JPTabBar) findViewById(R.id.tabbar);
        mTabbar.setTitles(R.string.home, R.string.together_buy, R.string.recharge, R.string.myself)
                .setNormalIcons(R.mipmap.ic_home, R.mipmap.ic_together_buy, R.mipmap.ic_recharge, R.mipmap.ic_myself)
                .setSelectedIcons(R.mipmap.ic_home_selected, R.mipmap.ic_together_buy_selected, R.mipmap
                        .ic_recharge_selected, R.mipmap.ic_myself_selected)
                .generate();
        mTabbar.setSelectedColor(ContextCompat.getColor(this, R.color.red));
        replaceFragment(new HomeFragment());
        mTabbar.setTabListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int index) {
                switch (index) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
            }
        });
    }

    /**
     * 切换fragment页面
     *
     * @param fragment
     */
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.replace(R.id.content, fragment);
        beginTransaction.commit();
    }

}
