package com.hqxh.fiamproperty.ui.adapter;

import android.animation.Animator;
import android.content.Context;

import com.hqxh.fiamproperty.R;
import com.hqxh.fiamproperty.model.R_ZXPERSON.PERSION;
import com.hqxh.fiamproperty.ui.widget.BaseViewHolder;
import com.hqxh.fiamproperty.unit.JsonUnit;

import java.util.List;


/**
 * Created by apple on 15/10/26
 * 执行人
 */
public class PersonAdapter extends BaseQuickAdapter<PERSION> {

    public PersonAdapter(Context context, int layoutResId, List data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void startAnim(Animator anim, int index) {
        super.startAnim(anim, index);
        if (index < 5)
            anim.setStartDelay(index * 150);
    }

    @Override
    protected void convert(BaseViewHolder helper, PERSION item) {
        helper.setText(R.id.displayname_text_id, JsonUnit.convertStrToArray(item.getPERSONID())[0]+","+JsonUnit.convertStrToArray(item.getDISPLAYNAME())[0]);
        helper.setText(R.id.cudept_text_id, JsonUnit.convertStrToArray(item.getCUDEPT())[0]);
        helper.setText(R.id.cucrew_text_id, JsonUnit.convertStrToArray(item.getCUCREW())[0]);
    }


}
