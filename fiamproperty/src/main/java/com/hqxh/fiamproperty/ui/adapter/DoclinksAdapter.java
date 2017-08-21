package com.hqxh.fiamproperty.ui.adapter;

import android.animation.Animator;
import android.content.Context;

import com.hqxh.fiamproperty.R;
import com.hqxh.fiamproperty.model.R_DOCLINKS.DOCLINKS;
import com.hqxh.fiamproperty.ui.widget.BaseViewHolder;
import com.hqxh.fiamproperty.unit.JsonUnit;

import java.util.List;


/**
 * Created by apple on 15/10/26
 * 附件文档
 */
public class DoclinksAdapter extends BaseQuickAdapter<DOCLINKS> {
    public DoclinksAdapter(Context context, int layoutResId, List data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void startAnim(Animator anim, int index) {
        super.startAnim(anim, index);
        if (index < 5)
            anim.setStartDelay(index * 150);
    }

    @Override
    protected void convert(BaseViewHolder helper, DOCLINKS item) {
        helper.setText(R.id.document_text_id, JsonUnit.convertStrToArray(item.getDOCUMENT())[0]);
        helper.setText(R.id.description_text_id, JsonUnit.convertStrToArray(item.getDESCRIPTION())[0]);
        helper.setText(R.id.create_owner_text_id, JsonUnit.convertStrToArray(item.getOWNER())[0]);
        helper.setText(R.id.createdate_text_id, JsonUnit.strToDateString(JsonUnit.convertStrToArray(item.getCREATEDATE())[0]));
        helper.setTextColorRes(R.id.preview_button_id, R.color.btn_color);
    }


}
