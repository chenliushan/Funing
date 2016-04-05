package polyu.comp.funing.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import polyu.comp.funing.R;
import polyu.comp.funing.constant.CommonConstant;
import polyu.comp.funing.model.User;
import polyu.comp.funing.utils.CommonUtils;

/**
 * Created by liushanchen on 16/4/5.
 */
public class UserInfoF extends Fragment {
    private TextView phone;
    private TextView name;
    private TextView address;
    private TextView address2;
    private TextView email;
    private TextView uid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_info, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        phone = (TextView) getActivity().findViewById(R.id.r_phone);
        uid = (TextView) getActivity().findViewById(R.id.r_id);
        name = (TextView) getActivity().findViewById(R.id.r_name);
        address = (TextView) getActivity().findViewById(R.id.r_address);
        address2 = (TextView) getActivity().findViewById(R.id.r_address2);
        email = (TextView) getActivity().findViewById(R.id.r_email);

        User user = CommonUtils.getUser(getActivity());
        name.setText(user.getName());
        email.setText(user.getEmail());
        String addr = user.getAddress();
        if (addr != null) {
            if(addr.length()>10){
                address.setText(addr.substring(0, 10));
                address2.setText(addr.substring(10));
            }else{
                address.setText(addr);
            }
        }
        phone.setText(user.getPhone());
        uid.setText(CommonConstant.userId+"");
    }
}
