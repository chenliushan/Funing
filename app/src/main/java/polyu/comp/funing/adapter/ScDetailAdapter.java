package polyu.comp.funing.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import polyu.comp.funing.R;
import polyu.comp.funing.model.Coupon;
import polyu.comp.funing.model.ShoppingCart;
import polyu.comp.funing.model.ShoppingCartDetail;


/**
 * Created by liushanchen on 16/3/19.
 */
public class ScDetailAdapter extends BaseAdapter {
    
    List<ShoppingCartDetail> myList=null;
    Context context;
    LayoutInflater layoutInflater;

    public ScDetailAdapter(Context context) {
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
    public ShoppingCartDetail getItem(int position) {
        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ShoppingCartDetail item = getItem(position);
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.shopping_cart_list_item, parent, false);
        }
        TextView pName=(TextView)view.findViewById(R.id.sc_p_name);
        TextView pPrice=(TextView)view.findViewById(R.id.sc_p_price);
        EditText quantityET=(EditText)view.findViewById(R.id.sc_quantity);
        TextView deleteT=(TextView)view.findViewById(R.id.sc_delete_p);
        
        pName.setText(item.getP_name());
        pPrice.setText(item. getP_price()+"");
        quantityET.setText(item.getSd_quantity());
        deleteT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProductFromCart();
                myList.remove(position);
                notifyDataSetChanged();
            }
        });
//        String discount=null;
//        if(item.getC_discount_type().equals("cash")){
//            discount="$"+item.getC_discount_detail();
//        }else{
//            discount=item.getC_discount_detail()+"off";
//        }
//        pPrice.setText(discount);
        
        return view;
    }

    public List<ShoppingCartDetail> getMyList() {
        return myList;
    }
    public void setMyList(List<ShoppingCartDetail> myList) {
        this.myList = myList;
    }
    public void updateMyList(List<ShoppingCartDetail> myList) {
        this.myList .addAll(myList);
    }
    
    private void deleteProductFromCart(){
        
    }
}
