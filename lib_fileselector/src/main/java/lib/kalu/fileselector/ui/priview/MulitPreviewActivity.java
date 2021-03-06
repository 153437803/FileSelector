package lib.kalu.fileselector.ui.priview;

import android.os.Bundle;

import androidx.annotation.Nullable;

import java.util.List;

import lib.kalu.fileselector.loadercallback.SelectedItemCollection;
import lib.kalu.fileselector.model.MediaModel;
import lib.kalu.fileselector.model.SelectorModel;
import lib.kalu.fileselector.ui.base.BasePreviewActivity;

/**
 * description: 选中图片集合预览
 * create by Administrator on 2020-03-27
 */
public class MulitPreviewActivity extends BasePreviewActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!SelectorModel.getInstance().hasInited) {
            setResult(RESULT_CANCELED);
            finish();
            return;
        }

        Bundle bundle = getIntent().getBundleExtra(EXTRA_DEFAULT_BUNDLE);
        List<MediaModel> selected = (List<MediaModel>) bundle.getSerializable(SelectedItemCollection.STATE_SELECTION);
        mAdapter.addAll(selected);
        mAdapter.notifyDataSetChanged();
        if (mSpec.countable) {
            mCheckView.setCheckedNum(1);
        } else {
            mCheckView.setChecked(true);
        }
        mPreviousPos = 0;
        updateSize(selected.get(0));
    }

}
