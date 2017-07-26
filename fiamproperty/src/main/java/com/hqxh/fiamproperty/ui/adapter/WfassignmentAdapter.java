package com.hqxh.fiamproperty.ui.adapter;

import android.animation.Animator;
import android.content.Context;

import com.hqxh.fiamproperty.R;
import com.hqxh.fiamproperty.model.Wfassignment;
import com.hqxh.fiamproperty.ui.widget.BaseViewHolder;
import com.hqxh.fiamproperty.unit.JsonUnit;

import java.util.List;


/**
 * Created by apple on 15/10/26
 * 待办任务
 */
public class WfassignmentAdapter extends BaseQuickAdapter<Wfassignment> {

    public WfassignmentAdapter(Context context, int layoutResId, List data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void startAnim(Animator anim, int index) {
        super.startAnim(anim, index);
        if (index < 5)
            anim.setStartDelay(index * 150);
    }

    @Override
    protected void convert(BaseViewHolder helper, Wfassignment item) {
        helper.setImageResource(R.id.task_image_id, R.mipmap.ic_db);
        helper.setText(R.id.duedate_text_id, JsonUnit.convertStrToArray(item.getDUEDATE())[0]);
        helper.setText(R.id.processname_text_id, JsonUnit.convertStrToArray(item.getPROCESSNAME())[0]);
        helper.setText(R.id.description_text_id, JsonUnit.convertStrToArray(item.getDESCRIPTION())[0]);
    }





}
