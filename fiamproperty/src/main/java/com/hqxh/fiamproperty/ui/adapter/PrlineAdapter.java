package com.hqxh.fiamproperty.ui.adapter;

import android.animation.Animator;
import android.content.Context;

import com.hqxh.fiamproperty.R;
import com.hqxh.fiamproperty.model.R_PRLINE.PRLINE;
import com.hqxh.fiamproperty.ui.widget.BaseViewHolder;
import com.hqxh.fiamproperty.unit.JsonUnit;

import java.util.List;


/**
 * Created by apple on 15/10/26
 * 试制采购申请-申请明细
 */
public class PrlineAdapter extends BaseQuickAdapter<PRLINE> {

    public PrlineAdapter(Context context, int layoutResId, List data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void startAnim(Animator anim, int index) {
        super.startAnim(anim, index);
        if (index < 5)
            anim.setStartDelay(index * 150);
    }

    @Override
    protected void convert(BaseViewHolder helper, PRLINE item) {
        helper.setText(R.id.prlinenum_text_id, JsonUnit.convertStrToArray(item.getPRLINENUM())[0]);
        helper.setText(R.id.description_text_id, JsonUnit.convertStrToArray(item.getDESCRIPTION())[0]);
        helper.setText(R.id.udmodel_text_id, JsonUnit.convertStrToArray(item.getUDMODEL())[0]);
        helper.setText(R.id.orderqty_text_id, JsonUnit.convertStrToArray(item.getORDERQTY())[0]);
        helper.setText(R.id.unitcost_text_id, JsonUnit.convertStrToArray(item.getUNITCOST2())[0]);
        helper.setText(R.id.linecost_text_id, JsonUnit.convertStrToArray(item.getLINECOST2())[0]);
    }


}
