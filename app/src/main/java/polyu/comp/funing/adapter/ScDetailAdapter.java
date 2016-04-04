package polyu.comp.funing.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import polyu.comp.funing.R;
import polyu.comp.funing.model.Product;
import polyu.comp.funing.model.ShoppingCartDetail;
import polyu.comp.funing.utils.CommonUtils;


/**
 * Created by liushanchen on 16/3/19.
 */
public class ScDetailAdapter extends BaseAdapter {
    private static String TAG = ScDetailAdapter.class.getSimpleName();

    List<ShoppingCartDetail> myList = null;
    Context context;
    LayoutInflater layoutInflater;

    public ScDetailAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (myList != null) {
            return myList.size();
        } else {
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
        final ShoppingCartDetail item = getItem(position);
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.shopping_cart_list_item, parent, false);
        }
        TextView pName = (TextView) view.findViewById(R.id.sc_p_name);
        TextView pPrice = (TextView) view.findViewById(R.id.sc_p_price);
        TextView quantityD = (TextView) view.findViewById(R.id.sc_quantity_decrease);
        TextView quantityET = (TextView) view.findViewById(R.id.sc_quantity);
        TextView quantityA = (TextView) view.findViewById(R.id.sc_quantity_add);
        TextView deleteT = (TextView) view.findViewById(R.id.sc_delete_p);

        double price=item.getP_price();
        final int quantity=item.getSd_quantity();
        pName.setText(item.getP_name());
        pPrice.setText("HK$" +  (price*quantity)+ "");
        quantityET.setText( quantity+ "");
        quantityD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantity==1){
                    CommonUtils.show(context,context.getResources().getString(R.string.decrease_quantity));
                    return;
                }
                item.setSd_quantity(quantity-1);
                updateScDetail(quantity-1,item);
                notifyDataSetChanged();
            }
        });
        quantityA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setSd_quantity(quantity+1);
                updateScDetail(quantity+1,item);
                notifyDataSetChanged();
            }
        });
        deleteT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProductFromSc();
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
        this.myList.addAll(myList);
    }

    private void deleteProductFromSc() {

    }
    private void updateScDetail(int quantity, ShoppingCartDetail shoppingCartDetail) {
        shoppingCartDetail.setSd_quantity(quantity);
        
    }
}
