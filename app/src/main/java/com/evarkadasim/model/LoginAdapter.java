package com.evarkadasim.model;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.evarkadasim.login_fragment;
import com.evarkadasim.register_fragment;

public class LoginAdapter extends FragmentPagerAdapter {

    public LoginAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {


        switch (position) {
            case 0:
                login_fragment login_fragment = new login_fragment();
                return login_fragment;

            case 1:
                register_fragment register_fragment = new register_fragment();
                return register_fragment;
            default:
                return null;


        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Giriş Yap";

            case 1:
                return "Kaydol";

            default:
                return null;


        }

    }
}
