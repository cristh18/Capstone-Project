package com.udacitynanodegree.cristhian.capstoneproject.ui.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.databinding.FragmentCategoryBinding;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.FragmentView;
import com.udacitynanodegree.cristhian.capstoneproject.ui.adapters.GenericAdapter;

public class CategoryFragment extends FragmentView {

    private FragmentCategoryBinding categoryBinding;
    private GenericAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        categoryBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false);
        return categoryBinding.getRoot();
    }


    @Override
    public String getName() {
        return CategoryFragment.class.getName();
    }


//    private String[] fragmentCategory = new String[1];
//
//    private RecyclerView myRecyclerView;
//    private StaggeredGridLayoutManager mStaggeredLayoutManager;
//    public static AppAdapter customListAdapter;
//
//    List<ApplicationData> appsData;
//
//    public CategoryFragment() {
//    }
//
//    public void setFragmentCategory(String category) {
//        fragmentCategory[0] = category;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
//    }
//
//    @Override
//    public String getName() {
//        return CategoryFragment.class.getName();
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             final Bundle savedInstanceState) {
//
//        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
//
//        Log.d(LOG_TAG, "savedInstanceState: " + savedInstanceState);
//        Log.d(LOG_TAG, "fragmentCategory: " + fragmentCategory);
//        Log.d(LOG_TAG, "fragmentCategory first element: " + fragmentCategory[0]);
//
//        appsData = new ArrayList<>();
//        Log.d(LOG_TAG, "Value appsData: " + appsData);
//
//        if (fragmentCategory[0] == null) {
//            if (savedInstanceState != null) {
//                customListAdapter.clear();
//                setFragmentCategory(String.valueOf(savedInstanceState.getInt("Pager_Current")));
//            }
//        }
//
//        Log.d(LOG_TAG, "Value fragmentCategory: " + fragmentCategory);
//        for (int j = 0; j < fragmentCategory.length; j++) {
//            Log.d(LOG_TAG, "Value fragmentCategory j element: " + fragmentCategory[j]);
//            int number = Integer.parseInt(fragmentCategory[j]) + 1;
//            getAppInfo(getActivity(), String.valueOf(number));
//        }
//
//        if (LaunchScreenActivity.IS_TABLET) {
//            mStaggeredLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        } else {
//            mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
//        }
//
//        myRecyclerView = (RecyclerView) rootView.findViewById(R.id.app_list);
//        myRecyclerView.setLayoutManager(mStaggeredLayoutManager);
//        myRecyclerView.setHasFixedSize(true);
//
//        customListAdapter = new AppAdapter(getActivity(), appsData);
//        myRecyclerView.setAdapter(customListAdapter);
//
//        customListAdapter.setOnItemClickListener(onItemClickListener);
//
//        return rootView;
//    }
//
//    /**
//     *
//     */
//    ItemClickListener onItemClickListener = new ItemClickListener() {
//        @Override
//        public void onItemClicked(View view, int position, ApplicationData data) {
//            ApplicationData applicationData = data;
//            Log.d(LOG_TAG, "click!!! " + applicationData.getApplicationName() + " click!!!");
//            showDetailInfoApp(data);
//        }
//    };
//
//    /**
//     * @param context
//     * @param categoryId
//     */
//    private void getAppInfo(Context context, String categoryId) {
//        ImageRepository imageRepository = ImageRepository.getImageRepoInstance();
//        AppRepository appRepository = AppRepository.getAppRepoInstance();
//        List<AppImage> imagesApp = imageRepository.getImagesByCategory(context, categoryId, context.getResources().getString(R.string.imageSize));
//        List<ApplicationData> apps = new ArrayList<>();
//        for (AppImage image : imagesApp) {
//            ApplicationData app = new ApplicationData();
//            app = appRepository.getAppById(context, image.getApplicationIdentifier());
//            app.setApplicationImage(image);
//            apps.add(app);
//        }
//
//        appsData.addAll(apps);
//    }
//
//    /**
//     * @param applicationData
//     */
//    private void showDetailInfoApp(ApplicationData applicationData) {
//        FragmentManager fm = getFragmentManager();
//        DetailAppFragment dialogFragment = new DetailAppFragment();
//        dialogFragment.setApplicationData(applicationData);
//        dialogFragment.show(fm, "App Detail");
//    }
}
