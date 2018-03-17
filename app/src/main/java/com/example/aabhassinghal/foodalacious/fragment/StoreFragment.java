package com.example.aabhassinghal.foodalacious.fragment;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.aabhassinghal.foodalacious.CustomAdapter;
import com.example.aabhassinghal.foodalacious.GridSpacingItemDecoration;
import com.example.aabhassinghal.foodalacious.Model;
import com.example.aabhassinghal.foodalacious.R;
import com.example.aabhassinghal.foodalacious.Recipe;
import com.example.aabhassinghal.foodalacious.RecipeObject;
import com.example.aabhassinghal.foodalacious.RecyclerTouchListener;
import com.example.aabhassinghal.foodalacious.StoreAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by aabhassinghal on 1/6/18.
 */

public class StoreFragment extends Fragment implements StoreAdapter.StorageAdapterListener{
    String url = "http://10.0.0.49:8000/";
    private static final String TAG = StoreFragment.class.getSimpleName();

    private RecyclerView recyclerView;
    private ImageButton recipe_addition;
    private List<Recipe> recipeList;
    private StoreAdapter.StorageAdapterListener listener;
    private StoreAdapter mAdapter;
    private PopupWindow mPopupWindow;
    private RelativeLayout mRelativeLayout;
    private LinearLayout mLayout;
    private SearchView searchView;
    ListView listView;
    ImageView iv;
    EditText editTextView;
    ArrayList<Model> ItemModelList;
    CustomAdapter customAdapter;
    int i=0;
    public StoreFragment() {
        // Required empty public constructor
    }

    public static StoreFragment newInstance(String param1, String param2) {
        StoreFragment fragment = new StoreFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_store, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        recipe_addition = view.findViewById(R.id.imageButton2);
        mRelativeLayout = view.findViewById(R.id.rl);
        recipe_addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Initialize a new instance of LayoutInflater service
                final LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                // Inflate the custom layout/view
                final View customView = inflater.inflate(R.layout.recipe_addition,null);

                /*
                    public PopupWindow (View contentView, int width, int height)
                        Create a new non focusable popup window which can display the contentView.
                        The dimension of the window must be passed to this constructor.

                        The popup does not provide any background. This should be handled by
                        the content view.

                    Parameters
                        contentView : the popup's content
                        width : the popup's width
                        height : the popup's height
                */
                // Initialize a new instance of popup window
                mPopupWindow = new PopupWindow(
                        customView,
                        LayoutParams.MATCH_PARENT,
                        LayoutParams.MATCH_PARENT
                );

                // Set an elevation value for popup window
                // Call requires API level 21
                if(Build.VERSION.SDK_INT>=21){
                    mPopupWindow.setElevation(5.0f);
                }
                mPopupWindow.setFocusable(true);
                listView = (ListView) customView.findViewById(R.id.listview);
                editTextView = (EditText) customView.findViewById(R.id.editText0);
                ItemModelList = new ArrayList<Model>();
                customAdapter = new CustomAdapter(getActivity().getApplicationContext(), ItemModelList);
//                listView.setEmptyView(customView.findViewById(R.id.empty));
                listView.setAdapter(customAdapter);
                iv = (ImageView)customView.findViewById(R.id.imageViewAdd);
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = editTextView.getText().toString();
                        Model md = new Model(name);
                        ItemModelList.add(md);
                        customAdapter.notifyDataSetChanged();
                        editTextView.setText("");

                    }
                });


                Button submit = (Button) customView.findViewById(R.id.button3);
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                ImageButton closeButton = (ImageButton) customView.findViewById(R.id.ib_close);

                // Set a click listener for the popup window close button
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Dismiss the popup window
                        mPopupWindow.dismiss();
                    }
                });

                /*
                    public void showAtLocation (View parent, int gravity, int x, int y)
                        Display the content view in a popup window at the specified location. If the
                        popup window cannot fit on screen, it will be clipped.
                        Learn WindowManager.LayoutParams for more information on how gravity and the x
                        and y parameters are related. Specifying a gravity of NO_GRAVITY is similar
                        to specifying Gravity.LEFT | Gravity.TOP.

                    Parameters
                        parent : a parent view to get the getWindowToken() token from
                        gravity : the gravity which controls the placement of the popup window
                        x : the popup's x location offset
                        y : the popup's y location offset
                */
                // Finally, show the popup window at the center location of root relative layout
                mPopupWindow.setAnimationStyle(R.style.Animation);
                mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER,1,1);
            }
        });
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.toolbar_title);
        setHasOptionsMenu(true);
        recipeList = new ArrayList<>();
        mAdapter = new StoreAdapter(getActivity(), recipeList, listener);
        whiteNotificationBar(recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(8), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onClick(View view, int position) {
                Recipe recipe = recipeList.get(position);
                Toast.makeText(getActivity().getApplicationContext(), "Selected: " +recipe.getTitle(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        fetchStoreItems();

        return view;
    }
    @SuppressLint("NewApi")
    public void addValue(View v) {
        String name = editTextView.getText().toString();
            Model md = new Model(name);
            ItemModelList.add(md);
            customAdapter.notifyDataSetChanged();
            editTextView.setText("");

    }
//    public void ondelete(View v){
//        final LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        // Inflate the custom layout/view
//        final View customView = inflater.inflate(R.layout.recipe_addition,null);
//
//    }
    void fetchStoreItems() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        RecipeObject service = retrofit.create(RecipeObject.class);
        Call<List<Recipe>> call = service.getallStudentDetails();
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Response<List<Recipe>> response, Retrofit retrofit) {
                try {
                    Log.d("message","asssA"+response.body());
                    List<Recipe> re= response.body();
                    recipeList.clear();
                    recipeList.addAll(re);
                    mAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onBackPressed() {
//        // close search view on back button pressed
//        if (!searchView.isIconified()) {
//            searchView.setIconified(true);
//            return;
//        }
//        super.onBackPressed();
//    }
    private EditText createNewEditextView() {
        final LayoutParams lparams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        final EditText editextView = new EditText(getActivity());
        editextView.setLayoutParams(lparams);
        return editextView;
    }
    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getActivity().getWindow().setStatusBarColor(Color.WHITE);
        }
    }
    @Override
    public void onRecipeSelected(Recipe recipe) {
        Toast.makeText(getActivity().getApplicationContext(), "Selected: " + recipe.getTitle(), Toast.LENGTH_LONG).show();
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


}