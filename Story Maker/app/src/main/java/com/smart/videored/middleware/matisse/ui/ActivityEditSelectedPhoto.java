package com.smart.videored.middleware.matisse.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.ads.control.AdmobHelp;
import com.smart.videored.core.screen.activity.EditPhotoActivity;
import com.smart.videored.core.listener.eventbus.EditerModel;
import com.smart.videored.R;
import com.smart.videored.ui.activities.MovieActivity;
import com.smart.videored.utils.MyConstant;
import com.smart.videored.middleware.matisse.internal.entity.Item;
import com.smart.videored.middleware.matisse.internal.utils.ImageEditCallback;
import com.smart.videored.middleware.matisse.internal.utils.PathUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ActivityEditSelectedPhoto extends AppCompatActivity {

    private RecyclerView imageRcv;
    private TextView vCount;
    private ImageEditAdapter adapter;

    private String selectedString = "";
    private LoadingDialog progressDialog;
    private ArrayList<String> sendPhotos = new ArrayList<>();
    private List<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_edit_selected_photo);

        imageRcv = findViewById(R.id.editSelectedPhotoList);
        vCount = findViewById(R.id.editSelectedPhotoCount);
        Toolbar toolbar = findViewById(R.id.toolbar);
        View vDone = findViewById(R.id.editSelectedPhotoDone);
        View vBack = findViewById(R.id.editSelectedPhotoBack);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            //actionBar.setDisplayHomeAsUpEnabled(true);
        }
        /*
        Drawable navigationIcon = toolbar.getNavigationIcon();
        if (navigationIcon != null) {
            navigationIcon.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
        }
        */
        findViewById(R.id.fab).setOnClickListener(view -> {
                    onBackPressed();
                }
        );

        selectedString = getString(R.string.selected);

        initImageList();

        vDone.setOnClickListener(view -> {
            LoadingDialog progressDialog2 = new LoadingDialog(this);
            this.progressDialog = progressDialog2;
            this.progressDialog.show();
            this.sendPhotos.clear();
            for (int i = 0; i < this.adapter.getItems().size(); i++) {
                this.sendPhotos.add(this.items.get(i).uri.toString());
            }
            createMovie();
          /*  Intent resultData = new Intent();
            resultData.putParcelableArrayListExtra(MyConstant.KEY_SELECTED_IMAGES, new ArrayList<>(adapter.getItems()));
            setResult(RESULT_OK, resultData);
            finish();*/
        });
        vBack.setOnClickListener(view -> onBackPressed());
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EditerModel event) {
        if (!event.getLink().equals("")) {
            for (int i = 0; i < items.size(); i++) {
                if (i == event.getPos()) {
                    items.get(i).uri = Uri.fromFile(new File(event.getLink()));
//                    adapter.getItems().get(i).uri = Uri.parse(event.getLink());
                    break;
                }
            }
            adapter.notifyItemChanged(event.getPos());
        }
    }

    ;

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void createMovie() {
        if (this.sendPhotos.size() < 3) {
            LoadingDialog progressDialog2 = this.progressDialog;
            if (progressDialog2 != null && progressDialog2.isShowing()) {
                this.progressDialog.dismiss();
            }
            Toast.makeText(this, getString(R.string.more_than_3_photos), Toast.LENGTH_SHORT).show();
            return;
        }

        AdmobHelp.getInstance().showInterstitialAd(this,() -> {
            Intent intent = new Intent(this, MovieActivity.class);
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("PHOTO", this.sendPhotos);
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }

    public void onStop() {
        LoadingDialog progressDialog2 = this.progressDialog;
        if (progressDialog2 != null && progressDialog2.isShowing()) {
            this.progressDialog.dismiss();
        }
        super.onStop();
    }


    private void initImageList() {
        Bundle imageBundle = getIntent().getExtras();
        if (imageBundle != null) {
            items = imageBundle.getParcelableArrayList(MyConstant.KEY_SELECTED_IMAGES);
            initAdapter();
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ImageEditCallback(adapter));
            itemTouchHelper.attachToRecyclerView(imageRcv);
            setCount(items.size());
        }
    }

    private void initAdapter() {
        adapter = new ImageEditAdapter(items, new ImageEditAdapter.OnItemInteraction() {
            @Override
            public void onItemRemoved(Item item, int position) {
                setCount(adapter.getItemCount());
            }

            @Override
            public void onEditClick(Item item, int position) {
                toEditPhotoActivity(position);
            }
        });
        imageRcv.setAdapter(adapter);
    }

    private void toEditPhotoActivity(int position) {
        ArrayList<String> pathList = new ArrayList<>();
        for (Item item : adapter.getItems()) {
            pathList.add(PathUtils.getPath(this, item.getContentUri()));
        }

        Intent intent = new Intent(this, EditPhotoActivity.class);
        intent.putStringArrayListExtra("PHOTO", pathList);
        intent.putExtra("POSITION", position);
        startActivity(intent);
    }

    private void setCount(int count) {
        vCount.setText(new StringBuilder()
                .append(count)
                .append(" ")
                .append(selectedString)
        );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

 /*   @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != RESULT_OK) return;
        if (requestCode == RESULT_OK) {
            String selected = data.getStringExtra(MyConstant.NEW_URL);
            int pos = data.getIntExtra(MyConstant.POSITION,0);
            for (int i = 0; i< items.size();i++){
                if (i == pos) items.get(i).uri = Uri.parse(selected);
            }
            adapter.notifyDataSetChanged();

        }
        super.onActivityResult(requestCode, resultCode, data);
    }*/
}