package com.hqxh.fiamproperty.ui.adapter;

import android.animation.Animator;
import android.content.Context;

import com.hqxh.fiamproperty.R;
import com.hqxh.fiamproperty.model.R_GRLINE;
import com.hqxh.fiamproperty.model.R_GRLINE.GRLINE;
import com.hqxh.fiamproperty.ui.widget.BaseViewHolder;
import com.hqxh.fiamproperty.unit.JsonUnit;

import java.util.List;

/**
 * Created by Administrator on 2017/8/15.
 */

public class WzmxAdapter extends BaseQuickAdapter<GRLINE>{

    public WzmxAdapter(Context context, int layoutResId, List data) {
        super(context, layoutResId, data);
    }
    @Override
    protected void startAnim(Animator anim, int index) {
        super.startAnim(anim, index);
        if (index < 5)
            anim.setStartDelay(index * 150);
    }

    @Override
    protected void convert(BaseViewHolder helper, GRLINE item) {
        helper.setText(R.id.description1_text_id, JsonUnit.convertStrToArray(item.getDESCRIPTION())[0]);
        helper.setText(R.id.grlin1_text_id,JsonUnit.convertStrToArray(item.getGRLIN1())[0]);
        helper.setText(R.id.qty_text_id,JsonUnit.convertStrToArray(item.getQTY())[0]);
        helper.setText(R.id.measureunitid_text_id,JsonUnit.convertStrToArray(item.getMEASUREUNITID())[0]);


    }
}
