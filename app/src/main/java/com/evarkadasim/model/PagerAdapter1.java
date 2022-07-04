package com.evarkadasim.model;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.evarkadasim.ui.chats.chats_fragment;
import com.evarkadasim.ui.ev_arkadasi.ev_arkadasi_fragment;
import com.evarkadasim.ui.kalacak_yer.kalacak_yer_fragment;


public class PagerAdapter1 extends FragmentPagerAdapter {

    public PagerAdapter1(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ev_arkadasi_fragment fragment1 = new ev_arkadasi_fragment();
                return fragment1;

            case 1:
                kalacak_yer_fragment fragment2 = new kalacak_yer_fragment();
                return fragment2;

            case 2:
                chats_fragment fragment3 = new chats_fragment();
                return fragment3;

            default:
                return null;


        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Kalacak Yer Ara";

            case 1:
                return "Ev Arkadaşı Bul";

            case 2:
                return "Mesajlar";

            default:
                return null;


        }

    }
}
