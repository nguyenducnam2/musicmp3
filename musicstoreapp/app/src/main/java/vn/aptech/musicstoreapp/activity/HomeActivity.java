package vn.aptech.musicstoreapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import vn.aptech.musicstoreapp.R;
import vn.aptech.musicstoreapp.adapter.MainViewPagerAdapter;
import vn.aptech.musicstoreapp.fragment.Fragment_Main_Home;
import vn.aptech.musicstoreapp.fragment.Fragment_Search;
import vn.aptech.musicstoreapp.fragment.LoadingDialog;

public class HomeActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final LoadingDialog loadingDialog = new LoadingDialog(HomeActivity.this);
        mapping();


        init();
//        overridePendingTransition(R.anim.anim_intent_in_home, R.anim.anim_intent_out);
    }

    private void mapping() {
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPager);
    }

    private void init() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new Fragment_Main_Home(), "");
        mainViewPagerAdapter.addFragment(new Fragment_Search(), "");
//        mainViewPagerAdapter.addFragment(new Fragment_Profile(), "");
//
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.icontimkiem);
//        tabLayout.getTabAt(2).setIcon(R.drawable.iconlogo);
//
    }
}