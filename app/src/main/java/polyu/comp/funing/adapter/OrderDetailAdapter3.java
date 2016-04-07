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
import polyu.comp.funing.model.OrderDetail;
import polyu.comp.funing.model.Product;

/**
 * Created by liushanchen on 16/3/19.
 */
public class OrderDetailAdapter3 extends BaseAdapter {
    List<OrderDetail> myList=null;
    Context context;
    LayoutInflater layoutInflater;

    public OrderDetailAdapter3(Context context) {
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
    public OrderDetail getItem(int position) {
        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OrderDetail item = getItem(position);
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.product_list_item, parent, false);
        }
        ImageView pItemImg=(ImageView)view.findViewById(R.id.p_item_img);
        TextView pName=(TextView)view.findViewById(R.id.p_name);
        TextView pPrice=(TextView)view.findViewById(R.id.p_price);
        Picasso.with(context).load(item.getP_image_url()).resize(200, 200).centerCrop().into(pItemImg);
        pName.setText(item.getP_name());
        pPrice.setText(item.getP_price()+"");
        
        return view;
    }

    public List<OrderDetail> getMyList() {
        return myList;
    }

    public void setMyList(List<OrderDetail> myList) {
        this.myList = myList;
    }
    
    private void addProductToShoppingCart(){

    }
}
