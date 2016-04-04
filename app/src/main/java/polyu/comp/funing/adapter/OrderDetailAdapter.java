package polyu.comp.funing.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import polyu.comp.funing.R;
import polyu.comp.funing.constant.CommonConstant;
import polyu.comp.funing.model.ScDbProcess;
import polyu.comp.funing.model.ShoppingCartDetail;
import polyu.comp.funing.service.ApiService;
import polyu.comp.funing.service.ScDetailR;
import polyu.comp.funing.utils.CommonUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by liushanchen on 16/3/19.
 */
public class OrderDetailAdapter extends BaseAdapter {
    private static String TAG = OrderDetailAdapter.class.getSimpleName();

    List<ShoppingCartDetail> myList = null;
    Context context;
    LayoutInflater layoutInflater;

    public OrderDetailAdapter(Context context) {
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
            view = layoutInflater.inflate(R.layout.order_product_item, parent, false);
        }
        TextView pName = (TextView) view.findViewById(R.id.o_p_name);
        TextView pPrice = (TextView) view.findViewById(R.id.o_p_price);
        TextView quantityET = (TextView) view.findViewById(R.id.o_quantity);

        double price=item.getP_price();
        final int quantity=item.getSd_quantity();
        pName.setText(item.getP_name());
        pPrice.setText("HK$" +  (price*quantity)+ "");
        quantityET.setText( quantity+ "");
        
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

    
   
}
