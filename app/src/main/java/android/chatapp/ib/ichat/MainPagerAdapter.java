package android.chatapp.ib.ichat;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by ibrah on 6/25/2017.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {


    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {


        switch (position){
            case 0: ChatsFragment chatsFragment = new ChatsFragment();
                return chatsFragment;
            case 1: FriendsFragment friendsFragment = new FriendsFragment();
                return friendsFragment;
            default: return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    public CharSequence getPageTitle(int position){

            switch (position){
                case 0: return "Chats";
                case 1: return "Friends";
                default: return null;
            }

    }
}
