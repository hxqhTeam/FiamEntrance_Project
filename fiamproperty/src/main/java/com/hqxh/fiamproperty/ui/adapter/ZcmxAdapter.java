package com.hqxh.fiamproperty.ui.adapter;

import android.animation.Animator;
import android.content.Context;
import android.util.Log;

import com.hqxh.fiamproperty.R;
import com.hqxh.fiamproperty.model.R_GRLINE.GRLINE;
import com.hqxh.fiamproperty.ui.widget.BaseViewHolder;
import com.hqxh.fiamproperty.unit.JsonUnit;

import java.util.List;

/**
 * Created by Administrator on 2017/8/15.
 */

public class ZcmxAdapter extends BaseQuickAdapter<GRLINE> {


    public ZcmxAdapter(Context context, int layoutResId, List<GRLINE> data) {
        super(context, layoutResId, data);
    }

    protected void startAnim(Animator anim, int index) {
        super.startAnim(anim, index);
        if (index < 5)
            anim.setStartDelay(index * 150);
    }
    @Override
    protected void convert(BaseViewHolder helper, GRLINE item) {


        helper.setText(R.id.samplenum_text_id, JsonUnit.convertStrToArray(item.getSAMPLENUM())[0]);
        helper.setText(R.id.udmodel_text_id, JsonUnit.convertStrToArray(item.getUDMODEL())[0]);
        helper.setText(R.id.udlicensenum_text_id, JsonUnit.convertStrToArray(item.getUDLICENSENUM())[0]);
        helper.setText(R.id.udvehicletype_text_id, JsonUnit.convertStrToArray(item.getUDVEHICLETYPE())[0]);

    }


}
