package com.hqxh.fiamproperty.ui.adapter;

import android.animation.Animator;
import android.content.Context;
import android.util.Log;

import com.hqxh.fiamproperty.R;
import com.hqxh.fiamproperty.constant.GlobalConfig;
import com.hqxh.fiamproperty.ui.widget.BaseViewHolder;
import com.hqxh.fiamproperty.unit.JsonUnit;

import java.util.List;
import com.hqxh.fiamproperty.model.R_Wfassignemt.Wfassignment;


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
        String app=JsonUnit.convertStrToArray(item.getAPP())[0];
        if(app.equals(GlobalConfig.TRAVEL)){//出差申请
            helper.setImageResource(R.id.task_image_id, R.mipmap.ic_ccsq);
        }else if(app.equals(GlobalConfig.GRWZ)){//出门管理
            helper.setImageResource(R.id.task_image_id, R.mipmap.ic_cmgl);
        }else if(app.equals(GlobalConfig.SZPR)){//采购申请
            helper.setImageResource(R.id.task_image_id, R.mipmap.ic_cgsq);
        }else if(app.equals(GlobalConfig.TOQT)){//任务单
            helper.setImageResource(R.id.task_image_id, R.mipmap.ic_rwd);
        }
//        else if(app.equals(GlobalConfig.GRWZ)){//合同
//            helper.setImageResource(R.id.task_image_id, R.mipmap.ic_ht);
//        }else if(app.equals(GlobalConfig.GRWZ)){//付款验收
//            helper.setImageResource(R.id.task_image_id, R.mipmap.ic_fkys);
//        }else if(app.equals(GlobalConfig.GRWZ)){//需款计划
//            helper.setImageResource(R.id.task_image_id, R.mipmap.ic_xkjh);
//        }else if(app.equals(GlobalConfig.GRWZ)){//报销
//            helper.setImageResource(R.id.task_image_id, R.mipmap.ic_bx);
//        }
//
        else {//其他
            helper.setImageResource(R.id.task_image_id, R.mipmap.ic_qt);
        }

        Log.e("APP",item.getAPP()+",DUEDATE="+item.getDUEDATE());
        helper.setText(R.id.duedate_text_id, JsonUnit.convertStrToArray(item.getDUEDATE())[0]);
        helper.setText(R.id.processname_text_id, JsonUnit.convertStrToArray(item.getUDAPPNAME())[0]);
        helper.setText(R.id.description_text_id, JsonUnit.convertStrToArray(item.getDESCRIPTION())[0]);
    }





}
