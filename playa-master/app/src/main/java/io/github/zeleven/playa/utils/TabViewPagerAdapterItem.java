package io.github.zeleven.playa.utils;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import io.github.zeleven.playa.data.model.Category;
import io.github.zeleven.playa.ui.module.main.hierarchy.detail.tabpage.HierarchyTabPageFragment;
import io.github.zeleven.playa.ui.module.main.project.tabpage.ProjectTabPageFragment;

/**
 * 标题和碎片的适配器
 */
public class TabViewPagerAdapterItem {
    private String title;
    private Fragment fragment;

    public TabViewPagerAdapterItem(String title, Fragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    /**
     * 创建项目分类 tab
     *
     * @param categories
     * @return
     */
    public static List<TabViewPagerAdapterItem> createProjectTabFragments(List<Category> categories) {
        ArrayList<TabViewPagerAdapterItem> adapterItems = new ArrayList<>();
        for (Category category : categories) {
            adapterItems.add(new TabViewPagerAdapterItem(
                    category.getName(),
                    ProjectTabPageFragment.newInstance(category.getId())
            ));
        }
//        for (int i = 0; i < categories.size(); i++) {
//            adapterItems.add(new TabViewPagerAdapterItem(
//                    categories.get(i).getName(),
//                    ProjectTabPageFragment.newInstance(categories.get(i).getId())
//            ));
//        }
        return adapterItems;
    }

    /**
     * 体系
     *
     * @param categories
     * @return
     */
    public static List<TabViewPagerAdapterItem> createHierarchyTabFragments(List<Category> categories) {
        ArrayList<TabViewPagerAdapterItem> adapterItems = new ArrayList<>();
        for (Category category : categories) {
            adapterItems.add(new TabViewPagerAdapterItem(
                    category.getName(),
                    HierarchyTabPageFragment.newInstance(category.getId())
            ));
        }
//        for (int i = 0; i < categories.size(); i++) {
//            adapterItems.add(new TabViewPagerAdapterItem(
//                    categories.get(i).getName(),
//                    HierarchyTabPageFragment.newInstance(categories.get(i).getId())
//            ));
//        }
        return adapterItems;
    }
}
