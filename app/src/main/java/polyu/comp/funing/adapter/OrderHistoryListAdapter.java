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
import polyu.comp.funing.model.Order;
import polyu.comp.funing.model.Product;

/**
 * Created by liushanchen on 16/3/19.
 */
public class OrderHistoryListAdapter extends BaseAdapter {
    List<Order> myList=null;
    Context context;
    LayoutInflater layoutInflater;

    public OrderHistoryListAdapter(Context context) {
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
    public Order getItem(int position) {
        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Order item = getItem(position);
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.order_history_list_item, parent, false);
        }
        TextView oid=(TextView)view.findViewById(R.id.oid);
        TextView o_status=(TextView)view.findViewById(R.id.o_status);
        TextView o_amount=(TextView)view.findViewById(R.id.o_amount);
        TextView o_date=(TextView)view.findViewById(R.id.o_date);
        oid.setText("#"+Integer.toString(item.getOid() + 10000));
        o_status.setText(item.getO_status()+"");
        o_amount.setText("$"+item.getO_amount());
        o_date.setText(item.getO_created_at()+"");
        
        return view;
    }

    public List<Order> getMyList() {
        return myList;
    }

    public void setMyList(List<Order> myList) {
        this.myList = myList;
    }
    
    private void addProductToShoppingCart(){

    }
}
