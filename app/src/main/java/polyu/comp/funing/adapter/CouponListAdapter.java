package polyu.comp.funing.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import polyu.comp.funing.R;
import polyu.comp.funing.model.Coupon;

/**
 * Created by liushanchen on 16/3/19.
 */
public class CouponListAdapter extends BaseAdapter {
    List<Coupon> myList=null;
    Context context;
    LayoutInflater layoutInflater;

    public CouponListAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if(myList!=null){
            return myList.size();
        }else{
            return 0;
        }
    }

    @Override
    public Coupon getItem(int position) {
        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Coupon item = getItem(position);
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.coupon_list_item, parent, false);
        }
        ImageView pItemImg=(ImageView)view.findViewById(R.id.p_item_img);
        TextView pName=(TextView)view.findViewById(R.id.cp_name);
        TextView expiryDate=(TextView)view.findViewById(R.id.cp_expired_date);
        Picasso.with(context).load(item.getC_image_url()).into(pItemImg);
        pName.setText(item.getC_name());
        expiryDate.setText(item. getUc_expired_at());
//        String discount=null;
//        if(item.getC_discount_type().equals("cash")){
//            discount="$"+item.getC_discount_detail();
//        }else{
//            discount=item.getC_discount_detail()+"off";
//        }
//        pPrice.setText(discount);
        
        return view;
    }

    public List<Coupon> getMyList() {
        return myList;
    }

    public void setMyList(List<Coupon> myList) {
        this.myList = myList;
    }
}
