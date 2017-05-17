package com.lottery.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;

import com.jpeng.jptabbar.JPTabBar;
import com.jpeng.jptabbar.OnTabSelectListener;
import com.lottery.R;
import com.lottery.base.BaseActivity;
import com.lottery.fragment.HomeFragment;
import com.lottery.fragment.PersonFragment;
import com.lottery.fragment.RechargeFragment;
import com.lottery.fragment.TogetherBuyFragment;

public class HomeActivity extends BaseActivity {

    private JPTabBar mTabbar;

    public HomeActivity() {
        super(R.layout.activity_home);
    }

    @Override
    protected void initContentView(Bundle bundle) {

    }

    @Override
    protected void initView() {
        mTabbar = (JPTabBar) findViewById(R.id.tabbar);
        mTabbar.setTitles(R.string.home, R.string.together_buy, R.string.recharge, R.string.myself)
                .setNormalIcons(R.mipmap.ic_home, R.mipmap.ic_together_buy, R.mipmap.ic_recharge, R.mipmap.ic_myself)
                .setSelectedIcons(R.mipmap.ic_home_selected, R.mipmap.ic_together_buy_selected, R.mipmap
                        .ic_recharge_selected, R.mipmap.ic_myself_selected)
                .generate();
        mTabbar.setSelectedColor(ContextCompat.getColor(this, R.color.red));
        setTitle("购彩大厅");
        replaceFragment(new HomeFragment());
        mTabbar.setTabListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int index) {
                switch (index) {
                    case 0:
                        setTitle("购彩大厅");
                        toolbar.setBackgroundColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                        replaceFragment(new HomeFragment());
                        break;
                    case 1:
                        setTitle("合买大厅");
                        toolbar.setBackgroundColor(ContextCompat.getColor(HomeActivity.this, R.color.colorAccent));
                        replaceFragment(new TogetherBuyFragment());
                        break;
                    case 2:
                        setTitle("充值中心");
                        toolbar.setBackgroundColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                        replaceFragment(new RechargeFragment());
                        break;
                    case 3:
                        setTitle("个人中心");
                        toolbar.setBackgroundColor(ContextCompat.getColor(HomeActivity.this, R.color.colorAccent));
                        replaceFragment(new PersonFragment());
                        break;
                }
            }
        });
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    /**
     * 切换fragment页面
     *
     * @param fragment
     */
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        beginTransaction.replace(R.id.content, fragment);
        beginTransaction.commit();
    }

}
