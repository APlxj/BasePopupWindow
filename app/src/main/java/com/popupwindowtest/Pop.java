package com.popupwindowtest;

import android.app.Activity;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * 类描述：
 * 创建人：swallow.li
 * 创建时间：
 * Email: swallow.li@kemai.cn
 * 修改备注：
 */
public class Pop extends BasePopWindow implements CompoundButton.OnCheckedChangeListener {

    public Pop(Activity context) {
        super(context);
        init();
        toast = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
    }

    private void init() {
        RadioButton rb1 = (RadioButton) contentView.findViewById(R.id.rb_1);
        RadioButton rb2 = (RadioButton) contentView.findViewById(R.id.rb_2);
        RadioButton rb3 = (RadioButton) contentView.findViewById(R.id.rb_3);
        RadioButton rb4 = (RadioButton) contentView.findViewById(R.id.rb_4);
        RadioButton rb5 = (RadioButton) contentView.findViewById(R.id.rb_5);

        rb1.setOnCheckedChangeListener(this);
        rb2.setOnCheckedChangeListener(this);
        rb3.setOnCheckedChangeListener(this);
        rb4.setOnCheckedChangeListener(this);
        rb5.setOnCheckedChangeListener(this);

    }

    @Override
    public int getLayout() {
        return R.layout.pop;
    }

    Toast toast;

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        this.dismiss();//点击消失
        switch (buttonView.getId()) {
            case R.id.rb_1:
                toast.setText("选中按钮1");
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.rb_2:
                toast.setText("选中按钮2");
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.rb_3:
                toast.setText("选中按钮3");
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.rb_4:
                toast.setText("选中按钮4");
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.rb_5:
                toast.setText("选中按钮5");
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.show();
                break;
        }
    }
}
