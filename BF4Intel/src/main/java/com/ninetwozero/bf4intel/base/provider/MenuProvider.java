package com.ninetwozero.bf4intel.base.provider;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

import androidx.core.view.ActionProvider;

import com.ninetwozero.bf4intel.R;

public class MenuProvider extends ActionProvider {

    private OnMenuProviderSelectedListener listener;
    private MenuProviderOnMenuItemClickListener menuProviderOnMenuItemClickListener = new MenuProviderOnMenuItemClickListener();
    private int expansionItemResourceTitle;

    public interface OnMenuProviderSelectedListener {
        public void onMenuSelected(MenuItem item);
    }

    private Context context;
    private int maximumMenuCount = 5;
    private String[] menuTitles;

    public MenuProvider(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public View onCreateActionView() {
        return null;
    }

    @Override
    public boolean hasSubMenu() {
        return true;
    }

    public void setMenuTitles(final String[] menuTitles) {
        this.menuTitles = menuTitles;
    }

    public void setOnMenuSelectedListener(OnMenuProviderSelectedListener listener) {
        this.listener = listener;
    }

    public void setExpansionItemTitle(int resourceId) {
        expansionItemResourceTitle = resourceId;
    }

    private int getExpansionItemResourceTitle() {
        return expansionItemResourceTitle == 0 ? R.string.filter_more_options : expansionItemResourceTitle;
    }

    @Override
    public void onPrepareSubMenu(SubMenu subMenu) {
        subMenu.clear();
        int menuTitlesCount = menuTitles.length;
        final int collapsedActivityCount = Math.min(maximumMenuCount, menuTitlesCount);
        for (int i = 0; i < collapsedActivityCount; i++) {
            subMenu.add(0, i, i, menuTitles[i])
                .setOnMenuItemClickListener(menuProviderOnMenuItemClickListener);
        }

        if (maximumMenuCount < menuTitlesCount) {
            SubMenu expandedSubMenu = subMenu
                .addSubMenu(Menu.NONE, maximumMenuCount, maximumMenuCount, context.getResources().getString(getExpansionItemResourceTitle()));
            for (int i = 0; i < menuTitlesCount; i++) {
                expandedSubMenu.add(0, i, i, menuTitles[i])
                    .setOnMenuItemClickListener(menuProviderOnMenuItemClickListener);
            }
        }
    }

    private class MenuProviderOnMenuItemClickListener implements MenuItem.OnMenuItemClickListener {

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            listener.onMenuSelected(item);
            return true;
        }
    }
}
